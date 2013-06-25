<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>ECKCore - Record import / update submission form</title>
  </head>

  <body>
    <div class="row-fluid">

      <section id="main">

        <div class="hero-unit row">
          <div class="page-header span12">
            <h1>Import / update record</h1>
          </div>
        </div>
        
        <div class="row">
          <div class="span12">
            <form id="importForm" name="importForm" action="save" method="POST" enctype="multipart/form-data">
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
                        <th>Metadata file:</th>
                        <td><input type="file" name="metadataFile" id="metadataFile"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" class="btn btn-primary" name="importSubmit" id="importSubmit" value="Import"/>
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

        $("#importSubmit").click(function() {

            var responseType = $("#responseType").val();
            var action = "save";
            
            if ( responseType == "html" ) {
                // Default action ok
            }
            if ( responseType == "json" ) {
                action = "save.json";
            }

            $("#importForm").attr("action", action);
            $("#importForm").submit();

            return false;
        });
    
    </script>
    
  </body>
</html>
