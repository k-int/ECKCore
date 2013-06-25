package com.k_int.euinside.core.wrapper.persistence

import java.nio.charset.Charset

import groovyx.net.http.Method
import groovyx.net.http.HTTPBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody
import org.apache.http.entity.mime.HttpMultipartMode
import org.apache.http.entity.mime.MultipartEntity
import org.apache.http.entity.mime.content.StringBody

class KiPersistenceWrapperService {

	@javax.annotation.PostConstruct
	def init() {
		log.debug("KiPersistenceWrapperService::init called");
	}
	
	// Config settings
	private def static URL_PERSISTENCE_MODULE_BASE = "http://localhost:8080/ECKCore/Persistence";
	
	private def static CALLBASE_IDENTIFY    = "/";
	private def static CALLBASE_PERSISTENCE = "/persistence/";
	
	private def static FORMAT_JSON = "json"

	private def static METHOD_CREATE_RECORD                  = "createRecord";
	private def static METHOD_IDENTIFY                       = "identify";
	private def static METHOD_LOOKUP_RECORD_BY_CMS_ID        = "lookupRecordByCmsId";
	private def static METHOD_LOOKUP_RECORD_BY_ECK_ID        = "lookupRecordByEckId";
	private def static METHOD_LOOKUP_RECORD                  = "lookupRecord";
	private def static METHOD_LOOKUP_RECORD_BY_PERSISTENT_ID = "lookupRecordByPersistentId";
	private def static METHOD_LOOKUP_RECORDS                 = "lookupRecords";
	private def static METHOD_LOOKUP_RECORD_ANY_ID_TYPE      = "lookupRecordsAnyIdType";
	private def static METHOD_SAVE_OR_UPDATE_RECORD          = "saveOrUpdateRecord";
	private def static METHOD_SAVE_RECORD                    = "saveRecord";
	private def static METHOD_UPDATE_RECORD                  = "updateRecord";

	private def static ARGUMENT_CMS_ID          = "cmsId";
	private def static ARGUMENT_DELETED         = "deleted";
	private def static ARGUMENT_ECK_ID          = "eckId";
	private def static ARGUMENT_ID              = "id";
	private def static ARGUMENT_ID_TYPE         = "idType";
	private def static ARGUMENT_PERSISTENT_ID   = "persistentId";
	private def static ARGUMENT_RECORD_CONTENTS = "recordContents";
	
	private def static JSON_NULL = "null";
	
	private def static EXTENSION_JSON = ".json";

	private def static CONTENT_TYPE_FORM = "multipart/form-data";
		
	def lookupRecordByEckId(params) {
		
		def eckId = params.eckId;
		def method = METHOD_LOOKUP_RECORD_BY_ECK_ID;
		def format = FORMAT_JSON;
		def args = [ : ];
		args.put(ARGUMENT_ECK_ID, eckId);
		
		def httpResponse = httpGet(method, format, args, CALLBASE_PERSISTENCE);
		return httpResponse;
	}
	
	def lookupRecordByCmsId(params) {
		
		def cmsId = params.cmsId;
		
		def method = METHOD_LOOKUP_RECORD_BY_CMS_ID;
		def format = FORMAT_JSON;
		def args = [ : ];
		args.put(ARGUMENT_CMS_ID, cmsId);
		
		def httpResponse = httpGet(method, format, args, CALLBASE_PERSISTENCE);
		return httpResponse;
	}
	
	def lookupRecordByPersistentId(params) {
		
		def persistentId = params.persistentId;
		
		def method = METHOD_LOOKUP_RECORD_BY_PERSISTENT_ID;
		def format = FORMAT_JSON;
		def args = [ : ];
		args.put(ARGUMENT_PERSISTENT_ID, persistentId);
		
		def httpResponse = httpGet(method, format, args, CALLBASE_PERSISTENCE);
		return httpResponse;
	}
	
	def lookupRecord(params) {
		
		def id = params.id;
		def idType = params.idType;
		
		def method = METHOD_LOOKUP_RECORD;
		def format = FORMAT_JSON;
		def args = [ : ]
		args.put(ARGUMENT_ID, id);
		args.put(ARGUMENT_ID_TYPE, idType);
		
		def httpResponse = httpGet(method, format, args, CALLBASE_PERSISTENCE);
	
		return httpResponse;
	}
	
	def lookupRecordsAnyIdType(params) {
		
		def id = params.id;
		
		def method = METHOD_LOOKUP_RECORD_ANY_ID_TYPE;
		def format = FORMAT_JSON;
		def args = [ : ];
		args.put(ARGUMENT_ID, id);
		
		def httpResponse = httpGet(method, format, args, CALLBASE_PERSISTENCE);
	
		return httpResponse;
	}
	
	def lookupRecords(params) {
		
		def cmsId = params.cmsId;
		def persistentId = params.persistentId;
		def eckId = params.eckId;
		
		def method = METHOD_LOOKUP_RECORDS;
		def format = FORMAT_JSON;
		def args = [ : ];
		args.put(ARGUMENT_CMS_ID, cmsId);
		args.put(ARGUMENT_PERSISTENT_ID, persistentId);
		args.put(ARGUMENT_ECK_ID, eckId);
		
		def httpResponse = httpGet(method, format, args, CALLBASE_PERSISTENCE);
	
		return httpResponse;
	}
	
	def createRecord(params) {
		
		def method = METHOD_CREATE_RECORD;
		def format = FORMAT_JSON;
		def args = [:];
		
		def httpResponse = httpPost(method, format, args, CALLBASE_PERSISTENCE);
		return httpResponse;
	}
	
