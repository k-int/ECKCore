<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>ECKCore - Record lookup search form</title>
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
            <form id="lookupForm" name="lookupForm" action="show" method="POST" >
                <table>
                    <tr>
                        <th>CMS ID:</th>
                        <td><input type="text" name="cmsId" id="cmsId"/></td>
                    </tr>
                    <tr>
                        <th>Persistent ID:</th>
                        <td><input type="text" name="persistentId" id="persistentId"/></td>
                    </tr>
                    <tr>
                        <th>ECK ID:</th>
                        <td><input type="text" name="eckId" id="eckId"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" class="btn btn-primary" name="lookupSubmit" id="lookupSubmit" value="Lookup"/>
                        </td>
                    </tr>
                </table>
            </form>
                <table>
                    <tr>
                        <th>Response type:</th>
                        <td>
                            <select name="responseType" id="responseType">
                                <option value="html" selected="selected">HTML</option>
                                <option value="json">JSON</option>
                            </select>
                        </td>
                    </tr>
                
                </table>                                
          </div>
        </div>
          
      </section>

    </div>
    
    <script type="text/javascript">

        $("#lookupSubmit").click(function() {

            var responseType = $("#responseType").val();
            var action = "show";
            
            if ( responseType == "html" ) {
                // Default action ok
            }
            if ( responseType == "json" ) {
                action = "show.json";
            }

            $("#lookupForm").attr("action", action);
            $("#lookupForm").submit();

            return false;
        });
    
    </script>
    
  </body>
</html>
