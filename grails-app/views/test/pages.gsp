<% def ModulesService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("modulesService") %>
<!doctype html>
<html>
  	<head>
    	<meta name="layout" content="bootstrap"/>
    	<title>ECKCore Test Pages</title>
  	</head>

  	<body>
    	<div class="row-fluid">

	      	<section id="main">
	
	        	<div class="hero-unit row">
	          		<div class="page-header span12">
	            		<h1>ECK Test Pages</h1>
	        	  	</div>
	        	</div>
	        
	        	<div class="row">
	          		<div class="span12">
	          			<ul>
                			<li><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}" controller ="Test" action="enrichmentRecord">Core - Enrichment</g:link></li>
	                		<li><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}" controller ="Test" action="statistics">Core - Aggregator Statistics</g:link></li>
                			<li><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getDefinitionModuleCode()}/test/index">Metadata Definition</a></li>
                			<li><a href="http://euinside.asp.monguz.hu/eck-preview-servlet/index.html">Preview</a></li>
                			<li><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getSetManagerModuleCode()}/Set/default/default/test">Set Manager</a></li>
                			<li><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getStatisticsModuleCode()}/default/default/test">Statistics</a></li>
	                		<li><a href="http://euinside.asp.monguz.hu/eck-validation-servlet/index.html">Validation - monguz</a></li>
                			<li><a href="http://euinside.semantika.si">Validation - semantika</a></li>
	            		</ul>
	          		</div>
		        </div>
	    	</section>
    	</div>
  	</body>
</html>
