// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

grails.config.locations = ["classpath:${appName}-config.groovy",
                           "file:./${appName}-config.groovy",
                           "file:${userHome}/.grails/${appName}-config.groovy"]

if (System.properties["${appName}.config.location"]) {
    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
}

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*', '/docs/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        grails.serverURL = "http://euinside.k-int.com/ECKCore"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
		   
	debug  'grails.app.config',
           'grails.app.controllers',
           'grails.app.services',
           'grails.app.domain',
           'grails.app.jobs'
}

// The default module configuration
// The urls and paths can be overridden in the user configuration 
moduleConfiguration {
	Core {
		internalURL = "http://localhost:8080"
		internalPath = "/ECKCore"
		externalURL = "http://localhost:8080"
		externalPath = "/ECKCore"
	}
	CultureGridLive {
		internalURL = "http://www.culturegrid.org.uk"
		internalPath = ""
		externalURL = "http://www.culturegrid.org.uk"
		externalPath = ""
		parameters = ["provider",
					  "providerResourceId"]
	}
	CultureGridTest {
		internalURL = "http://testing.culturegrid.org.uk"
		internalPath = ""
		externalURL = "http://testing.culturegrid.org.uk"
		externalPath = ""
		parameters = ["provider",
					  "providerResourceId"]
	}
	DarkAggregator {
		internalURL = "http://euinside.k-int.com"
		internalPath = ""
		externalURL = "http://euinside.k-int.com"
		externalPath = ""
		parameters = ["provider",
					  "providerResourceId"]
	}
	DataMapping {
		internalURL = "http://www.heron-net.be"
		internalPath = "/einside_test/dmt.php/DataMapping"
		externalURL = " http://www.heron-net.be"
		externalPath = "/einside_test/dmt.php/DataMapping"
		parameters = ["sourceFormat",
					  "targetFormat",
					  "request_id"]
	}
	DataTransformation {
		internalURL = "http://services.libis.be"
		internalPath = "/euInside/dmt.php/DataMapping"
		externalURL = "http://services.libis.be"
		externalPath = "/euInside/dmt.php/DataMapping"
		parameters = ["mappingRules",
					  "sourceFormat",
					  "targetFormat",
					  "request_id"]
	}
	Definition {
		internalURL = "http://localhost:28081"
		internalPath = "/ECKDefinition"
		externalURL = "http://localhost:28081"
		externalPath = "/ECKDefinition"
	}
	Europeana {
		internalURL = "http://europeana.eu"
		internalPath = "/api/v2"
		externalURL = "http://europeana.eu"
		externalPath = "/api/v2"
		parameters = ["countryCode",
					  "id",
					  "offset",
					  "pagesize",
					  "profile",
					  "query",
					  "rows",
					  "start",
					  "wskey"]
	}
	PIDGenerate {
		internalURL = "http://euinside.semantika.si"
		internalPath = "/pid"
		externalURL = "http://euinside.semantika.si"
		externalPath = "/pid"
		parameters = ["accessionNumber",
					  "institutionUrl",
					  "recordType"]
	}
	Preview {
		internalURL = "http://euinside.asp.monguz.hu"
		internalPath = "/eck-preview-module/Preview"
		externalURL = "http://euinside.asp.monguz.hu"
		externalPath = "/eck-preview-module/Preview"
	}
	SetManager {
		internalURL = "http://localhost:28082"
		internalPath = "/ECKSetManager"
		externalURL = "http://localhost:28082"
		externalPath = "/ECKSetManager"
		parameters = ["delete",
					  "deleteAll",
					  "from",
					  "historyItems",
					  "identifier",
					  "metadataPrefix",
					  "resumptionToken",
					  "recordId",
					  "set",
					  "setDescription",
					  "statisticsDetails",
					  "until",
					  "verb"]
	}
	Statistics {
		internalURL = "http://localhost:28085"
		internalPath = "/ECKStatistics"
		externalURL = "http://localhost:28085"
		externalPath = "/ECKStatistics"
		parameters =["dateTime",
				     "days",
					 "duration",
					 "itemsProcessed",
					 "limit",
					 "numberFailed",
					 "numberSuccessful",
					 "offset",
					 "queryType"]
	}
	Validate {
		internalURL = "http://euinside.asp.monguz.hu"
		internalPath = "/eck-validation-module/Validation"
		externalURL = "http://euinside.asp.monguz.hu"
		externalPath = "/eck-validation-module/Validation"
	}
	Validate2 {
		internalURL = "http://euinside.semantika.si"
		internalPath = "/Validation"
		externalURL = "http://euinside.semantika.si"
		externalPath = "/Validation"
	}
}

