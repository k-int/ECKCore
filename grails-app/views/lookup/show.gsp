<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>ECKCore - Lookup record response</title>
  </head>

  <body>
    <div class="row-fluid">

      <section id="main">

        <div class="hero-unit row">
          <div class="page-header span12">
            <h1>Lookup record response</h1>
          </div>
        </div>
        
        <div class="row">
          <div class="span12">
          
            <g:if test="${responseVal.recordCount > 0 }">
                <g:if test="${responseVal.recordCount == 1}">
                    <h3>1 record found</h3>
                </g:if>
                <g:else>
                    <h3>${responseVal.recordCount} records found</h3>
                </g:else>
                
                <g:each in="${responseVal.records}" var="thisRecord" status="i">
                
                    <h4>Record ${i+1}</h4>
                    <table class="table table-striped">
                     <tr>
                         <th>ECK Id:</th>
                         <td>${thisRecord.eckId}</td>
                     </tr>
                     <tr>
                         <th>Persistent Id:</th>
                            <td>${thisRecord.persistentId}</td>
                        </tr>
                        <tr>
                            <th>CMS Id:</th>
                            <td>${thisRecord.cmsId}</td>
                        </tr>
                        <tr>
                            <th>Deleted?:</th>
                            <td>${thisRecord.deleted}</td>
                        </tr>
                        <tr>
                            <th>Contents:</th>
                            <td>${thisRecord.originalData}</td>
                        </tr>
                    </table>
                </g:each>
            </g:if>
            <g:else>
                <h3>No records found</h3>
                <p>Your search found no results. Click <g:link action="search">here</g:link> to try a different search.</p>
            </g:else>
          </div>
        </div>
          
      </section>

    </div>
  </body>
</html>
