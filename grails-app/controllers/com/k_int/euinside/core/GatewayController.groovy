package com.k_int.euinside.core

import javax.servlet.http.HttpServletResponse;

class GatewayController {

	def ModulesService;

	private def processResponse(responseValue, module) {

		boolean rendered = false;	
		def content = responseValue.content;
		
		log.info("Returned: Status Code: " + responseValue.status.statusCode + ", reason phrase: " + responseValue.status.reasonPhrase);
		if (responseValue.status.statusCode == HttpServletResponse.SC_OK) {
			def contentType = responseValue.contentType;
			
			if (contentType != null) {
				// We have disabled the automatic parsing, so everything should come back as a string ...
				// If we ever deal with binary objects, then this will probably need to change ...
				// if the content type is html, then we need to modify the urls contained in the html
				String html = responseValue.contentString;
				if (contentType.contains("html")) {
					html = ModulesService.replacePathInHtml(module, html);
				} 
				render(text : html, contentType : contentType, encoding : "UTF-8");
				rendered = true;
			}
		}

		// If we have not rendered the response then just pass back the status code and the content of the body 
		if (!rendered) {
			def statusCode = responseValue.status.statusCode;
			render(status: statusCode, text: responseValue.contentString, contentType: "text/plain", encoding: "UTF-8");
		}
	}
	
    def definitionGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_DEFINITION, params, request);
		processResponse(responseValue, ModulesService.MODULE_DEFINITION);
	}
	
    def definitionPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_DEFINITION, params, request);
		processResponse(responseValue, ModulesService.MODULE_DEFINITION);
	}
	
    def persistenceGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_PERSISTENCE, params, request);
		processResponse(responseValue, ModulesService.MODULE_PERSISTENCE);
	}
	
    def persistencePostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_PERSISTENCE, params, request);
		processResponse(responseValue, ModulesService.MODULE_PERSISTENCE);
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
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_VALIDATE, params, request);
		processResponse(responseValue, ModulesService.MODULE_VALIDATE);
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

	def validateGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_VALIDATE, params, request);
		processResponse(responseValue, ModulesService.MODULE_VALIDATE);
	}
	
    def validatePostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_VALIDATE, params, request);
		processResponse(responseValue, ModulesService.MODULE_VALIDATE);
	}

	def validate2GetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_VALIDATE2, params, request);
		processResponse(responseValue, ModulesService.MODULE_VALIDATE2);
	}
	
    def validate2PostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_VALIDATE2, params, request);
		processResponse(responseValue, ModulesService.MODULE_VALIDATE2);
	}
}
