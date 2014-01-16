<% def ModulesService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("modulesService") %>
<!doctype html>
<html>
  	<head>
    	<meta name="layout" content="bootstrap"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}" type="text/css">
    	<title>Aggregator - Statistics</title>
  	</head>

  	<body>
    	<div class="row-fluid">

	      	<section id="main">
	
	        	<div class="hero-unit row">
	          		<div class="page-header span12">
	            		<h1>Aggregator - Statistics</h1>
	          		</div>
	        	</div>
	        
	        	<div class="row">
	          		<div class="span12">
	          			<h4>Description</h4>
	          			<p> Provides statistics for the required collection from the specified aggregator</p>

	            		<h4>Invocation</h4>
						<p>The url for the interface takes the form <b>/&lt;aggregator&gt;/statistics/&lt;provider&gt;/&lt;collection&gt;</b> Where:</p>

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
								<td> Is the code for the collection that the action is to be performed on</td>
							</tr>
						</table>

	            		<h4>Response</h4>
	            		<p>The response is in json</p>
	            		<pre id="successResponse" name="successResponse"></pre>
	            
	            		<h4>Testing</h4>
	            		<p>A simple test form for the Aggregator statistics action is available <a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/Test/statistics">here</a></p>
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
