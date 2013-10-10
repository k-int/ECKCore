
// for each module the internalURL, internalPath, externalURL, externalPath and parameters can be overridden
// If you add your own aggregator then you will need to add a module to say how we communicate with it
// As an example the DataMapping module is defined as 
//	DataMapping {
//		internalURL = "http://www.heron-net.be"
//		internalPath = "/einside_test/dmt.php/DataMapping"
//		externalURL = " http://www.heron-net.be"
//		externalPath = "/einside_test/dmt.php/DataMapping"
//		parameters = ["sourceFormat",
//					  "targetFormat",
//					  "request_id"]
//	}
// Which says the address to the machine is http://www.heron-net.be and the path to the mapping module is /einside_test/dmt.php/DataMapping
// and that the mapping module can take 3 parameters sourceFormat, targetFormat and request_id
//
// If the module does not take any parameters then that parameters can be left out
//  

modules {
	Core {
	}
	DataMapping {
	}
	DataTransformation {
	}
	Definition {
	}
	Europeana {
	}
	PIDGenerate {
	}
	Preview {
	}
	SetManager {
	}
	Statistics {
	}
	Validate {
	}
	Validate2 {
	}
}

// Here you can define the communication with your own aggregator, if it is not provided out of the box
// As an example the Dark Aggregator is defined as 
//	DarkAggregator {
//		path = "/dpp/collection/statistics/\$(PROVIDER)/\$(COLLECTION)"
//		moduleName = "DarkAggregator"
//	}
// Which says that the module to be used is DarkAggregator, your module is very unlikely to be defined by default,
// so you will need to add a module with the same name in the modules section above so it knows how to communicate with your aggregator
// The path is the url path that will be appended to that specified in the module section, there are 2 important keywords here
// \$(PROVIDER) which will be replaced by the provider code specified in the url and
// \$(COLLECTION) which will be replace by the collection specified in the url
//
// As another example you can also specify parameters to be added to the url (the bit after the question mark in the url), the following is what is used for Europeanea 
//Europeana {
//	path = "datasets/provider_id.json"
//	moduleName = "Europeana"
//	Parameters {
//		id = "\$(PROVIDER)"
//		wskey = "XXX"
//	}
//}
// Here we ignore the collection code as this is not required, but we need to specify 2 parameters id which we set to the provider code and the wskey which is your europeana api key
// 
aggregators {
}
