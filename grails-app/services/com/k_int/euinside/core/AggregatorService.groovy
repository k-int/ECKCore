package com.k_int.euinside.core

class AggregatorService {

	def grailsApplication
	def ModulesService
	
	private static def aggregators;
	
	def initialise() {
		aggregators = grailsApplication.config.aggregatorConfiguration;
		log.debug("Config locations: " + grailsApplication.config.grails.config.locations);
		
		// We need to merge the site configuration from modules
		def aggregatorConfigurationSite = grailsApplication.config.aggregators;
		
		// Step through each of the modules the site has defined and update the predefined configuration with their configuration
		aggregatorConfigurationSite.each() {
			// Is it a module we know abount
			def aggregator = aggregators[it.key];
			if (aggregator.isEmpty()) {
				aggregator = [ : ];
				aggregators[it.key] = aggregator;
				log.info("Adding aggregator \"" + it.key + "\" to the configuration: ");
			}

			it.value.each() { key, value ->
				log.info("Setting " + key + " to: \"" + value.toString() + "\"");
				aggregator[key] = value;
			}
		}
			
		// Log all the information we have for an aggregator to the log file
		log.debug("Dumping aggregator configuration");
		aggregators.each() {
			// Log the aggregator information
			log.debug("Aggregator: \"" + it.key + "\"");
			it.value.each() {key, value ->
				if (value instanceof String) {
					log.debug(key + ": \"" + value + "\"");
				} else if (key.equals("Parameters")){
					// Must be the parameters
					value.each() {parameter, parameterValue ->
						log.debug("Parameter: \"" + parameter + "\" value: \"" + parameterValue + "\"");
					}
				}
			}
		}
	}

	def getAggregators() {
		return(aggregators);
	}
	
    def execute(parameters, requestObject) {
		def aggregatorName = parameters.aggregatorName;
		def aggregator = aggregators[aggregatorName];
		if (aggregator.isEmpty()) {
			// Not an aggregator we know about
			return(null);
		} else {
			def providerCode = parameters.providerCode;
			def collectionCode = parameters.collectionCode;
			def aggregatorParameters = aggregator.'Parameters';
			String path = aggregator.'path';
			def moduleName = aggregator.'moduleName';
			String finalPath = replaceKeyFields(path, providerCode, collectionCode);
			parameters.'path' = finalPath;
			if (!aggregatorParameters.isEmpty()) {
				aggregatorParameters.each() {
					parameters[it.key] = replaceKeyFields(it.value, providerCode, collectionCode);
				}
			}

			return(ModulesService.httpGet(moduleName, parameters, requestObject, false));
		}
    }
	
	private def replaceKeyFields(value, providerCode, collectionCode) {
		return(value.replace("\$(PROVIDER)", providerCode).replace("\$(COLLECTION)", collectionCode));
	}
}
