
package com.k_int.euinside.core

import java.nio.charset.Charset;
import javax.servlet.http.HttpServletResponse;

import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Aggregator;
import com.k_int.euinside.client.module.aggregator.CultureGridDataEnrichments;
import com.k_int.euinside.client.module.aggregator.EuropeanaDataEnrichments;
import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaEnrichments
import com.k_int.euinside.client.module.aggregator.europeana.processor.EnrichmentProcessorArray;
import com.k_int.euinside.client.xml.ClientXML;

class AggregatorService {
	private static def ACTION_ENRICHMENT_RECORD = "enrichmentRecord";
	private static def ACTION_SEARCH            = "search";
	private static def ACTION_STATISTICS        = "statistics";

	def grailsApplication
	def LoggingUtilitiesService
	def ModulesService
	
	public static Charset UTF8 = Charset.forName("UTF-8");
	private static def aggregators;

	def initialise() {
		aggregators = grailsApplication.config.aggregatorConfiguration;
		log.debug("Config locations: " + grailsApplication.config.grails.config.locations);
		
		// We need to merge the site configuration from modules
		def aggregatorConfigurationSite = grailsApplication.config.aggregators;

		// Merge the site configuration with the default configuration
		aggregators = aggregators.merge(aggregatorConfigurationSite);

		// Log all the information we have for an aggregator to the log file
		LoggingUtilitiesService.logObject("Dumping aggregator configuration", aggregators);
	}

	def getAggregators(hasAction) {
		def foundAggregators = [:];
		aggregators.each() {aggregatorName, aggregatorDetails ->
			def actions = aggregatorDetails.actions;
			def actionProperties = actions[hasAction];
			if ((actionProperties != null) && !actionProperties.isEmpty()) {
				foundAggregators.put(aggregatorName, aggregatorDetails)
			}
		} 
		return(foundAggregators);
	}

	private def setError(errorText) {
		def result = [ : ];
		result.status = [ : ];
		result.status.statusCode = HttpServletResponse.SC_BAD_REQUEST;
		result.status.reasonPhrase = errorText;
		result.contentBytes = errorText.getBytes(UTF8);
		return(result);
	}

    def execute(parameters, requestObject) {
		def aggregatorName = parameters.aggregatorName;
		def aggregator = aggregators[aggregatorName];
		def responseValue = null;
		if (aggregator.isEmpty()) {
			responseValue = setError("Unknown Aggregator: \"" + aggregatorName + "\"");
		} else {
			// It is an aggregator we know about
			def action = parameters.aggregatorAction;
			def actionProperties = aggregator.actions[action];
			if ((actionProperties == null) || actionProperties.isEmpty()) {
				responseValue = setError("Unknown action for Aggregator: \"" + action + "\"");
			} else {
				// Do we have a scenario where it is not generic, in which case how do make it generic ?
				def specialProcessing = actionProperties.'specialProcessing';
				if ((specialProcessing != null) && !specialProcessing.isEmpty()) {
					responseValue = "$specialProcessing"(parameters, requestObject, aggregator, action, actionProperties, actionProperties.'specialProcessingParameter');
				}
				if (responseValue == null) {
					// Build up the potential parameter replacements
					def replacementFields = [ : ];
					def parameter1 = parameters.parameter1;
					def parameter2 = parameters.parameter2;
					def defaultReplacements = actionProperties.'defaultReplacements'; 
					switch (action) {
						case ACTION_ENRICHMENT_RECORD:
							addReplacementField(replacementFields, "RECORD_ID", parameter2, defaultReplacements);
							addReplacementField(replacementFields, "SET_ID", parameter1, defaultReplacements);
							addReplacementField(replacementFields, "PROVIDER_OR_SET_ID", parameter1, defaultReplacements);
							break;
							
						case ACTION_SEARCH:
						case ACTION_STATISTICS:
							addReplacementField(replacementFields, "COLLECTION", parameter2, defaultReplacements);
							addReplacementField(replacementFields, "PROVIDER", parameter1, defaultReplacements);
							addReplacementField(replacementFields, "PROVIDER_OR_SET_ID", parameter1, defaultReplacements);
							break;
							
						default:
							// Add parameter1 and parameter2 as parameters for any user defined actions
							addReplacementField(replacementFields, "PARAMETER1", parameter1, defaultReplacements);
							addReplacementField(replacementFields, "PARAMETER2", parameter2, defaultReplacements);
							break;
					}
					
					// It is an action this aggregator knows about
					def moduleName = aggregator.'moduleName';
					
					// Set the path we use to get the result
					String path = actionProperties.'path';
					parameters.'path' = replaceKeyFields(path, replacementFields);
					
					// Set any parameters from the configuration
					def actionParameters = actionProperties.'parameters';
					if (!actionParameters.isEmpty()) {
						actionParameters.each() {
							def value = replaceKeyFields(it.value, replacementFields);
							if ((value != null) && !value.isEmpty()) {
								parameters[it.key] = value;
							}
						}
					}
					
					// Do we need to map any parameters
					def mapParameters = actionProperties.'mapParameters';
					if (!mapParameters.isEmpty()) {
						mapParameters.each() {
							def value = parameters[it.value]
							if ((value != null) && !value.isEmpty()) {
								parameters[it.key] = value;
							}
						}
					}
	
					// Do we have any default parameters
					def defaultParameters = actionProperties.'defaultParameters';
					if (!defaultParameters.isEmpty()) {
						defaultParameters.each() {
							def value = parameters[it.key]
							if ((value == null) || value.isEmpty()) {
								parameters[it.key] = it.value;
							}
						}
					}
	
					// Go away get the response from the aggregator
					responseValue = ModulesService.httpGet(moduleName, parameters, requestObject, false);
					
					if (responseValue.status.statusCode == HttpServletResponse.SC_OK) {
						if (responseValue.contentBytes == null) {
							// As good as any other to deafult to, since it will be empty
							responseValue.JSONResponse = new EuropeanaEnrichments();
						} else {
							// Will we be receiving xml
							def isXMLResponse = actionProperties.xmlResponse;
							isXMLResponse = ((isXMLResponse instanceof Boolean) && isXMLResponse);
							
							// Can we turn this into a generic statistic response
							// If we have an xml response then we do need to turn it into a class file ...
							def rawRequired = parameters.rawRequired;
							if ((rawRequired == null) || !rawRequired.equals("yes") || isXMLResponse) {
								// They have not requested the raw response
								def convertedOutput = null;
								def classFile = actionProperties.parser;
								if (!classFile.isEmpty()) {
									// A class file has been defined to turn it into a generic stats response
									try {
										// Lets see if we can load the class file
										def classObject = getClass().getClassLoader().loadClass(classFile);
										if (classObject != null) {
											// We have loaded it, so lets turn the json into a String and attempt to turn it
											// into the class object 
											def data = new String(responseValue.contentBytes, UTF8);
											if ((data != null) && !data.isEmpty()) {
												def returnedObject;
												if (isXMLResponse) {
													returnedObject = ClientXML.readXMLString(data, classObject);
												} else {
													returnedObject = ClientJSON.readJSONString(data, classObject);
												}
												if (returnedObject != null) {
													// We have successfully interpreted the json, so let us now turn it into aGeneric Stats object
													try {
														responseValue.JSONResponse = returnedObject.convertToGeneric();
													} catch (MissingMethodException e) {
														// That is fine we can ignore the error, just means we will not map it to the generic format
														log.error("Unable to find method convertToGeneric in class: \"" + classFile + "\"");
													}
												}
											}
										}
									} catch (ClassNotFoundException e) {
										// That is fine we can ignore the error, just means we will not map it to the generic format
										log.error("Unable to load class file: \"" + classFile + "\"");
									}
								}
							} 
						}
					}
				}
			}
		}
		return(responseValue);
    }

