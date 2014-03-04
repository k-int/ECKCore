grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolver = "maven"
//grails.server.port.http = 28090

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

		mavenRepo "http://snapshots.repository.codehaus.org/"
		
        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
		mavenRepo "https://oss.sonatype.org/content/repositories/releases"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

		compile 'commons-io:commons-io:2.4'
		compile "net.sf.ehcache:ehcache-core:2.4.6"
		compile 'org.apache.httpcomponents:httpmime:4.2.5'

		// We do not want the groovy file being pulled in, as it includes the wrong version
		compile ('org.codehaus.groovy.modules.http-builder:http-builder:0.6') {
			excludes 'groovy'
		}

		compile "com.k-int.EUInside:ECKClient:0.3-SNAPSHOT"
    }

    plugins {
        runtime ":jquery:1.8.3"
        runtime ":jquery-ui:1.8.24"
        runtime ":resources:1.2"
		runtime ":twitter-bootstrap:2.3.0"
		runtime ":webxml:1.4.1"
		
        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        compile ':cache:1.1.1'
        build ":tomcat:7.0.50"
    }
}
