<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
      <title><fmt:message key="title"/></title>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
      <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
  </head>
  <body>
  
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
	<sec:authorize access="hasRole('ROLE_USER')">
 <div class="container" style="margin-top:100px">
    <img src="<c:url value="/resources/images/sparky1.jpg" />" alt="Sparky" style="width:100px;height:100px;display:inline-block">
    <h4 style="display:inline-block; float:right">Logged in as <em>${userName}</em></h4> 
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

  <h1 style="color: #6699FF; text-align:center"><fmt:message key="photoHeading"/></h1>
  <div align="center">
  		<c:out value="${imagesMessage}"/>
  		<c:if test="${not empty imageList}">
  			<c:forEach items="${imageList}" var="image">
  				<div align="center" style="width:440px; padding:7px; border: 5px solid gray; display:inline-block">
  				<img src="<c:url value="${image}"/>" style="width:400px" />
  				</div>
  			</c:forEach>
  		</c:if> 
  </div>
  </sec:authorize>
  </body>
</html>