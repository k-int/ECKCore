<% def ModulesService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("modulesService") %>
<!doctype html>
<html>
  	<head>
    	<meta name="layout" content="bootstrap"/>
    	<title>ECKCore home</title>
  	</head>

  	<body>
    	<div class="row-fluid">

	      	<section id="main">
	
	        	<div class="hero-unit row">
	          		<div class="page-header span12">
	            		<h1>Welcome to the ECK Core (iteration 2) prototype system</h1>
	        	  	</div>
	          		<p>The gateway for the modules used by ECK</p>
	        	</div>
	        
	        	<div class="row">
	          		<div class="span12">
	            		<h2>Available Modules</h2>
	            		<ul>
	                		<li><g:link base="http://www.heron-net.be/einside_test" controller="dmt.php" action="DataMapping">Data Mapping (libis)</g:link> <a href="/ECKCore/static/docs/ManualDataMappingTransformationService.doc">Download api</a></li>
	                		<li><g:link base="http://services.libis.be/euinside" controller="mt.php" action="DataMapping">Data Transformation (libis)</g:link> <a href="/ECKCore/static/docs/ManualDataMappingTransformationService.doc">Download api</a></li>
	                		<li><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getDefinitionModuleCode()}">Metadata Definition (k-int)</g:link> Module home page describes api</li>
	                		<li>Europeana Statistics (part of the Europeana API, the features we make use are datasets and providers)</li>
	                		<li><g:link base="http://euinside.semantika.si" controller="pid" action="Generate">PID Generation (semantika)</g:link> <a href="/ECKCore/static/docs/Semantika_EU_Inside_PID_Generation_WS.pdf">Download api</a></li>
	                		<li><g:link base="http://euinside.asp.monguz.hu" controller="eck-preview-servlet" action="index.html">Preview (monguz)</g:link><a href="/ECKCore/static/docs/preview-rest-api-MON.pdf">Download api</a></li>
	                		<li><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getSetManagerModuleCode()}">Set Manager (k-int)</g:link> Module home page describes api</li>
	                		<li><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getStatisticsModuleCode()}">Statistics (k-int)</g:link> Module home page describes api</li>
	                		<li><g:link base="http://euinside.asp.monguz.hu" controller="eck-validation-servlet" action ="index.html">Validation (monguz)</g:link> <a href="/ECKCore/static/docs/validation-rest-api-common.pdf">Download api</a></li>
	                		<li><g:link base="http://euinside.semantika.si">Validation (semantika)</g:link> <a href="/ECKCore/static/docs/validation-rest-api-common.pdf">Download api</a></li>
	            		</ul>
	          		</div>
	            Documentation for the Java 7 Client for the ECKCore can be found <g:link base="http://euinside.k-int.com/ECKClient/apidocs">here</g:link>
		        </div>
	    	</section>
    	</div>
  	</body>
</html>
