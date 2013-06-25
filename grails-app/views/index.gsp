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
	                		<li><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getDefinitionModuleCode()}/">Metadata Definition</g:link></li>
	                		<li><g:link base="http://euinside.semantika.si/pid/Generate">PID Generation</g:link></li>
	                		<li><g:link base="http://app.asp.hunteka.hu:5080/eck-preview-servlet/index.html">Preview</g:link></li>
	                		<li><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getSetManagerModuleCode()}/">Set Manager</g:link></li>
	                		<li><g:link base="http://app.asp.hunteka.hu:5080/eck-validation-servlet/index.html">Validation (monguz)</g:link></li>
	                		<li><g:link base="http://euinside.semantika.si/">Validation (semantika)</g:link></li>
	            		</ul>
	          		</div>
	            Documentation for the Java 7 Client for the ECKCore can be found <g:link base="http://euinside.k-int.com/ECKClient/apidocs">here</g:link>
		        </div>
	    	</section>
    	</div>
  	</body>
</html>
