<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>ECKCore - Record import / update response</title>
  </head>

  <body>
    <div class="row-fluid">

      <section id="main">

        <div class="hero-unit row">
          <div class="page-header span12">
            <h1>Import / update record response</h1>
          </div>
        </div>
        
        <div class="row">
          <div class="span12">
          
            <g:if test="${ responseVal.success == true }">
                <h3>Import / update successful</h3>
                <h4>Messages:</h4>
                <ul>
                    <g:each in="${responseVal.messages}" var="aMessage">
                        <li>${aMessage}</li>
                    </g:each>
                </ul>
                <h4>ECK record ID:</h4>
                <ul>
                    <li>${responseVal.eckId}</li>
                </ul>
            </g:if>
            <g:else>
                <h3>Import unsuccessful</h3>
                <h4>Messages:</h4>
                <ul>
                    <g:each in="${responseVal.messages}" var="aMessage">
                        <li>${aMessage}</li>
                    </g:each>
                </ul>
            </g:else>
          </div>
        </div>
          
      </section>

    </div>
  </body>
</html>
