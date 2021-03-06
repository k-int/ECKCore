package com.k_int.euinside.core

import grails.converters.JSON;

import javax.servlet.http.HttpServletResponse;

class GatewayController {

	def AggregatorService;
	def ModulesService;

	private def processResponse(responseValue, module) {

		boolean rendered = false;	
		def content = responseValue.content;
		
		log.info("Returned: Status Code: " + responseValue.status.statusCode + ", reason phrase: " + responseValue.status.reasonPhrase);
		if (responseValue.status.statusCode == HttpServletResponse.SC_OK) {
			def contentType = responseValue.contentType;
			
			if ((contentType != null) && (responseValue.contentBytes != null)) {
				// if the content type is html, then we need to modify the urls contained in the html
				if (contentType.contains("html")) {
					String html = new String(responseValue.contentBytes, ((responseValue.charset == null) ? "UTF-8" : responseValue.charset));
					html = ModulesService.replacePathInHtml(module, html);
					render(text : html, contentType : contentType, encoding : "UTF-8");
				} else {
					// For everything else we juat pass back what we were supplied 
					response.contentType = contentType;
					response.outputStream << responseValue.contentBytes;
				}
				rendered = true;
			}
		}

		// If we have not rendered the response then just pass back the status code and the content of the body, assuming it is text 
		if (!rendered) {
			def statusCode = responseValue.status.statusCode;
			def contentString = ((responseValue.contentBytes == null) ? "" : new String(responseValue.contentBytes, "UTF-8"));
			render(status: statusCode, text: contentString, contentType: "text/plain", encoding: "UTF-8");
		}
	}

	def aggregatorGetRelay() {
		def responseValue = AggregatorService.execute(params, request);
		def JSONResponse = responseValue.JSONResponse;
		if (responseValue.JSONResponse == null) {
			processResponse(responseValue, null);
		} else {
			log.info("Mapped agrregator response to a generic response, returning JSON");
			render JSONResponse as JSON;
		}
	}
		
    def dataMappingGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_DATA_MAPPING, params, request);
		processResponse(responseValue, ModulesService.MODULE_DATA_MAPPING);
	}
	
    def dataMappingPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_DATA_MAPPING, params, request, true);
		processResponse(responseValue, ModulesService.MODULE_DATA_MAPPING);
	}
	
    def dataTransformationGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_DATA_TRANSFORMATION, params, request);
		processResponse(responseValue, ModulesService.MODULE_DATA_TRANSFORMATION);
	}
	
    def dataTransformationPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_DATA_TRANSFORMATION, params, request, true);
		processResponse(responseValue, ModulesService.MODULE_DATA_TRANSFORMATION);
	}
	
    def definitionGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_DEFINITION, params, request);
		processResponse(responseValue, ModulesService.MODULE_DEFINITION);
	}
	
    def definitionPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_DEFINITION, params, request);
		processResponse(responseValue, ModulesService.MODULE_DEFINITION);
	}
	
    def europeanaGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_EUROPEANA, params, request, true);
		processResponse(responseValue, ModulesService.MODULE_EUROPEANA);
	}
	
    def europeanaPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_EUROPEANA, params, request);
		processResponse(responseValue, ModulesService.MODULE_EUROPEANA);
	}
	
	def pidGenerateGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_PID_GENERATE, params, request);
		processResponse(responseValue, ModulesService.MODULE_PID_GENERATE);
	}
	
    def pidGeneratePostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_PID_GENERATE, params, request);
		processResponse(responseValue, ModulesService.MODULE_PID_GENERATE);
	}

	def previewGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_PREVIEW, params, request);
		processResponse(responseValue, ModulesService.MODULE_PREVIEW);
	}
	
    def previewPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_PREVIEW, params, request);
		processResponse(responseValue, ModulesService.MODULE_PREVIEW);
	}

	def setManagerGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_SET_MANAGER, params, request);
		processResponse(responseValue, ModulesService.MODULE_SET_MANAGER);
	}
	
    def setManagerPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_SET_MANAGER, params, request);
		processResponse(responseValue, ModulesService.MODULE_SET_MANAGER);
	}

	def statisticsGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_STATISTICS, params, request);
		processResponse(responseValue, ModulesService.MODULE_STATISTICS);
	}
	
    def statisticsPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_STATISTICS, params, request);
		processResponse(responseValue, ModulesService.MODULE_STATISTICS);
	}

	def validateGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_VALIDATE, params, request);
		processResponse(responseValue, ModulesService.MODULE_VALIDATE);
	}
	
    def validatePostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_VALIDATE, params, request, false);
		processResponse(responseValue, ModulesService.MODULE_VALIDATE);
	}

	def validate2GetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_VALIDATE2, params, request);
		processResponse(responseValue, ModulesService.MODULE_VALIDATE2);
	}
	
    def validate2PostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_VALIDATE2, params, request, true);
		processResponse(responseValue, ModulesService.MODULE_VALIDATE2);
	}
}
