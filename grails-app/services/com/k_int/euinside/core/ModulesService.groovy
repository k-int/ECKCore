package com.k_int.euinside.core

import groovyx.net.http.Method
import groovyx.net.http.HTTPBuilder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.input.ReaderInputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.ByteArrayBody
import org.apache.http.entity.mime.HttpMultipartMode
import org.apache.http.entity.mime.MultipartEntity

class ModulesService {
	def grailsApplication

	public static String MODULE_CORE         = "Core";
	public static String MODULE_DEFINITION   = "Definition";
	public static String MODULE_PERSISTENCE  = "Persistence";
	public static String MODULE_PID_GENERATE = "PIDGenerate";
	public static String MODULE_PREVIEW      = "Preview";
	public static String MODULE_SET_MANAGER  = "SetManager";
	public static String MODULE_STATISTICS   = "Statistics";
	public static String MODULE_VALIDATE     = "Validate";    // monguz validation
	public static String MODULE_VALIDATE2    = "Validate2";   // semantika validation
	
	private static def modules;
	private static String corePath;
		
	/**
	 * Initialiser called by bootstrap to setup this service
	 */
	def initialise() {
		modules = grailsApplication.config.moduleConfiguration;
		
		// We need to merge the site configuration from modules
		def modulesSite = grailsApplication.config.modules;
		// Step through each of the modules the site has defined and update the predefined configuration with their configuration
		modulesSite.each() {
			// Is it a module we know abount
			def module = modules[it.key];
			if (module.isEmpty()) {
				log.error("Unknown module in the configuration: \"" + it.key + "\"");
				modules.remove(it.key);
			} else {
				// The 4 settings the site can set are internalURL, internalPath, externalURL and externalPath 
				log.info("Using local information for module: \"" + it.key + "\"");
				it.value.each() { key, value ->
					if (value instanceof String) {
						log.info("Setting " + key + " to: \"" + value + "\"");
						module[key] = value;
					}
				}
			}
		}
			
		// Log all the information we have for a module to the log file
		log.debug("Dumping module configuration");
		modules.each() {
			// Log the module information
			log.debug("Module: \"" + it.key + "\"");
			it.value.each () { key, value ->
				if (value instanceof String) {
					log.debug(key + ": \"" + value + "\"");
				} else {
					value.keySet().each { parameter ->
						log.debug("Parameter: \"" + parameter + "\"");
					}
				}
			}
		}
		
		corePath = modules[MODULE_CORE].externalPath;
	}

	public static String getCoreModuleCode() {
		return(MODULE_CORE);
	}
	
	public static String getDefinitionModuleCode() {
		return(MODULE_DEFINITION);
	}
	
	public static String getPersistenceModuleCode() {
		return(MODULE_PERSISTENCE);
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
			moduleParameters.keySet().each {
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
	private def determineURL(module, urlPath) {
		String url = modules[module].internalURL + modules[module].internalPath;
		if ((urlPath != null) && !urlPath.isEmpty()) {
			url += "/" + urlPath;
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
		result.contentString = "";

		// If we have a reader in our hand, then get hold of the string		
		if ((content != null) && (content instanceof java.io.Reader)) {
			// We probably shouldn't always turn it into a string, as there maybe the possibility in the future
			// that we are dealing with a binary response and that wioll catch us out
			result.contentString= IOUtils.toString(new ReaderInputStream(content, "UTF-8"), "UTF-8");
		}

		if (httpResponse.statusLine.statusCode == HttpServletResponse.SC_OK) {
			// We should have a content type if everything was OK
			result.contentType = httpResponse.getContentType();
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
		return(http(module, parameters, requestObject, Method.GET));
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
		return(http(module, parameters, requestObject, Method.POST));
	}

	/**
	 * Performs a gateway http request
	 * 	
	 * @param module .......... The module we are performing the gateway operation for 
	 * @param parameters ...... Query parameters that may need passing onto the module
	 * @param requestObject ... The original request object, required for passing through any posted files
	 * @param method .......... The http method to be performed, defined in groovyx.net.http.Method
	 * 
	 * @return ... A map that contains the following obects
	 *                 content ....... The returned content
	 *                 status ........ The status line
	 *                 contentType ... The type of the content
	 */
	private def http(module, parameters, requestObject, method) {
		def result = null;
		
		def url = determineURL(module, parameters.path);
		def queryArguments = createURLArgs(module, parameters);
		log.debug("making HTTP call to url: " + url);

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
		
		http.request(method) { req ->
			
			// Only add the file contents, if we have been supplied with them
			if (multiPartFiles == null) {
				// No files to send, so just set the content type to that of a form
				requestContentType : ContentType.MULTIPART_FORM_DATA
			} else {
				// If we only have 1 file then do not send it as a form
				if (multiPartFiles.size() == 1) {
					// We only have 1 file so set that directly to the body
					multiPartFiles.each() {parameterName, multiPartFile ->
						// Do we need to set the content type twice ????
						requestContentType : multiPartFile.contentType
												
						// We only have 1 file so set that directly to the body
						req.setEntity(new ByteArrayEntity(multiPartFile.bytes, ContentType.parse(multiPartFile.contentType)));
					}
				} else if (multiPartFiles.size() > 1) {
					requestContentType : ContentType.MULTIPART_FORM_DATA
					MultipartEntity multiPartContent = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE)
					multiPartFiles.each() {parameterName, multiPartFile ->
						// Add the file contents to the request as the specified parameter
					    multiPartContent.addPart(parameterName, new ByteArrayBody(multiPartFile.bytes, multiPartFile.contentType, multiPartFile.originalFilename));
					}
					
					// Now we can add the parts to the request
					req.setEntity(multiPartContent)
				}
			}
				
			// add all the arguments
			uri.query = queryArguments;
			
			// Set the Accept header
			headers.'Accept' = requestObject.getHeader("Accept");
			
			// Deal with the response
			// We need to deal with failures in some sensible way	   
			response.success = { httpResponse, content ->
				result = processResponse(httpResponse, content);
			}
			
			// handler for any failure status code:
			response.failure = { httpResponse, content ->
				result = processResponse(httpResponse, content);
			}
		}
	   
		return(result);
	}
}
