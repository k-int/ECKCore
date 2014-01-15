package com.k_int.euinside.core

import groovyx.net.http.Method
import groovyx.net.http.HTTPBuilder;

import java.nio.charset.Charset;
 
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.ByteArrayBody
import org.apache.http.entity.mime.content.StringBody
import org.apache.http.entity.mime.HttpMultipartMode
import org.apache.http.entity.mime.MultipartEntity
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicStatusLine;

class ModulesService {
	def grailsApplication
	def LoggingUtilitiesService;

	public static String MODULE_CORE                = "Core";
	public static String MODULE_DATA_MAPPING        = "DataMapping"; // Libis
	public static String MODULE_DATA_TRANSFORMATION = "DataTransformation"; // Libis
	public static String MODULE_DEFINITION          = "Definition";
	public static String MODULE_EUROPEANA           = "Europeana";
	public static String MODULE_PID_GENERATE        = "PIDGenerate";        // Semantika
	public static String MODULE_PREVIEW             = "Preview";
	public static String MODULE_SET_MANAGER         = "SetManager";
	public static String MODULE_STATISTICS          = "Statistics";
	public static String MODULE_VALIDATE            = "Validate";    // monguz validation
	public static String MODULE_VALIDATE2           = "Validate2";   // semantika validation
	public static Charset UTF8 = Charset.forName("UTF-8");
	
	private static def modules;
	private static String corePath;
		
	/**
	 * Initialiser called by bootstrap to setup this service
	 */
	def initialise() {
		modules = grailsApplication.config.moduleConfiguration;
		
		// We need to merge the site configuration from modules
		def modulesSite = grailsApplication.config.modules;
		
		// Merge the site configuration with the default configuration
		modules = modules.merge(modulesSite);

		// Log all the information we have for a module to the log file
		LoggingUtilitiesService.logObject("Dumping module configuration", modules);
		
		corePath = modules[MODULE_CORE].externalPath;
	}

	public static String getCoreModuleCode() {
		return(MODULE_CORE);
	}
	
	public static String getDataMappingModuleCode() {
		return(MODULE_DATA_MAPPING);
	}
	
	public static String getDataTransformationModuleCode() {
		return(MODULE_DATA_TRANSFORMATION);
	}
	
	public static String getDefinitionModuleCode() {
		return(MODULE_DEFINITION);
	}
	
	public static String getEuropeanaModuleCode() {
		return(MODULE_EUROPEANA);
	}
	
	public static String getPreviewModuleCode() {
		return(MODULE_PREVIEW);
	}
	
	public static String getSetManagerModuleCode() {
		return(MODULE_SET_MANAGER);
	}
	
	public static String getStatisticsModuleCode() {
		return(MODULE_STATISTICS);
	}
	
	public static String getValidateModuleCode() {
		return(MODULE_VALIDATE);
	}

	public static String getModuleInternalURL(String module) { 
		return(modules[module].internalURL);
	}

	public static String getModuleExternalPath(String module) { 
		return(modules[module].externalPath);
	}

	public static String getModuleInternalPath(String module) { 
		return(modules[module].internalPath);
	}

	public static def getModuleParameters(String module) { 
		return(modules[module].parameters);
	}

	/** 
	 * Determines the query arguments that are to be passed to the module
	 *  	
	 * @param module ....... The module we are performing the gateway operation for 
	 * @param parameters ... The parameters passed to usby the caller
	 * 
	 * @return The query arguments to be passed onto the module
	 */
	private def createURLArgs(module, parameters) {
		def arguments = [ : ];
		def moduleParameters = modules[module].parameters;
		if (moduleParameters != null) {
			moduleParameters.each() {
				def value = parameters.getAt(it);
				if (value != null) {
					arguments.putAt(it, value);
				} 
			}
		}
		return(arguments);
	}

	/**
	 * Determines the proper url for the module
	 * 		
	 * @param module .... The module we are performing the gateway operation for 
	 * @param urlPath ... The path within this module that the caller is wanting to goto
	 * 
	 * @return The url where the module lives
	 */
	private def determineURL(module, urlPath, appendJsonToURL) {
		String url = modules[module].internalURL + modules[module].internalPath;
		if ((urlPath != null) && !urlPath.isEmpty()) {
			if (!urlPath.startsWith("/")) {
				// Causes a problem if we have 2 slashes and parameters, without paramaters it works ...
				url += "/";
			}
			url += urlPath;
		}
		if (appendJsonToURL) {
			url += ".json";
		}
		return(url);
	} 

