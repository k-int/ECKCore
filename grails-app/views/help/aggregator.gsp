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
	            		<p>The Aggregator interface provides a way of obtaining statistics for your collection form the different aggregators.</p>
	            		<p>The information returned at the moment is a straight copy from what is returned by the called aggregator,
	            		   it is planned to normalise the results from the different aggregators, so that all the it shouldn't matter what aggregator you want the results from the results will be the same</p>
	            		<h4>Invocation</h4>
						<p>The url for the interface takes the form <b>/&lt;provider&gt;/&lt;collection&gt;/&lt;action&gt;/&lt;aggregator&gt;?parameters</b> Where:</p>

						<table class="parameters">
							<tr>
								<th>URL Part</th>
								<th align="left">Description</th>
							</tr>
							<tr>
								<td>provider</td>
								<td>Is the code for the provider of the data, if it is not required by the aggregator then use *</td>
							</tr>
							<tr>
								<td>collection</td>
								<td>Is the code for the collection that the action is to be performed on</td>
							</tr>
							<tr>
								<td>action</td>
								<td>The action to be performed on the set, there is only one action and that is statistics</td>
							</tr>
							<tr>
								<td>aggregator</td>
								<td>The code for the aggregator that you want to retrieve the statistics from (eg. DarkAggregator, CultureGridTest, CultureGridLive, Europeana, SetManager, ...)</td>
							</tr>
						</table>
						<br/>
						
	            		<h4>Parameters</h4>
						<p>There is one parameter that the ECKCore intercepts and takes notice off and that is rawRequired, if this is set to yes then the raw data returned from the aggregator will be passed back to the caller, if it is not set or not set to yes then if we know how to map the data to the Generic Statistics format then this will happen, otherwise the raw format will be returned.</p>
								
	            		<h4>Response</h4>
	            		<p>The Generic Statistics is returned in json, an example response is</p>
	            		<pre id="successResponse" name="successResponse"></pre>
	            
	            		<h4>Testing</h4>
	            		<p>A simple test form for the Aggregator interface is available <a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/Test/aggregator">here</a></p>
	          		</div>
	        	</div>
	      	</section>
    	</div>
    	
		<script src="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/static/js/json_syntax.js" type="text/javascript"></script>
	    <script type="text/javascript">
		    $(document).ready(function (){
		    	var genericStatisticsResponse = {"pending": 2,"rejected": 1,"providerCode": "DEFAULT","total": 9,"europeanaDataSets": [{"status": "Ingestion complete","identifier": "09405c","publishedRecords": 1773,"name": "09405c_Ag_UK_ELocal_LDGEF"}],"accepted": 8,"collectionCode": "DEFAULT","description": "Auto generated with code: DEFAULT"};
        		$("#successResponse").html(JSONSyntaxHighlight(genericStatisticsResponse));
    		});
    
    	</script>
	</body>
</html>
