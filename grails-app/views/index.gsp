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
	            		<table>
	            			<tr>
	            				<th align="left">Module</th>
	            				<th align="left">Partner</th>
	            				<th align="left">API Docs.</th>
	            				<th align="left">Base Rest URL</th>
	            			</tr>
	                		<tr>
	                			<td><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/help/core">Core</g:link></td>
	                			<td>k-int</td>
	                			<td><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/help/core">api</g:link></td>
	                			<td>http://euinside.k-int.com/ECKCore2</td>
	                		</tr>
	            			<tr>
	            				<td><g:link base="http://www.heron-net.be/einside_test" controller="dmt.php" action="DataMapping">Data Mapping</g:link></td>
	            				<td>Libis</td>
	            				<td><a href="/ECKCore/static/docs/ManualDataMappingTransformationService.doc">api</a></td>
	            				<td>http://www.heron-net.be/einside_test/dmt.php/DataMapping</td>
	            			</tr>
	            			<tr>
	                			<td><g:link base="http://services.libis.be/euinside" controller="mt.php" action="DataMapping">Data Transformation</g:link></td>
	                			<td>Libis</td>
	                			<td><a href="/ECKCore/static/docs/ManualDataMappingTransformationService.doc">api</a></td>
	                			<td>http://services.libis.be/euInside/dmt.php/DataMapping</td>
	            			</tr>
	            			<tr>
	                			<td><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getDefinitionModuleCode()}">Metadata Definition</g:link></td>
	                			<td>k-int</td>
	                			<td><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getDefinitionModuleCode()}">api</g:link></td>
	                			<td>http://euinside.k-int.com/ECKDefinition</td>
	                		</tr>
	                		<tr>
	                			<td>Europeana Statistics (datasets)</td>
	                			<td>Europeana</td>
	                			<td><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/help/europeana">api</g:link></td>
	                			<td>http://testenv-solr.eanadev.org:9191/api/v2 (test, as it is not live yet)</td>
	                		</tr>
	                		<tr>
	                			<td><g:link base="http://euinside.semantika.si" controller="pid" action="Generate">PID Generation</g:link></td>
	                			<td>Semantika</td>
	                			<td><a href="/ECKCore/static/docs/Semantika_EU_Inside_PID_Generation_WS.pdf">api</a></td>
	                			<td>http://euinside.semantika.si/pid</td>
	                		</tr>
	                		<tr>
	                			<td><g:link base="http://euinside.asp.monguz.hu" controller="eck-preview-servlet" action="index.html">Preview</g:link></td>
	                			<td>Monguz</td>
	                			<td><a href="/ECKCore/static/docs/preview-rest-api-MON.pdf">api</a></td>
	                			<td>http://euinside.asp.monguz.hu/eck-preview-module/Preview</td>
	                		</tr>
	                		<tr>
	                			<td><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getSetManagerModuleCode()}">Set Manager</g:link></td>
	                			<td>k-int</td>
	                			<td><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getSetManagerModuleCode()}">api</g:link></td>
	                			<td>http://euinside.k-int.com/ECKSetManager</td>
	                		</tr>
	                		<tr>
	                			<td><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getStatisticsModuleCode()}">Statistics</g:link></td>
	                			<td>k-int</td>
	                			<td><g:link base="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getStatisticsModuleCode()}">api</g:link></td>
	                			<td>http://euinside.k-int.com/ECKStatistics</td>
	                		</tr>
	                		<tr>
	                			<td><g:link base="http://euinside.asp.monguz.hu" controller="eck-validation-servlet" action ="index.html">Validation</g:link></td>
	                			<td>Monguz</td>
	                			<td><a href="/ECKCore/static/docs/validation-rest-api-common.pdf">api</a></td>
	                			<td>http://euinside.asp.monguz.hu/eck-validation-module/Validation</td>
	                		</tr>
	                		<tr>
	                			<td><g:link base="http://euinside.semantika.si">Validation</g:link></td>
	                			<td>Semantika</td>
	                			<td><a href="/ECKCore/static/docs/validation-rest-api-common.pdf">api</a></td>
	                			<td>http://euinside.semantika.si/Validation</td>
	                		</tr>
	            		</table>
	          		<br/>
	          		</div>
	            Documentation for the Java 7 Client for the ECKCore can be found <g:link base="http://euinside.k-int.com/ECKClient/apidocs">here</g:link>
		        </div>
	    	</section>
    	</div>
  	</body>
</html>