	/**
	 * Mangles the anchors in the html, so that links to the module still go through the gateway
	 * Links to css, js, etc. are changed so that they go directly to the module
	 *  
	 * @param module ... The module we are performing the gateway operation for 
	 * @param html   ... The html that has been returned
	 * 
	 * @return The html with the links modified
	 */
	def replacePathInHtml(module, html) {
		def externalURL = modules[module].externalURL;
		def externalPath = modules[module].externalPath;
		def internalPath = modules[module].internalPath;
		
		// anchors we need to replace in a slightly different way to links as they can still go through the core
		String anchor = "\\<[aA] .*\\" + internalPath; 
		html = html.replaceAll(anchor) {
			it.replace(internalPath, corePath + "/" + module);
		};
	
		// Now replace all the other parts with direct paths
		return(html.replace(internalPath, externalURL + externalPath));
	} 

	/**
	 * Interprets the response from the server to pull oyt the information we are interested in
	 *  
	 * @param httpResponse ... The response from the server
	 * @param content ........ The content returned from the server
	 * 
	 * @return ... A map that contains the following obects
	 *                 content ....... The returned content
	 *                 status ........ The status line
	 *                 contentType ... The type of the content
	 */
	private def processResponse(httpResponse, content) {
		def result = [ : ];
		result.content = content;
		result.status = httpResponse.statusLine;
		result.contentBytes = null;

		// If we have a reader in our hand, then get hold of the string		
		if (content != null) {
			if (content instanceof java.io.Reader) {
				// Turn the response into a byte array
				result.contentBytes = IOUtils.toByteArray(content, UTF8);
			} else if (content instanceof java.io.InputStream) {
				// Slightly different conersion in this case
				result.contentBytes = IOUtils.toByteArray(content);
			}
		}

		if (httpResponse.statusLine.statusCode == HttpServletResponse.SC_OK) {
			if (content == null) {
				// No content
				result.contentType = null;
			} else {
				// We should have a content type if everything was OK
				try {
					result.contentType = httpResponse.getContentType();
					if (result.contentType.equals("application/octet-stream")) {
						// Assume it is a zip file
						result.contentType = "application/zip";
					}
				} catch (IllegalArgumentException e) {
					// if the Content-Transfer-Encoding is binary, then we shall assume application/zip
					def headers = httpResponse.getHeaders("Content-Transfer-Encoding");
					if (headers != null) {
						headers.each() {
							if (it.getValue() == "binary") {
								result.contentType = "application/zip";
							}
						}
					}
				}
			}
		}
		return(result);
	}
		
	/**
	 * Performs a gateway GET request
	 * 	
	 * @param module .......... The module we are performing the gateway operation for 
	 * @param parameters ...... Query parameters that may need passing onto the module
	 * @param requestObject ... The original request object, required for passing through any posted files
	 * 
	 * @return ... A map that contains the following obects
	 *                 content ....... The returned content
	 *                 status ........ The status line
	 *                 contentType ... The type of the content
	 */
	def httpGet(module, parameters, requestObject) {
		return(httpGet(module, parameters, requestObject, false));
	}

	/**
	 * Performs a gateway GET request
	 * 	
	 * @param module .......... The module we are performing the gateway operation for 
	 * @param parameters ...... Query parameters that may need passing onto the module
	 * @param requestObject ... The original request object, required for passing through any posted files
	 * @param appendJsonToURL ... if true we append ".json" to the url
	 * 
	 * @return ... A map that contains the following obects
	 *                 content ....... The returned content
	 *                 status ........ The status line
	 *                 contentType ... The type of the content
	 */
	def httpGet(module, parameters, requestObject, appendJsonToURL) {
		return(http(module, parameters, requestObject, Method.GET, false, appendJsonToURL));
	}

	/**
	 * Performs a gateway http POST request
	 * 	
	 * @param module .......... The module we are performing the gateway operation for 
	 * @param parameters ...... Query parameters that may need passing onto the module
	 * @param requestObject ... The original request object, required for passing through any posted files
	 * @param treatEverythingAsFormData If true, treat parameters and attachments as form data
	 * 
	 * @return ... A map that contains the following obects
	 *                 content ....... The returned content
	 *                 status ........ The status line
	 *                 contentType ... The type of the content
	 */
	def httpPost(module, parameters, requestObject, treatEverythingAsFormData) {
		return(http(module, parameters, requestObject, Method.POST, treatEverythingAsFormData, false));
	}

	/**
	 * Performs a gateway http POST request
	 * 	
	 * @param module .......... The module we are performing the gateway operation for 
	 * @param parameters ...... Query parameters that may need passing onto the module
	 * @param requestObject ... The original request object, required for passing through any posted files
	 * 
	 * @return ... A map that contains the following obects
	 *                 content ....... The returned content
	 *                 status ........ The status line
	 *                 contentType ... The type of the content
	 */
	def httpPost(module, parameters, requestObject) {
		return(httpPost(module, parameters, requestObject, false));
	}

