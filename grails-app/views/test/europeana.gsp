<% def ModulesService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("modulesService") %>
<!doctype html>
<html>
	<head>
	  	<meta name="layout" content="bootstrap"/>
	  	<title>ECKCore - Europeana - Test Page</title>
	</head>

	<body>
    	<div class="row-fluid">
			<section id="main">
		
		  		<div class="hero-unit row">
		    		<div class="page-header span12">
		      			<h1>ECKCore - Europeana - Test Page</h1>
		    		</div>
		  		</div>
		  
			  	<div class="row">
			  		<h4>The wskey is a required field, go to the europeana web site to obtain one if you do not have one, providers can be searched with or without a provider id, datasets can be searched using a provider id or dataset id</h4>
			     	<div class="span12">
		           		<table>
		               		<tr>
		                   		<th align="right">wskey: </th>
		                   		<td><g:field type="text" name="wskey" value="xxx" required="true"/></td>
		               		</tr>
		               		<tr>
		                   		<th align="right">Provider Id: </th>
		                   		<td><g:field type="text" name="providerId"/></td>
		               		</tr>
		               		<tr>
		                   		<th align="right">Dataset Id: </th>
		                   		<td><g:field type="text" name="datasetId"/></td>
		               		</tr>
		               		<tr>
		                   		<td colspan="2">
		                   			<div class="btn btn-primary">
		                       			<g:field type="button"  name="datasetButton" value="Data Set"/>
		                       		</div>
		                   			<div class="btn btn-primary">
		                       			<g:field type="button"  name="providerButton" value="Provider"/>
		                       		</div>
		                   		</td>
		               		</tr>
		           		</table>
			     	</div>
		  		</div>
      		</section>
    	</div>
    
    	<script type="text/javascript">
	    	$("#datasetButton").click(function() {
		    	var url = "${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/Europeana/datasets";
		    	var identifier = null;
		    	if ($("#wskey").val() == "") {
			    	alert("Please enter the wskey field");
			    } else {
			    	var providerId = $("#providerId").val();
			    	if ((providerId != null) && (providerId != "")) {
				    	url += "/provider_id";
				    	identifier = providerId;
			    	} else {
				    	var datasetId = $("#datasetId").val();
				    	if ((datasetId != null) && (datasetId != "")) {
					    	url += "/dataset_id";
					    	identifier = datasetId;
				    	} else {
					    	alert("Please enter either the Provider Identifier or the Dataset Identifier");
					    }
				    }
			    	if (identifier != null) {
					    url += ".json?";
					    url += "wskey=" + $("#wskey").val();
				    	url += "&id=" + identifier;
				    	window.location = url;
			    	}   
				}
	    	});
	    	$("#providerButton").click(function() {
		    	var url = "${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/Europeana/providers";
		    	var identifier = null;
		    	if ($("#wskey").val() == "") {
			    	alert("Please enter the wskey field");
			    } else {
			    	var providerId = $("#providerId").val();
			    	if ((providerId != null) && (providerId != "")) {
				    	url += "/provider_id";
				    	identifier = providerId;
				    }
				    url += ".json?";
				    url += "wskey=" + $("#wskey").val();
				    if (identifier != null) { 
				    	url += "&id=" + identifier;
				    } 
			    	window.location = url;   
				}
	    	});
    	</script>
    
  	</body>
</html>
