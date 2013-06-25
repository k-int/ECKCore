package com.k_int.euinside.core.functions

import com.k_int.euinside.core.upload.ImportController;
import com.k_int.euinside.core.functions.FunctionController;
import com.k_int.euinside.core.lookup.LookupController;

/**
 * A service to describe functions that are provided internally to the ECK Core
 * 
 * @author rpb rich@k-int.com
 * @version 1.0 10.01.13
 *
 */
class ExternalFunctionsService {

	
	def kiPersistenceWrapperService;
	
	@javax.annotation.PostConstruct
	def init() {
		log.debug("ExternalFunctionsService::init called");
	}

	/**
	 * Compile and return the list of all available methods from the various ECK Core internal modules	
	 * @return The list of module definitions and their method definitions
	 */
	def list() {
		
		def persistenceResponse = kiPersistenceWrapperService.identify();
		
		def retval = [];
		if ( persistenceResponse != null )
			retval.add(persistenceResponse);
		
		return retval;
	}
	
}
