<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>ECKCore - Record lookup index</title>
  </head>

  <body>
    <div class="row-fluid">

      <section id="main">

        <div class="hero-unit row">
          <div class="page-header span12">
            <h1>Lookup record</h1>
          </div>
        </div>
        
        <div class="row">
          <div class="span12">
            <h4>Parameters / invocation</h4>
            <p>In order to lookup a record that has previously been imported into the ECK the caller must POST to <g:createLink action="show"/> with one (or more) of the following parameters:</p>
            <dl>
                <dt>cmsId</dt>
                <dd>The CMS identifier for the record</dd>
                <dt>persistentId</dt>
                <dd>The persistent identifier for the record</dd>
                <dt>eckId</dt>
                <dd>The eck provided identifier for the record</dd>
            </dl>
            <p>Setting the acceptable content type in your request to "application/json" (or POSTing to <g:createLink action="show.json"/>) will result in a json response like one of the two shown below.</p>
            <p>Note: The request must be sent as a POST request. Sending a GET request will result in an error response being returned (HTTP Error 405).</p>
            
            <h4>Responses</h4>
            <h5>Record found response</h5>
            <pre id="successResponse" name="successResponse"></pre>
            <h5>No record found response</h5>
            <pre id="failResponse" name="failResponse"></pre>
            
            <h4>Testing</h4>
            <p>In order to allow simple testing of the ECK lookup interfaces, etc. a form is available <g:link action="search">here</g:link>. This form renders the json response as an HTML page by default but can be changed to return the json response directly using the 'Response type' field</p>
          </div>
        </div>
          
      </section>

    </div>
    
    <script type="text/javascript">

    $(document).ready(function (){

    	var successData = {records: [{eckId: 1,cmsId: "1234",persistentId: "http://www.k-int.com/object/1234",deleted: null,originalData: "metadata"}], recordCount: 1};
    	var failData = {records: [ ],recordCount: 0};
        $("#successResponse").html(syntaxHighlight(successData));
        $('#failResponse').html(syntaxHighlight(failData));
        
    });
    
function syntaxHighlight(json) {
    if (typeof json != 'string') {
         json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
        var cls = 'number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'key';
            } else {
                cls = 'string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'boolean';
        } else if (/null/.test(match)) {
            cls = 'null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}
    </script>
    
    <style>
        pre {outline: 1px solid #ccc; padding: 5px; margin: 5px; }
        .string { color: green; }
        .number { color: darkorange; }
        .boolean { color: blue; }
        .null { color: magenta; }
        .key { color: red; }
    </style>
  </body>
</html>
