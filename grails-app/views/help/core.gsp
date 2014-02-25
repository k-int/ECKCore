<% def ModulesService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("modulesService") %>
<!doctype html>
<html>
  	<head>
	    <meta name="layout" content="bootstrap"/>
	    <title>EUInside - Core</title>
  	</head>

  	<body>
    	<div class="row-fluid">
      		<section id="main">
        		<div class="hero-unit row">
          			<div class="page-header span12">
            			<h1>EUInside - Core</h1>
          			</div>
        		</div>
        
        		<div class="row">
          			<div class="span12">
            			<h4>Description</h4>
            			<p>The primary role of the core module is to act as a gateway for all the other modules, as a secondary role it will provide a generic interface for obtaining statistics from the Aggregators</p>
            			<p>The table below lists the modules / interfaces that core knows about and the path to access that module / interface</p>
            			<table>
            				<tr>
            					<th align="left">Module / Interface</th>
            					<th align="left">Path</th>
            					<th align="left">Example</th>
            				</tr>
	                		<tr>
	                			<td><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/help/aggregator">Aggregator</a></td>
	                			<td>Aggregator</td>
	                			<td>http://euinside.k-int.com/ECKCore2/Aggregator/20223/*/statistics/Europeana</td>
	                		</tr>
	            			<tr>
	            				<td><a href="http://www.heron-net.be/einside_test/dmt.php/DataMapping">Data Mapping</a></td>
	            				<td>DataMapping</td>
	            				<td>http://euinside.k-int.com/ECKCore2/DataMapping/Libis/my_tansfer/Transform</td>
	            			</tr>
	            			<tr>
	                			<td><a href="http://services.libis.be/euinside/mt.php/DataMapping">Data Transformation</a></td>
	                			<td>DataTransformation</td>
	            				<td>http://euinside.k-int.com/ECKCore2/DataTransformation/Libis/LeuvenLibData/Transform</td>
	            			</tr>
	            			<tr>
	                			<td><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getDefinitionModuleCode()}">Metadata Definition</a></td>
	                			<td>Definition</td>
	                			<td>http://euinside.k-int.com/ECKCore2/Definition/errors</td>
	                		</tr>
	                		<tr>
	                			<td><a href="http://euinside.semantika.si/pid/Generate">PID Generation</a></td>
	                			<td>PIDGenerate</td>
	                			<td>http://euinside.k-int.com/ECKCore2/PIDGenerate/generate</td>
	                		</tr>
	                		<tr>
	                			<td><a href="http://euinside.asp.monguz.hu/eck-preview-servlet/index.html">Preview</a></td>
	                			<td>Preview</td>
	                			<td>http://euinside.k-int.com/ECKCore2/Preview/chas/single/preview/lido</td>
	                		</tr>
	                		<tr>
	                			<td><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getSetManagerModuleCode()}">Set Manager</a></td>
	                			<td>SetManager</td>
	                			<td>http://euinside.k-int.com/ECKCore2/SetManager/Set/chas/second/update</td>
	                		</tr>
	                		<tr>
	                			<td><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getStatisticsModuleCode()}">Statistics</a></td>
	                			<td>Statistics</td>
	                			<td>http://euinside.k-int.com/ECKCore2/Statistics/setManager/commit/update</td>
	                		</tr>
	                		<tr>
	                			<td><a href="http://euinside.asp.monguz.hu/eck-validation-servlet/index.html">Validation (monguz)</a></td>
	                			<td>Validate</td>
	                			<td>http://euinside.k-int.com/ECKCore2/Validate/CultureGrid/single/validate/lido</td>
	                		</tr>
	                		<tr>
	                			<td><a href="http://euinside.semantika.si">Validation (semantika)</a></td>
	                			<td>Validate2</td>
	                			<td>http://euinside.k-int.com/ECKCore2/Validate2/CultureGrid/single/validate/lido</td>
	                		</tr>
            			</table>
            			<br/>
						<p>To use any of the modules through the core gateway, build up the URL as follows </p>
						<ul>
							<li>Start with the url for the ECKCore eg. http://euinside.k-int.com/ECKCore2</li>
							<li>Now append the path that is relevant for the module from the above table eg. http://euinside.k-int.com/ECKCore2/SetManager</li>
							<li>Now append the appropriate path from the module api document for what you want to do. eg. http://euinside.k-int.com/ECKCore2/SetManager/Set/default/default/status</li>
							<li>The url has now been built up, do not forget post / append any arguments / files that are required by the modules api</li>
						</ul> 
         			</div>
       			</div>
		    </section>
    	</div>

	</body>
</html>