	private def addReplacementField(replacementFields, key, value, defaultReplacements) {
		if ((value == null) || value.isEmpty()) {
			// Do we have a default replacement value
			if ((defaultReplacements != null) && !defaultReplacements.isEmpty()) {
				value = defaultReplacements[key];
			}
			
			// If value is null, set it to an empty string
			if (value == null) {
				value = "";
			}
		}
		replacementFields["\$(" + key + ")"] = value;
	}	
	
	private def replaceKeyFields(value, replacementFields) {
		def result = value;
		replacementFields.each() {
			result = result.replace(it.key , it.value);
		}
		return(result);
	}

	private def convertLong(text, defaultValue) {
		def number = defaultValue;
		if ((text != null) && !text.isEmpty()) {
			try {
				number = Long.parseLong(text);
			} catch (NumberFormatException e) {
				// do nothing as the default value has been set
			}
		}
		return(number);	
	}
		
	private def convertInt(text, defaultValue) {
		def number = defaultValue;
		if ((text != null) && !text.isEmpty()) {
			try {
				number = Integer.parseInt(text);
			} catch (NumberFormatException e) {
				// do nothing as the default value has been set
			}
		}
		return(number);	
	}

	private def cultureGridMultiple(aggregator, start, processor, setId, actionProperties) {
		CultureGridDataEnrichments.getEnrichments(Aggregator.get(aggregator.'moduleName'), setId, processor, start);
	}
		
	private def europeanaMultiple(aggregator, start, processor, setId, actionProperties) {
		def defaultParameters = actionProperties.'defaultParameters';
		EuropeanaDataEnrichments.getEnrichments(defaultParameters.'wskey', setId, processor, start);
	}
		
	private def checkForMultiple(parameters, requestObject, aggregator, action, actionProperties, processingMethod) {
		def responseValue = null;
		
		def ProviderOrSetId = parameters.parameter1;
		def SetOrRecordId = parameters.parameter2;
		def rows = parameters.rows;
		def start = parameters.start;
		
		if (SetOrRecordId.equals("*") ||
			((rows != null) && !rows.isEmpty()) ||
			((start != null) && !start.isEmpty())) {
			// Rather than return 1 record, we have a bundle of records to return
			// I should really have introduced a separate action to do this, unfortunately I wrote the documentation and
			// circulated it before doing the implementation, it is relatively configurable though
			def processor = new EnrichmentProcessorArray(convertInt(rows, 50));
			def actualStartPosition = convertLong(start, 1);
			"$processingMethod"(aggregator, actualStartPosition, processor, ProviderOrSetId, actionProperties);
			responseValue = [ : ];
			responseValue.status = [ : ];
			responseValue.status.statusCode = HttpServletResponse.SC_OK;
			responseValue.status.reasonPhrase = "OK";
			responseValue.JSONResponse = processor.getEnrichments();
		}
		return(responseValue); 
	}
}
