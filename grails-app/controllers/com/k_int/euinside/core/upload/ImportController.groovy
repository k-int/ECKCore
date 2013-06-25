package com.k_int.euinside.core.upload

import com.k_int.euinside.shared.model.functions.ArgumentDefinition;
import com.k_int.euinside.shared.model.functions.MethodDefinition;
import com.k_int.euinside.shared.model.functions.ModuleDefinition;

import grails.converters.JSON

class ImportController {

	// Ensure save only responds to POST requests
	static allowedMethods = [save: 'POST'];
	
	def importService;
	
	/**
	 * Simple index action just to provide instructions on what to do
	 * in HTML format
	 */
    def index() { 
		log.debug("ImportController::index method called");
	}
	
	/**
	 * Simple action to mock up the submission of a metadata file into the
	 * ECK - not intended to be used generally
	 */
	def create() {
		log.debug("ImportController::create method called");
		
		def retVal = [:];
		
		return retVal;
	}
	
	/**
	 * Method to accept uploaded metadata record and store it in the ECK for now
	 * @return Success or failure and information regarding the upload as either HTML or JSON
	 */
	def save() {
		log.debug("ImportController::save method called");
		
		def cmsId = params.cmsId;
		def persistentId = params.persistentId;
		def metadataFile = request.getFile("metadataFile");

		// Set up the response to be returned
		def responseVal = [:];
		
		// Check for the required fields and complain if not received
		def responseMessages = [];
		def okToContinue = true;
		
		if ( !cmsId || "".equals(cmsId.trim()) ) {
			okToContinue = false;
			responseMessages.add("A CMS ID is required and has not been specified");
		}
		if ( !persistentId || "".equals(persistentId.trim()) ) {
			okToContinue = false;
			responseMessages.add("A persistent ID is required and has not been specified");
		}
		if ( !metadataFile || metadataFile.isEmpty() ) {
			okToContinue = false;
			responseMessages.add("A non-empty metadata file is required and has not been provided");
		}
		
		if ( okToContinue ) {

			def savedRecordInfo = importService.storeMetadata(cmsId, persistentId, metadataFile.bytes, metadataFile.contentType, metadataFile.originalFilename);
			
			if ( savedRecordInfo.record.id != null ) {
				responseVal.success = true;
				responseVal.messages = [];
				responseVal.messages.add("Metadata record successfully imported");
				responseVal.eckId = savedRecordInfo.record.id;
			} else {
				// Something's gone wrong with the save - complain
				responseVal.success = false;
				responseVal.messages = [];
				responseVal.messages.add("Something has gone wrong with saving the metadata");
				responseVal.eckId = 0l;
			}
		} else {
			// Can't continue as we don't have the required information - set up the return information
			// with this fact so that it can be returned as appropriate
			responseVal.success = false;
			responseVal.messages = responseMessages;
			responseVal.eckId = 0l;
		}
		
		withFormat {
			html { return [responseVal: responseVal] }
			json { render responseVal as JSON }
			
		}
	}
	
	/**
	 * Method to specify the methods, etc. provided by this controller for use
	 * in the function listing section of the system
	 * 
	 * @return Description of the 'module' and its available methods
	 */
	def static identify() {
		
		// Set up the index method
		def indexMethodDesc = new MethodDefinition("index", null, null, "Provide a simple frontpage to the import service (when called with an HTML content type)");

		// Set up the create method
		def createMethodDesc = new MethodDefinition("create", null, null, "Provide a simple form to allow the testing of importing records (when called with an HTML content type)");

		// Set up the save method
		def cmsIdArg = new ArgumentDefinition("cmsId", "String", true);
		def persistentIdArg = new ArgumentDefinition("persistentId", "String", true);
		def metadataFileArg = new ArgumentDefinition("metadataFile", "File", true);
		def args = new LinkedHashSet<ArgumentDefinition>();
		args.add(cmsIdArg);
		args.add(persistentIdArg);
		args.add(metadataFileArg);

		def saveMethodDesc = new MethodDefinition("save", args, "JSON wrapper", "Save or update the metadata record within the ECK");
		
		// Set up the 'module' description and add the various methods to it
		def importModule = new ModuleDefinition();
		importModule.name = "Import";
		importModule.moduleType = "internal";
		importModule.methodDefinitions.add(indexMethodDesc);
		importModule.methodDefinitions.add(createMethodDesc);
		importModule.methodDefinitions.add(saveMethodDesc);
		
		return importModule;
	}
}
