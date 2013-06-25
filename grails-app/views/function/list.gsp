<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>ECKCore - Available function list</title>
  </head>

  <body>
    <div class="row-fluid">

      <section id="main">

        <div class="hero-unit row">
          <div class="page-header span12">
            <h1>Available functions</h1>
          </div>
        </div>
        
        <div class="row">
          <div class="span12">

            <h3>Information</h3>
            <p>The following information is also available as a json response by sending a GET request to <g:link action="list.json">here</g:link> or by requesting this URL with a content type of "application/json" specified.</p>
          
            <h3>Calling available functions</h3>
            <p>In order to call the exposed functions it is necessary to construct the relevant URL and then send a GET request to that URL with a content type of "application/json" specified. 
            Please note that currently only 'external' modules can be called in this way as 'internal' modules are already exposed directly through the ECK Core itself.</p>
            
            <p>In order to construct the required URL you must combine the function call URL: <g:createLink action="call.json" absolute="true"/> with the following arguments:
            
                <dl>
                    <dt>module</dt>
                    <dd>The name of the module as specified in the identification list below</dd>
                    <dt>function</dt>
                    <dd>The name of the function to be called as specified in the identification list below</dd>
                    <dt>args</dt>
                    <dd>The arguments to be passed to the function as specified in the identification list below as a JSON string</dd>
                </dl>
            </p>
            
            <p>An example of one such URL is: <a href="/ECKCore/function/call.json?module=/KIPersistence/persistence&function=lookupRecordByEckId&args={eckId:1}">/ECKCore/function/call.json?module=/KIPersistence/persistence&function=lookupRecordByEckId&args={eckId:1}</a>
            which calls the lookupRecordByEckId() method on the KIPersistence module with the required arguments as a JSON string. Note clicking on the preceding link should return an appropriate response.</p>
            
            <h3>Available functions</h3>
            <g:each in="${availableCalls}">

                <h4>Module: ${it.name} <i>(${it.moduleType})</i></h4>
                <table class="table table-striped table-condensed">
                    <thead>
	                <tr>
	                    <th>Method name</th>
	                    <th colspan="3">Arguments</th>
	                </tr>
	                <tr>
	                    <td class="emptyCell"/>
	                    <td colspan="3">
	                       <table class="table table-condensed">
	                           <tr>
			                        <th width="33%">Name</th>
			                        <th width="33%">Type</th>
			                        <th width="33%">Required?</th>
	                           </tr>
	                       </table>
	                    </td>
	                </tr>
                </thead>
                <tbody>
                    <g:each in="${it.methods}" var="aMethod">
                        <tr>
                            <td colspan="4">
                                <h5>${aMethod.methodName}</h5>
                                <ul>
                                    <li><b>Return type: </b>${aMethod.returnType}</li>
                                    <li><b>Description: </b>${aMethod.description}</li>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <td/>
                            <td colspan="3">
                                <table class="table table-condensed"> 
                       
                        <g:each in="${aMethod.arguments}" var="anArg">
	                        <tr>
	                            <td width="33%">${anArg.argumentName}</td>
	                            <td width="33%">${anArg.argumentType}</td>
	                            <td width="33%">${anArg.required}</td>
	                        </tr>
                        </g:each>
                                </table>
                                </td>
                                </tr>
                    </g:each>
                </tbody>
                </table>
             </g:each>
            
          </div>
        </div>
          
      </section>

    </div>
  </body>
</html>
