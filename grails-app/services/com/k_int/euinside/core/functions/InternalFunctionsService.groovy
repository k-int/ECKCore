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
class InternalFunctionsService {

	
	@javax.annotation.PostConstruct
	def init() {
		log.debug("InternalFunctionsService::init called");
	}

	/**
	 * Compile and return the list of all available methods from the various ECK Core internal modules	
	 * @return The list of module definitions and their method definitions
	 */
	def list() {
		def importResponse = ImportController.identify();
		def lookupResponse = LookupController.identify();
		def functionResponse = FunctionController.identify();
		
		def retval = [];
		if ( importResponse != null )
			retval.add(importResponse);
		if ( lookupResponse != null )
			retval.add(lookupResponse);
		if ( functionResponse != null )
			retval.add(functionResponse);
		
		return retval;
	}
	
}
