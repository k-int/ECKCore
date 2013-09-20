// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

grails.config.locations = [ "classpath:${appName}-config.properties",
                             "classpath:${appName}-config.groovy",
                             "file:./${appName}-config.properties",
                             "file:./${appName}-config.groovy",
                             "file:${userHome}/.grails/${appName}-config.properties",
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
	DataTransformation {
		internalURL = "http://services.libis.be"
		internalPath = "/euInside/dmt.php/DataMapping"
		externalURL = "http://services.libis.be"
		externalPath = "/euInside/dmt.php/DataMapping"
		parameters {
			mappingRules = ""
			sourceFormat = ""
			targetFormat = ""
			request_id = ""
		}
	}
	Definition {
		internalURL = "http://localhost:28081"
		internalPath = "/ECKDefinition"
		externalURL = "http://localhost:28081"
		externalPath = "/ECKDefinition"
	}
	// Note: Real url for Europeana is http://europeana.eu/api/v2
	Europeana {
		internalURL = "http://testenv-solr.eanadev.org:9191"
		internalPath = "/api2"
		externalURL = "http://testenv-solr.eanadev.org:9191"
		externalPath = "/api2"
	}
	PIDGenerate {
		internalURL = "http://euinside.semantika.si"
		internalPath = "/pid"
		externalURL = "http://euinside.semantika.si"
		externalPath = "/pid"
		parameters {
			accessionNumber = ""
			institutionUrl = ""
			recordType = ""
		}
	}
	Preview {
		internalURL = "http://app.asp.hunteka.hu:5080"
		internalPath = "/eck-preview-module/templates"
		externalURL = "http://app.asp.hunteka.hu:5080"
		externalPath = "/eck-preview-module/templates"
	}
	SetManager {
		internalURL = "http://localhost:28082"
		internalPath = "/ECKSetManager"
		externalURL = "http://localhost:28082"
		externalPath = "/ECKSetManager"
		parameters {
			delete = ""
			deleteAll = ""
			historyItems = ""
			recordId = ""
			setDescription = ""
			statisticsDetails = ""
		}
	}
	Statistics {
		internalURL = "http://localhost:28085"
		internalPath = "/ECKStatistics"
		externalURL = "http://localhost:28085"
		externalPath = "/ECKStatistics"
		parameters {
			dateTime = ""
			days = ""
			duration = ""
			itemsProcessed = ""
			limit = ""
			numberFailed = ""
			numberSuccessful = ""
			offset = ""
			queryType = ""
		}
	}
	Validate {
		internalURL = "http://app.asp.hunteka.hu:5080"
		internalPath = "/eck-validation-module/Validation"
		externalURL = "http://app.asp.hunteka.hu:5080"
		externalPath = "/eck-validation-module/Validation"
	}
	Validate2 {
		internalURL = "http://euinside.semantika.si"
		internalPath = "/Validation"
		externalURL = "http://euinside.semantika.si"
		externalPath = "/Validation"
	}
}
