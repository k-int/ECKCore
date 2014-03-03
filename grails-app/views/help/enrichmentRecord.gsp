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
		    	var enrichmentResponse = {"timestampUpdated": "1970-01-01T00:00:00.000Z","agents": [{"about": "http://dbpedia.org/resource/Ludovico_Buti","prefLabel": {"it": ["ludovico buti"],"en": ["ludovico buti"],"es": ["ludovico buti"]}}],"concepts": [{"about": "http://www.eionet.europa.eu/gemet/concept/6023","prefLabel": {"sl": ["papir"],"sk": ["papier"],"da": ["papir"],"ro": ["hârtie"],"it": ["carta"],"no": ["papir"],"hu": ["papír"],"lt": ["popierius"],"cs": ["papír"],"de": ["papier"],"el": ["χαρτί/ανακοίνωση/έγγραφο/εφημερίδα/τίτλος/αξιόγραφο"],"def": ["paper","纸"],"fi": ["paperi"],"pt": ["papel"],"pl": ["papier"],"bg": ["хартия"],"sv": ["papper"],"fr": ["papier"],"en": ["paper"],"ru": ["бумага"],"et": ["paber"],"es": ["papel"],"nl": ["papier"]},"broader": ["http://www.eionet.europa.eu/gemet/concept/4260"]}],"places": [{"about": "http://sws.geonames.org/2650629/","prefLabel": {"def": ["county durham","durham"],"en": ["durham"]},"latitude": 54.7768,"longitude": -1.57575},{"about": "http://sws.geonames.org/7293138/","prefLabel": {"def": ["rokeby"]},"isPartOf": {"def": ["http://sws.geonames.org/2650629/"]},"latitude": 54.51662,"longitude": -1.8934}],"identifiers": ["http://ww2.durham.gov.uk/dre/pgDre.aspx?ID=SMR7806"],"timespans": [{"about": "http://semium.org/time/14xx_1_third","prefLabel": {"en": ["early 15th century"],"ru": ["начало 15-го века"]}},{"about": "http://semium.org/time/1420","prefLabel": {"def": ["1420"]},"isPartOf": {"en": ["http://semium.org/time/14xx_1_third"]}}],"years": ["1420"],"timestampCreated": "1970-01-01T00:00:00.000Z","europeanaIdentifier": "/2022316/971991E13D2AB8ACC387533A3EDED1C2143F8097"};
        		$("#successResponse").html(JSONSyntaxHighlight(enrichmentResponse));
    		});
    
    	</script>
	</body>
</html>
