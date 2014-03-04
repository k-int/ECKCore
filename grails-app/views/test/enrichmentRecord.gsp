<% def ModulesService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("modulesService") %>
<% def AggregatorService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("aggregatorService") %>
<!doctype html>
<html>
	<head>
	  	<meta name="layout" content="bootstrap"/>
	  	<title>ECKCore - Aggregator - EnrichmentRecord - Test Page</title>
	</head>

	<body>
    	<div class="row-fluid">
			<section id="main">
		
		  		<div class="hero-unit row">
		    		<div class="page-header span12">
		      			<h1>ECKCore - Aggregator - EnrichmentRecord - Test Page</h1>
		    		</div>
		  		</div>
		  
			  	<div class="row">
			  		<h4>Either the Set and Record Id or the Provider and lidoRecID need to be filled out along with the Aggregator</h4>
			  		
			     	<div class="span12">
		           		<table>
		               		<tr>
		                   		<th align="right">Aggregator: </th>
		                   		<td>
			                   		<select id="aggregator">
										<g:each in="${AggregatorService.getAggregators('enrichmentRecord').keySet()}">
											<option value="${it}">${it}</option>
										</g:each>
									</select>
								</td>
		               		</tr>
		               		<tr>
		                   		<th align="right">Set: </th>
		                   		<td><g:field type="text" name="set"/></td>
		               		</tr>
		               		<tr>
		                   		<th align="right">Record Id: </th>
		                   		<td><g:field type="text" name="recordId"/></td>
		               		</tr>
		               		<tr>
		                   		<th align="right">Provider Code: </th>
		                   		<td><g:field type="text" name="providerCode"/></td>
		               		</tr>
		               		<tr>
		                   		<th align="right">lidoRecID: </th>
		                   		<td><g:field type="text" name="lidoRecID"/></td>
		               		</tr>
		               		<tr>
		                   		<th align="right">Start: </th>
		                   		<td><g:field type="text" name="start"/></td>
		               		</tr>
		               		<tr>
		                   		<th align="right">Rows: </th>
		                   		<td><g:field type="text" name="rows"/></td>
		               		</tr>
		               		<tr>
		                   		<th align="right">Output Raw Data: </th>
		                   		<td><g:field type="checkbox" name="rawData"/></td>
		               		</tr>
		               		<tr>
		                   		<td colspan="2">
		                   			<div class="btn btn-primary">
		                       			<g:field type="button"  name="execute" value="Execute"/>
		                       		</div>
		                   		</td>
		               		</tr>
		           		</table>
			     	</div>
		  		</div>
      		</section>
    	</div>
    
    	<script type="text/javascript">
	    	$("#execute").click(function() {
		    	var url = "${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/Aggregator/";
		    	url += $("#aggregator").val() + "/enrichmentRecord/";
		    	if ($("#set").val() == "") {
			    	// Retrieve the enrichment for a specific lidoRecID
			    	url += $("#providerCode").val() + "?lidoRecID=" + encodeURIComponent($("#lidoRecID").val());
			    } else {
				    url += $("#set").val();
				    if ($("#recordId").val() == "") {
					    // Retrieve the enrichments from the specified offset for the given number of records
						url += "/*" 
						var attributeAppender = "?";
					    if ($("#start").val() != "") {
					    	url += attributeAppender + "start=" + $("#start").val();
					    	attributeAppender = "&";
					    }
					    if ($("#rows").val() != "") {
					    	url += attributeAppender + "rows=" + $("#rows").val();
					    }
					} else {
						// Retrieve the enrcihment for the specified record id
			    		url +=  "/" + $("#recordId").val();
					}
			    }
			    if ($("#rawData").attr("checked") == "checked") {
				   	url += "?rawRequired=yes";
				}
			    window.location = url;   
	    	});
    	</script>
    
  	</body>
</html>
