<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
    <title><fmt:message key="title"/></title>
    <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
      <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="collapse navbar-collapse navHeaderCollapse">
            <ul class="nav navbar-nav navbar-left navbar">
                <li><a href="<c:url value="/home.htm"/>">Home</a></li>
                <li><a href="<c:url value="/upload.htm"/>">Upload</a></li>
                <li class="active"><a href="<c:url value="/settings.htm"/>">Settings</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container" style="margin-top:100px">
    <img src="<c:url value="/resources/images/sparky1.jpg" />" alt="Sparky" style="width:100px;height:100px;display:inline-block">
    <h4 style="display:inline-block; float:right">Logged in as <em>username</em></h4> 
    <!-- For login user -->
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
      <form action="${logoutUrl}" method="post" id="logoutForm">
          <input type="hidden" name="${_csrf.parameterName}"
                 value="${_csrf.token}" />
      </form>
      <script>
          function formSubmit() {
              document.getElementById("logoutForm").submit();
          }
      </script>
      <c:if test="${pageContext.request.userPrincipal.name != null}">
          <p style="float:right">
              <a href="javascript:formSubmit()"> Logout</a>
          </p>
      </c:if>
</div>
<div class="container" style="width:50%; text-align:center">
    <div class="row">
        <div class="col-md-12">
            <h3 style="color:green">Change Your Profile Photo</h3> <br>
            <label class="jakeLabel" style="font-size: large">Photo&nbsp;&nbsp;</label> <input class="jakeInput" type="file" name="profilePhotoToUpload" accept="image/*"><br><br>
            <label class="jakeLabel"></label><input class="jakeInput" type="submit" value="Save Changes">
        </div>
    </div>
</div>
</body>
</html>
