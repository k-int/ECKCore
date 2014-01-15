package com.k_int.euinside.core

class LoggingUtilitiesService {

	private def INDENT_STEP = "    ";
		
	def logObject(descriptionText, object) {
		log.debug(descriptionText);
		logObjectIndent(object, "");
	}
	
	private def logObjectIndent(object, indentation) {
		if (object instanceof Map) {
			logObjectMap(object, indentation);
		} else if (object instanceof List) {
			logObjectList(object, indentation);
		} else {
			log.debug(indentation + "\"" + object + "\"");
		}
	}
	
	private def logObjectMap(object, indentation) {
		object.each() {
			log.debug(indentation + it.key);
			logObjectIndent(it.value, indentation + INDENT_STEP);
		}
	}
	
	private def logObjectList(object, indentation) {
		object.each() {
			logObjectIndent(it, indentation + INDENT_STEP);
		}
	}
}
