<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">EMS</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
          	<li><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li class="active" ><a href="${pageContext.request.contextPath}/userProfilePage">User Profile</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
                        <li class="active" role="presentation"><a href="${pageContext.request.contextPath}/userLoginPage">UserLogin</a></li>
             <li><a href="${pageContext.request.contextPath}/userLogout">Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>