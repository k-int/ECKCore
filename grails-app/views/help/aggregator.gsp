<!doctype html>
<html>
  	<head>
    	<meta name="layout" content="bootstrap"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}" type="text/css">
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
	            		<h4>Parameters / invocation</h4>
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
								<td>The code for the aggregator that you want to retrieve the statistics from (eg. DarkAggregator, CultureGridTest, CultureGridLive, Europeana, ...)</td>
							</tr>
						</table>
						<br/>
						<p>The possible parameters are defined by the aggregator as the values supplied will just be passed on</p>
								
	            		<h4>Testing</h4>
	            		<p>A simple test form for the Aggregator interface is available at <a href="/ECKCore/Test/aggregator">here</a></p>
	          		</div>
	        	</div>
	      	</section>
    	</div>
	</body>
</html>
