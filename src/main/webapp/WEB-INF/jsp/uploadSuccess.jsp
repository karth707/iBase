<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
      <title><fmt:message key="title"/></title>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
      <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
  </head>
<body>
<div>
	<div class="navbar navbar-inverse navbar-fixed-top">
	      <div class="container">
	          <div class="collapse navbar-collapse navHeaderCollapse">
	              <ul class="nav navbar-nav navbar-left navbar">
	                  <li class="active"><a href="<c:url value="/home"/>">Home</a></li>
	                  <li><a href="<c:url value="/upload"/>">Upload</a></li>
	                  <li><a href="<c:url value="/settings"/>">Settings</a></li>
	              </ul>
	          </div>
	      </div>
	  </div>
  
  	<div style="margin-top:100px;text-align:center">
		<h3 style="color: green;">File has been uploaded successfully.</h3> <br>
		File Name : ${imageFile.name}
	</div>
</div>
</body>
</html>