	def saveRecord(params) {
		
		def record = params.record;
		return saveRecord(record.cmsId, record.persistentId, record.deleted, record.originalData, params.contentType, params.filename);
	}
	
	def saveRecord(cmsId, persistentId, deleted, originalData, contentType, filename) {
		
		def method = METHOD_SAVE_RECORD;
		def format = FORMAT_JSON;
		def args = [ : ];
		args.put(ARGUMENT_CMS_ID, cmsId);
		args.put(ARGUMENT_PERSISTENT_ID, persistentId);
		args.put(ARGUMENT_DELETED, deleted);
		
		def httpResponse = httpPost(method, format, args, originalData, contentType, filename, CALLBASE_PERSISTENCE);
		return httpResponse;
	}
	
	def updateRecord(params) {
		
		def record = params.record;
		
		return updateRecord(record.id, record.cmsId, record.persistentId, record.deleted, record.originalData, params.contentType, params.filename);	
	}
	
	def updateRecord(eckId, cmsId, persistentId, deleted, originalData, contentType, filename) {
		
		def method = METHOD_UPDATE_RECORD;
		def format = FORMAT_JSON;
		def args = [ : ];
		args.put(ARGUMENT_ECK_ID, eckId);
		args.put(ARGUMENT_CMS_ID, cmsId);
		args.put(ARGUMENT_PERSISTENT_ID, persistentId);
		args.put(ARGUMENT_DELETED, deleted);
		
		def httpResponse = httpPost(method, format, args, originalData, contentType, filename, CALLBASE_PERSISTENCE);
		return httpResponse;
	}
	
	def saveOrUpdateRecord(params) {
		
		def record = params.record;
		return saveOrUpdateRecord(record.id, record.cmsId, record.persistentId, record.deleted, record.originalData, params.contentType, params.filename);	
	}
	
	def saveOrUpdateRecord(eckId, cmsId, persistentId, deleted, originalData, contentType, filename) {
		
		def method = METHOD_SAVE_OR_UPDATE_RECORD;
		def format = FORMAT_JSON;
		def args = [ : ];
		args.put(ARGUMENT_ECK_ID, eckId);
		args.put(ARGUMENT_CMS_ID, cmsId);
		args.put(ARGUMENT_PERSISTENT_ID, persistentId);
		args.put(ARGUMENT_DELETED, deleted);
		
		def httpResponse = httpPost(method, format, args, originalData, contentType, filename, CALLBASE_PERSISTENCE);
		return httpResponse;
	}
	
	def identify(params) {
		
		def method = METHOD_IDENTIFY;
		def format = FORMAT_JSON;
		def args = [:];
		
		def returnValue = httpGet(method, format, args, CALLBASE_IDENTIFY);
	}
	
	def httpGet(method, format, args, callBase) {
		def returnValue;
		
		def url = determineURL(callBase);
		def path = determinePath(method, format);
		
		log.debug("making HTTP call to url: " + url + " path: " + path);
		
		def http = new HTTPBuilder(url);
		
		// Perform a GET
		def query = [:];
		query.putAll(args);
		
		http.get(path: path, query: query) { httpResponse, json ->
			returnValue = processJSONResponse(httpResponse, json)
		}
		
		return returnValue;
	}

	private def processJSONResponse(httpResponse, json) {
		def returnValue = null;
		log.debug("HTTP Status Code response = " + httpResponse.statusLine.statusCode);
		if (httpResponse.statusLine.statusCode == 200) {
			log.debug("returned json = " + json.toString());
			
			if (JSON_NULL.equals(json.toString()))
				returnValue = null;
			else
				returnValue = json;
		}
		return(returnValue);
	}

	private def determineURL(callBase) {
		return(URL_PERSISTENCE_MODULE_BASE + callBase);
	}
		
	private def determinePath(method, format) {
		def path = method;
		if ( FORMAT_JSON.equals(format) ) {
			path = path + EXTENSION_JSON;
		}
		return(path);
	}
		
	private def httpPost(method, format, args, callBase) {
		return(httpPost(method, format, args, null, null, null, callBase))
	}
	
	private def httpPost(method, format, args, originalData, contentType, filename, callBase) {
		def returnValue;
		
		def url = determineURL(callBase);
		def path = determinePath(method, format);
		log.debug("making HTTP call to url: " + url + " path: " + path);
		
		// We can now post this file off to the persistence layer		
		def http = new HTTPBuilder(url);
		http.request(Method.POST) { req ->
			uri.path = path
			requestContentType: CONTENT_TYPE_FORM
			MultipartEntity multiPartContent = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE)

			// Only add the record contents, if we have been supplied with them
			if ((originalData != null) &&
				(contentType != null) &&
				(filename != null)) {	   
				// Add the file contents to the request as the recordContents parameter
			    multiPartContent.addPart(ARGUMENT_RECORD_CONTENTS, new ByteArrayBody(originalData, contentType, filename))
			}
				
			// Now add all the parameters, seems a bit of an odd way of doing it, but hey it seems to work	   
			args.each() {argument ->
				String value = (argument.value == null) ? "" : argument.value.toString();
				multiPartContent.addPart(argument.key, new StringBody(value))
			}

			// Now we can add the parts to the request
			req.setEntity(multiPartContent)

			// Define what we are going to do if we are successful and have received json response
			// We need to deal with failures in some sensible way	   
			response.success = { httpResponse, json ->
				returnValue = processJSONResponse(httpResponse, json)
			}	   
		}
	   
		return returnValue;
	}
}
