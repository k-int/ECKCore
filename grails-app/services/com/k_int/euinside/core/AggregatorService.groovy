package com.k_int.euinside.core

import java.nio.charset.Charset;
import javax.servlet.http.HttpServletResponse;

import com.k_int.euinside.client.json.ClientJSON;

class AggregatorService {

	def grailsApplication
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
		log.debug("Dumping aggregator configuration");
		dumpObject(aggregators, 0);
	}

	def dumpObject(object, indent) {
		def indentation = "";
		for (int i = 0; i < indent; i++) {
			indentation += "    ";
		}
		object.each() {
			if (it.value instanceof Map) {
				log.debug(indentation + "Key: \"" + it.key + "\"");
				dumpObject(it.value, indent + 1);
			} else {
				log.debug(indentation + "\"" + it.key + "\" = \"" + it.value + "\"");
			}
		}
	}
	
	def getAggregators() {
		return(aggregators);
	}

	def setError(errorText) {
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
			// Build up the potential parameter replacements
			def replacementFields = [ : ];
			def parameter1 = parameters.parameter1;
			def parameter2 = parameters.parameter2;
			def action = parameters.aggregatorAction;
			switch (action) {
				case "enrichmentRecord":
					addReplacementField(replacementFields, "RECORD_ID", parameter2);
					addReplacementField(replacementFields, "SET_ID", parameter1);
					break;
					
				case "search":
				case "statistics":
					addReplacementField(replacementFields, "COLLECTION", parameter2);
					addReplacementField(replacementFields, "PROVIDER", parameter1);
					break;
					
				default:
					// Add parameter1 and parameter2 as parameters for any user defined actions
					addReplacementField(replacementFields, "PARAMETER1", parameter1);
					addReplacementField(replacementFields, "PARAMETER2", parameter2);
					break;
			}
			def actionProperties = aggregator.actions[action];
			if ((actionProperties == null) || actionProperties.isEmpty()) {
				responseValue = setError("Unknown action for Aggregator: \"" + action + "\"");
			} else {
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
					// Can we turn this into a generic statistic response
					def rawRequired = parameters.rawRequired;
					if ((rawRequired == null) || !rawRequired.equals("yes")) {
						// They have not requested the raw response
						def convertedOutput = null;
						def classFile = actionProperties.parser;
						if (classFile != null) {
							// A class file has been defined to turn it into a generic stats response
							try {
								// Lets see if we can load the class file
								def classObject = getClass().getClassLoader().loadClass(classFile);
								if (classObject != null) {
									// We have loaded it, so lets turn the json into a String and attempt to turn it
									// into the class object 
									def json = new String(responseValue.contentBytes, UTF8);
									if ((json != null) && !json.isEmpty()) {
										def returnedObject = ClientJSON.readJSONString(json, classObject);
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
			return(responseValue);
		}
    }

	private def addReplacementField(replacementFields, key, value) {
		if (value != null) {
			replacementFields["\$(" + key + ")"] = value;
		}
	}	
	
	private def replaceKeyFields(value, replacementFields) {
		def result = value;
		replacementFields.each() {
			result = result.replace(it.key , it.value);
		}
		return(result);
	}
}
