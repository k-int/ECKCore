import grails.converters.JSON

class BootStrap {
	def grailsApplication
	
    def init = { servletContext ->
		log.info("Config locations: " + grailsApplication.config.grails.config.locations);
		// Loop through all the services, to see if they need to be initialised
		grailsApplication.serviceClasses.each { 
			def serviceBean = grailsApplication.mainContext.getBean(it.propertyName) 
			if (it.metaClass.respondsTo(serviceBean, 'initialise')) { 
				// We have one that wants initialising and has an initialise method
				serviceBean.initialise() 
			} 
		}
		
		def excludeUnimportantFieldsAndNullFields = {it ->
			return(it.properties.findAll{key, value ->
				((key != "class") && (key != "logger") && (value != null))
			});
		}
		JSON.registerObjectMarshaller(com.k_int.euinside.client.module.aggregator.generic.GenericStatistic) {
			excludeUnimportantFieldsAndNullFields(it);
		}
		JSON.registerObjectMarshaller(com.k_int.euinside.client.module.aggregator.generic.GenericEuropeanaStatistic) {
			excludeUnimportantFieldsAndNullFields(it);
		}
    }
	
    def destroy = {
    }
}
