class UrlMappings {

	static mappings = {
		"/DataTransformation/$path**" {
			controller = "gateway"
			action = [GET : "dataTransformationGetRelay", POST : "dataTransformationPostRelay"]
		}
		name definition : "/Definition" {
			controller = "gateway"
			action = [GET : "definitionGetRelay", POST : "definitionPostRelay"]
		}
		"/Definition/$path**" {
			controller = "gateway"
			action = [GET : "definitionGetRelay", POST : "definitionPostRelay"]
		}
		"/Europeana/$path**" {
			controller = "gateway"
			action = [GET : "europeanaGetRelay", POST : "europeanaPostRelay"]
		}
		"/PIDGenerate/$path**" {
			controller = "gateway"
			action = [GET : "pidGenerateGetRelay", POST : "pidGeneratePostRelay"]
		}
		"/Preview/$path**" {
			controller = "gateway"
			action = [GET : "previewGetRelay", POST : "previewPostRelay"]
		}
		name setManager : "/SetManager" {
			controller = "gateway"
			action = [GET : "setManagerGetRelay", POST : "setManagerPostRelay"]
		}
		"/SetManager/$path**" {
			controller = "gateway"
			action = [GET : "setManagerGetRelay", POST : "setManagerPostRelay"]
		}
		"/Statistics/$path**" {
			controller = "gateway"
			action = [GET : "statisticsGetRelay", POST : "statisticsPostRelay"]
		}
		"/Validation/$path**" {
			controller = "gateway"
			action = [GET : "validateGetRelay", POST : "validatePostRelay"]
		}
		"/Validation2/$path**" {
			controller = "gateway"
			action = [GET : "validate2GetRelay", POST : "validate2PostRelay"]
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
