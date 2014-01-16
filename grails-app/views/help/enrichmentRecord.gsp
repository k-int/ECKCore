<% def ModulesService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("modulesService") %>
<!doctype html>
<html>
  	<head>
    	<meta name="layout" content="bootstrap"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}" type="text/css">
    	<title>Aggregator - EnrichmentRecord</title>
  	</head>

  	<body>
    	<div class="row-fluid">

	      	<section id="main">
	
	        	<div class="hero-unit row">
	          		<div class="page-header span12">
	            		<h1>Aggregator - EnrichmentRecord</h1>
	          		</div>
	        	</div>
	        
	        	<div class="row">
	          		<div class="span12">
	          			<h4>Description</h4>
	          			<p>Retrieves the europeana enriched record from the specified aggregator</p>

	            		<h4>Invocation</h4>
						<p>The url for the interface has the following 2 forms:
							<b>
								<ul>
									<li>/&lt;aggregator&gt;/enrichmentRecord/&lt;set&gt;/&lt;recordId&gt;</li>
									<li>/&lt;aggregator&gt;/enrichmentRecord/&lt;provider&gt;?lidoRecID=&lt;lidoRecID&gt; (This is not supported by europeana)</li>
								</ul>
							</b>
							Where
						</p>

						<table class="parameters">
							<tr>
								<th>URL Part</th>
								<th align="left">Description</th>
							</tr>
							<tr>
								<td>set</td>
								<td>Is the aggregators identifier for the set that the record belongs to, this is the same as the provider for CultureGrid and the Dark Aggregator</td>
							</tr>
							<tr>
								<td>recordId</td>
								<td>Is the aggregators identifier for the record</td>
							</tr>
							<tr>
								<td>provider</td>
								<td>Is the aggregators code for the provider of the data</td>
							</tr>
							<tr>
								<td>lidoRecID</td>
								<td>The lidoRecID that was contained in the lido record when the record was uploaded to the aggregator</td>
							</tr>
						</table>

	            		<h4>Response</h4>
	            		<p>The response is in json</p>
	            		<pre id="successResponse" name="successResponse"></pre>
	            
	            		<h4>Testing</h4>
	            		<p>A simple test form for the Aggregator enrichmentRecord action is available <a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/Test/enrichmentRecord">here</a></p>
	          		</div>
	        	</div>
	      	</section>
    	</div>
    	
		<script src="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/static/js/json_syntax.js" type="text/javascript"></script>
	    <script type="text/javascript">
		    $(document).ready(function (){
		    	var enrichmentResponse = "";
        		$("#successResponse").html(JSONSyntaxHighlight(enrichmentResponse));
    		});
    
    	</script>
	</body>
</html>
