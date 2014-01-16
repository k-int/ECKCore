<% def ModulesService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("modulesService") %>
<!doctype html>
<html>
  	<head>
    	<meta name="layout" content="bootstrap"/>
		<link rel="stylesheet" href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/static/css/index.css" type="text/css">
    	<title>ECKCore - Aggregator</title>
  	</head>

  	<body>
    	<div class="row-fluid">

	      	<section id="main">
	
	        	<div class="hero-unit row">
	          		<div class="page-header span12">
	            		<h1>ECKCore - Aggregator</h1>
	          		</div>
	        	</div>
	        
	        	<div class="row">
	          		<div class="span12">
	            		<h4>Description</h4>
	            		<p>The Aggregator interface provides a way of talking to the aggregators through the ECKCore.</p>
	            		<br/>

	            		<h4>Invocation</h4>
						<p>The url for the interface takes the form <b>/&lt;aggregator&gt;/&lt;action&gt;/&lt;urlParameter1&gt;/&lt;urlParameter2&gt;?parameters</b> Where:</p>

						<table class="parameters">
							<tr>
								<th>URL Part</th>
								<th align="left">Description</th>
							</tr>
							<tr>
								<td>aggregator</td>
								<td>The code for the aggregator that you want to retrieve the statistics from (eg. DarkAggregator, CultureGridTest, CultureGridLive, Europeana, SetManager, ...)</td>
							</tr>
							<tr>
								<td>action</td>
								<td>The action to be performed against the aggregator</td>
							</tr>
							<tr>
								<td>urlParameter1</td>
								<td>The purpose of this parameter varies depending on the action being performed, see the action for more details</td>
							</tr>
							<tr>
								<td>urlParameter2</td>
								<td>The purpose of this parameter varies depending on the action being performed, see the action for more details</td>
							</tr>
						</table>
						<br/>
						
						<p>The possible actions are:</p>
						<ul>
							<li><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/help/enrichmentRecord">enrichmentRecord</a></li>
							<li><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/help/search">search</a></li>
							<li><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/help/statistics">statistics</a></li>
                        </ul>
                        
	            		<p>Note: Not all aggregators support all actions, when the aggregator does not support the action or support has not been added for an aggregator the http status 400 will be returned with an explanatory message</p>
	            		<br/>

	            		<h4>Parameters</h4>
						<p>There is one general parameter that the ECKCore intercepts and takes notice off and that is rawRequired, if this is set to yes then the raw data returned from the aggregator will be passed back to the caller, if it is not set or not set to yes then we will not map the data to the generic format unless xml is being returned form the server.</p>
								
	            		<h4>Response</h4>
	            		<p>The data for all actions is returned as json</p>
	            
	            		<h4>Testing</h4>
	            		<p>A simple test form is available from each of the action pages</p>
	          		</div>
	        	</div>
	      	</section>
    	</div>
	</body>
</html>
