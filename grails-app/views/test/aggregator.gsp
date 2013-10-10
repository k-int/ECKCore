<% def ModulesService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("modulesService") %>
<% def AggregatorService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("aggregatorService") %>
<!doctype html>
<html>
	<head>
	  	<meta name="layout" content="bootstrap"/>
	  	<title>ECKCore - Aggregator - Test Page</title>
	</head>

	<body>
    	<div class="row-fluid">
			<section id="main">
		
		  		<div class="hero-unit row">
		    		<div class="page-header span12">
		      			<h1>ECKCore - Aggregator - Test Page</h1>
		    		</div>
		  		</div>
		  
			  	<div class="row">
			  		<h4>All fields need to be filled out, if you do not have a provider or collection, enter *</h4>
			     	<div class="span12">
		           		<table>
		               		<tr>
		                   		<th align="right">Provider Code: </th>
		                   		<td><g:field type="text" name="providerCode" value="*" required="true"/></td>
		               		</tr>
		               		<tr>
		                   		<th align="right">Collection Code: </th>
		                   		<td><g:field type="text" name="collectionCode" value="*" required="true"/></td>
		               		</tr>
		               		<tr>
		                   		<th align="right">Aggregator: </th>
		                   		<td>
			                   		<select id="aggregator">
										<g:each in="${AggregatorService.getAggregators().keySet()}">
											<option value="${it}">${it}</option>
										</g:each>
									</select>
								</td>
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
		    	if ($("#providerCode").val() == "") {
			    	alert("Please enter the provider code field");
			    } else if ($("#collectionCode").val() == "") {
			    	alert("Please enter the collection code field");
			    } else {
			    	var url = "${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/Aggregator/";
			    	url += $("#providerCode").val() + "/" + $("#collectionCode").val() + "/statistics/" + $("#aggregator").val();
			    	window.location = url;   
				}
	    	});
    	</script>
    
  	</body>
</html>