aggregatorConfiguration {
	CultureGridLive {
		moduleName = "CultureGridLive"
		actions {
			enrichmentRecord {
				path = "dpp/resource/\$(RECORD_ID)/stream/EuropeanaEnrichment"
				xmlResponse = true
				parser = "com.k_int.euinside.client.module.aggregator.europeana.EuropeanaEnrichments"
				parameters {
					provider = "\$(PROVIDER_OR_SET_ID)"
				}
				mapParameters {
					providerResourceId = "lidoRecID"
				}
				defaultReplacements {
					RECORD_ID = "*"
				}
			}
			statistics {
				path = "dpp/collection/statistics/\$(PROVIDER)/\$(COLLECTION)"
				parser = "com.k_int.euinside.client.module.aggregator.cultureGrid.CultureGridStatistic"
			}
		}
	}
	CultureGridTest {
		moduleName = "CultureGridTest"
		actions {
			enrichmentRecord {
				path = "dpp/resource/\$(RECORD_ID)/stream/EuropeanaEnrichment"
				xmlResponse = true
				parser = "com.k_int.euinside.client.module.aggregator.europeana.EuropeanaEnrichments"
				parameters {
					provider = "\$(PROVIDER_OR_SET_ID)"
				}
				mapParameters {
					providerResourceId = "lidoRecID"
				}
				defaultReplacements {
					RECORD_ID = "*"
				}
			}
			statistics {
				path = "dpp/collection/statistics/\$(PROVIDER)/\$(COLLECTION)"
				parser = "com.k_int.euinside.client.module.aggregator.cultureGrid.CultureGridStatistic"
			}
		}
	}
	DarkAggregator {
		moduleName = "DarkAggregator"
		actions {
			enrichmentRecord {
				path = "dpp/resource/\$(RECORD_ID)/stream/EuropeanaEnrichment"
				xmlResponse = true
				parser = "com.k_int.euinside.client.module.aggregator.europeana.EuropeanaEnrichments"
				parameters {
					provider = "\$(PROVIDER_OR_SET_ID)"
				}
				mapParameters {
					providerResourceId = "lidoRecID"
				}
				defaultReplacements {
					RECORD_ID = "*"
				}
			}
			statistics {
				path = "dpp/collection/statistics/\$(PROVIDER)/\$(COLLECTION)"
				parser = "com.k_int.euinside.client.module.aggregator.cultureGrid.CultureGridStatistic"
			}
		}
	}
	Europeana {
		moduleName = "Europeana"
		actions {
			enrichmentRecord {
				path = "record/\$(SET_ID)/\$(RECORD_ID).json"
				parser = "com.k_int.euinside.client.module.aggregator.europeana.EuropeanaRecordEnrichment"
				defaultParameters {
					profile = "full"
					wskey = "t2nkfwUNA"
				}
			}
			search {
				path = "search.json"
				parser = "com.k_int.euinside.client.module.aggregator.europeana.EuropeanaSearchResult"
				parameters {
					query = "europeana_collectionName:\"\$(COLLECTION)\""
				}
				defaultParameters {
					profile = "minimal"
					rows = "50"
					start = "1"
					wskey = "t2nkfwUNA"
				}
			}
			statistics {
				path = "datasets/provider_id.json"
				parser = "com.k_int.euinside.client.module.aggregator.europeana.EuropeanaDataSetResult"
				parameters {
					id = "\$(PROVIDER)"
				}
				defaultParameters {
					wskey = "t2nkfwUNA"
				}
			}
		}
	}
	SetManager {
		moduleName = "SetManager"
		actions {
			statistics {
				path = "Set/\$(PROVIDER)/\$(COLLECTION)/statistics"
				parser = "com.k_int.euinside.client.module.setmanager.statistics.Statistic"
			}
		}
	}
}

// Uncomment and edit the following lines to start using Grails encoding & escaping improvements

/* remove this line 
// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside null
                scriptlet = 'none' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
remove this line */
