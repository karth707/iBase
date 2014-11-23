<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
      <title><fmt:message key="title"/></title>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
      <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
      <style>
.image {
	position:relative;
	width:340px; 	
	border: 5px solid gray;
	margin: 2px; 
	display:inline-block
}
.image .text {
    position:absolute;
    top:10px;        
    width: 100%;   
    color:white;
    background-color: rgba(0,0,0,0.6);
    opacity: .60; /* Standard: FF gt 1.5, Opera, Safari, CSS3 */
    filter: alpha(opacity=60); /* IE lt 8 */
    -ms-filter: "alpha(opacity=60)"; /* IE 8 */
    -khtml-opacity: .60; /* Safari 1.x */
    -moz-opacity: .60; /* FF lt 1.5, Netscape */
}
</style>
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
 	<div class="container" style="margin-top:60px">
    <img src="<c:url value="/resources/images/${userName}/profile.jpg" />" alt="Sparky" style="width:100px;height:100px;display:inline-block" onerror="this.src='<c:url value="/resources/images/sparky1.jpg" />'">
    <h4 style="display:inline-block; float:right">Logged in as <em><c:out value="${userName}"/></em>
        	<c:if test="${pageContext.request.userPrincipal.name != null}">
        		<a href="javascript:formSubmit()">  Logout</a>
        	</c:if>
        </h4>
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

  <h1 style="color: #6699FF; text-align:center"><fmt:message key="photoHeading"/></h1>
  <div align="center">
  		<c:out value="${imagesMessage}"/>
  		<c:if test="${not empty imageObjects}">
  			<c:forEach items="${imageObjects}" var="image">
  				<div class="image" align="center">
  				<img src="<c:url value="${image.imageLocation}"/>" style="width:100%" />
  					<div class="text" >
  					 	<p>${image.imageTitle}</p>
  					</div>
  				</div>
  			</c:forEach>
  		</c:if> 
  </div>
  </sec:authorize>
  </body>
</html>