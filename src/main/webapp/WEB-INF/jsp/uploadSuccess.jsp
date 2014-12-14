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
	                  <li><a href="<c:url value="/home"/>">Home</a></li>
	                  <li><a href="<c:url value="/upload"/>">Upload</a></li>
	                  <li><a href="<c:url value="/settings"/>">Settings</a></li>
	                  <li><a href="<c:url value="/friends"/>">Friends</a></li>
	              </ul>
	              <ul class="nav navbar-nav navbar-right navbar">
	              	<li class="active"><a href="<c:url value="/settings"/>">Settings</a></li>
              		<li>
              			<c:if test="${pageContext.request.userPrincipal.name != null}">
        					<a href="javascript:formSubmit()">Logout</a>
        				</c:if>
              		</li>
              	</ul>
	          </div>
	      </div>
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
	  </div>
  
  	<div style="margin-top:100px;text-align:center">
		<h3 style="color: white; padding: 5 25 5 25;opacity: 0.6; background-color: black">The file ${uploadedFileName} has been uploaded successfully.</h3> <br>
		<img src="<c:url value="${uploadedImage}"/>" style="display:inline-block;max-width:500px;max-height:500px;width: auto;height: auto;" />	
		<br>
		<br>
		<p style="color: darkgray">Title: ${imageDesc}</p>
	</div>
</div>
</body>
</html>
