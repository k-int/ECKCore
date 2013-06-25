package com.k_int.euinside.core.upload

/**
 * Service to provide metadata upload / import services to the ECK Core
 * @author rpb rich@k-int.com
 * @version 1.0 08.01.13
 */
class ImportService {

	def kiPersistenceWrapperService;
	
	def storeMetadata(String cmsId, String persistentId, byte[] metadataFileContents, String contentType, String filename) {
		def retval = [:];
		
		def lookupParams = [id: cmsId, idType: "cms"];
		def existingRecord = kiPersistenceWrapperService.lookupRecord(lookupParams);
		if ( !existingRecord ) {
			lookupParams.id=persistentId;
			lookupParams.idType = "persistent";
			existingRecord = kiPersistenceWrapperService.lookupRecord(lookupParams);
		} 
		
		if ( existingRecord != null && existingRecord.id != null ) {
			// There's an existing record - update it
			log.debug("Updating an existing record");
			
			existingRecord.originalData = metadataFileContents;
			
			log.debug("About to persist the updated record");
			def updateParams = [record:existingRecord];
			existingRecord = kiPersistenceWrapperService.updateRecord(updateParams);
			
			retval.record = existingRecord;

		} else {
			// No existing record - create a new one
			log.debug("Saving a new record");
			
			def newRecord = kiPersistenceWrapperService.createRecord(null);
			newRecord.cmsId = cmsId;
			newRecord.persistentId = persistentId;
			newRecord.originalData = metadataFileContents;
			
			log.debug("Saving new record with details set");
			def saveParams = [record: newRecord, contentType: contentType, filename: filename];
			newRecord = kiPersistenceWrapperService.saveRecord(saveParams);
			
			retval.record = newRecord;
		}
		
		log.debug("Returning after storing metadata, etc. retval: " + retval);
		return retval;
	}
}
