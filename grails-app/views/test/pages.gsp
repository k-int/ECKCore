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
	                		<li><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}" controller ="Test" action="aggregator">9. Core - Aggregator Statistics</g:link></li>
                			<li><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}" controller ="Test" action="europeana">9. Core - Europeana</g:link></li>
                			<li><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getDefinitionModuleCode()}/test/index"> 3. Metadata Definition</a></li>
                			<li><a href="http://euinside.asp.monguz.hu/eck-preview-servlet/index.html">6. Preview</a></li>
                			<li><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getSetManagerModuleCode()}/Set/default/default/test">8/11. Set Manager</a></li>
                			<li><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getStatisticsModuleCode()}/default/default/test">9. Statistics</a></li>
	                		<li><a href="http://euinside.asp.monguz.hu/eck-validation-servlet/index.html">7. Validation - monguz</a></li>
                			<li><a href="http://euinside.semantika.si">7. Validation - semantika</a></li>
	            		</ul>
	          		</div>
		        </div>
	    	</section>
    	</div>
  	</body>
</html>
