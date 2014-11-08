<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
      <title><fmt:message key="title"/></title>
      <link rel="stylesheet" href="<c:url value="/Resources/MyTheme/CSS/bootstrap.min.css" />">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/MyTheme/CSS/styles.css">
  </head>
  <body>
  <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
          <div class="collapse navbar-collapse navHeaderCollapse">
              <ul class="nav navbar-nav navbar-left navbar">
                  <li class="active"><a href="<c:url value="/home.htm"/>">Home</a></li>
                  <li><a href="<c:url value="/upload.htm"/>">Upload</a></li>
                  <li><a href="<c:url value="/settings.htm"/>">Settings</a></li>
              </ul>
          </div>
      </div>
  </div>

  <div class="container">
      <!-- CHANGE IMG TO USER PROFILE PIC-->
      <img src="${pageContext.request.contextPath}/Resources/MyTheme/Images/sparky1.jpg" alt="Sparky" style="width:100px;height:100px;display:inline-block">
      <!-- CHANGE NAME TO USER'S NAME-->
      <h4 style="display:inline-block;">Name</h4>
      <!-- CHANGE USERNAME TO USER'S USERNAME-->
        <h4 style="display:inline-block; float:right">Logged in as <em>username</em> <a href="logout.html">Log Out</a></h4><br>
        <h5 style="float:right; margin-right:110px"><a href="<c:url value="/upload.htm"/>">Upload another photo!</a></h5>
  </div>
    <h1 style="color: #6699FF; text-align:center"><fmt:message key="photoHeading"/></h1>
  <!-- CHANGE Imagelist TO USER'S IMAGE LIST-->
  <!-- <c:out value="${imageList}"/> -->
  </body>
</html>