	/**
	 * Performs a gateway http request
	 * 	
	 * @param module .......... The module we are performing the gateway operation for 
	 * @param parameters ...... Query parameters that may need passing onto the module
	 * @param requestObject ... The original request object, required for passing through any posted files
	 * @param method .......... The http method to be performed, defined in groovyx.net.http.Method
	 * @param treatEverythingAsFormData If true, treat parameters and attachments as form data
	 * 
	 * @return ... A map that contains the following obects
	 *                 content ....... The returned content
	 *                 status ........ The status line
	 *                 contentType ... The type of the content
	 */
	private def http(module, parameters, requestObject, method, treatEverythingAsFormData, appendJsonToURL) {
		def result = null;
		
		def url = determineURL(module, parameters.path, appendJsonToURL);
		def queryArguments = createURLArgs(module, parameters);
		log.debug("making HTTP call to url: " + url);
		log.debug("Arguments: " + queryArguments);
		
		// Determine if we have a file to send on
		def multiPartFiles = null;

		// Only attempt to get the file parameter if this is a multipart request
		if ((requestObject != null) &&
			(requestObject instanceof org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest)) {
			multiPartFiles = requestObject.getFileMap(); 
		}
	
		// Now we have everything we need, let us perform the post		
		def http = new HTTPBuilder(url);

		// We are only interested in text output from the http builder, with regards to json, html and xml
		http.parser.'application/json' = http.parser.'text/plain';
		http.parser.'application/xml' = http.parser.'text/plain';
		http.parser.'text/xml' = http.parser.'text/plain';
		http.parser.'text/html' = http.parser.'text/plain';

		try {		
			http.request(method) { req ->
				def entity = null;
				def contentType = ContentType.MULTIPART_FORM_DATA;
				 
				// For Libis everything has to be posted ...
				if (treatEverythingAsFormData) {
					// Build up the file parts
					entity = buildMultiPartFilesEntity(multiPartFiles);				
	
					// We now need to deal with the query arguments
					addArgumentsToEntity(entity, queryArguments);
				} else {
					// Only add the file contents, if we have been supplied with them
					if ((multiPartFiles != null) && !multiPartFiles.isEmpty()) {
						// If we only have 1 file then do not send it as a form unless we are told to do so
						if (multiPartFiles.size() == 1) {
							// We only have 1 file so set that directly to the body
							multiPartFiles.each() {parameterName, multiPartFile ->
								// Do we need to set the content type twice ????
								contentType = multiPartFile.contentType
														
								// We only have 1 file so set that directly to the body
								entity = new ByteArrayEntity(multiPartFile.bytes, ContentType.parse(multiPartFile.contentType));
							}
						} else {
							// Build up the file parts
							entity = buildMultiPartFilesEntity(multiPartFiles);				
						}
					}
						
					// add all the arguments
					uri.query = queryArguments;
				}
	
				// Set the content type of the request			
				requestContentType : contentType;
				
				// If we have an entity then set it
				if (entity != null) {
					// Now we can add the parts to the request
					req.setEntity(entity);
				}
				
				// Set the Accept header
				headers.'Accept' = requestObject.getHeader("Accept");
				
				// Deal with the response
				response.success = { httpResponse, content ->
					result = processResponse(httpResponse, content);
				}
				
				// handler for any failure status code:
				response.failure = { httpResponse, content ->
					result = processResponse(httpResponse, content);
				}
			}
		} catch (ConnectException | UnknownHostException e) {
			result = [ : ];
			result.content = null;
			result.status = new BasicStatusLine(HttpVersion.HTTP_1_1, HttpServletResponse.SC_GATEWAY_TIMEOUT, "Timeout");
			result.contentBytes = null;
		}
		return(result);
	}

	/**
	 * Builds an entity containing the files to be sent
	 * 	
	 * @param multiPartFiles The files that need to passed on
	 * 
	 * @return The entity that contains the multipart files
	 */
	private def buildMultiPartFilesEntity(multiPartFiles) {
		def entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		if (multiPartFiles != null) {
			multiPartFiles.each() {parameterName, multiPartFile ->
				// Add the file contents to the request as the specified parameter
				entity.addPart(parameterName, new ByteArrayBody(multiPartFile.bytes, multiPartFile.contentType, multiPartFile.originalFilename));
			}
		}
		return(entity);
	}

	/**
	 * Adds the query arguments to the entity
	 * 	
	 * @param entity The entity the arguments are to be added to
	 * @param queryArguments The query arguments that need to be added to the entity
	 */
	private def addArgumentsToEntity(entity, queryArguments) {
		if (queryArguments != null) {
			queryArguments.each() {key, value ->
				// Add the parameters to the request
				entity.addPart(key, new StringBody(value));
			}
		}
	}
}
