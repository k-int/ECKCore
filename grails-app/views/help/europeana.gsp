<!doctype html>
<html>
  	<head>
	    <meta name="layout" content="bootstrap"/>
	    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'json_syntax.css')}" />
	    <title>Europeana - DataSets</title>
  	</head>

  	<body>
    	<div class="row-fluid">
      		<section id="main">
        		<div class="hero-unit row">
          			<div class="page-header span12">
            			<h1>Europeana DataSets</h1>
          			</div>
        		</div>
        
        		<div class="row">
          			<div class="span12">
            			<h4>Invocation</h4>
            			<p>To retrieve the data sets for a provider we use the path /datasets/provider_id.json (eg. <a href="http://euinside.k-int.com/ECKCore2/Europeana/datasets/provider_id.json?wskey=xxx&id=5">Example</a>) with the following parameters</p>

						<table class="parameters">
							<tr>
								<th align="left">Parameter</th>
								<th align="left">Description</th>
							</tr>
							<tr>
								<td>wskey</td>
								<td>Your api key that allows you to use the Europeana api</td>
							</tr>
							<tr>	
								<td>id</td>
								<td>The Europeana id for the provider you want to see the data sets for</td>
							</tr>
						</table>
            			<br/>
           				<p>The content is returned as json</p>
           
           				<h4>Example response</h4>
           				<pre id="datasetResponse" class="jsonSyntax"></pre>
         			</div>
       			</div>
		    </section>
    	</div>
    
		<script src="/ECKCore/js/json_syntax.js" type="text/javascript"></script>
		
	    <script type="text/javascript">
		    $(document).ready(function (){
		    	var datasetResponse = {"action":"/v2/datasets/provider_id.json","success":true,"statsStartTime":1380903028655,"statsDuration":1238,"itemsCount":2,"totalResults":2,"items":[{"success":true,"identifier":"00501","name":"00501_L_GR_UniPatras_kosmopolis_dc","description":"Hidden","status":"Ingestion complete","publishedRecords":"40366","deletedRecords":"Not implemented yet"},{"success":true,"identifier":"00502","name":"00502_L_GR_UniPatras_pleias_dc","description":"Hidden","status":"Disabled and Replaced","publishedRecords":"34085","deletedRecords":"Not implemented yet"}]};
        		$("#datasetResponse").html(JSONSyntaxHighlight(datasetResponse));
    		});
    
    	</script>
	</body>
</html>
