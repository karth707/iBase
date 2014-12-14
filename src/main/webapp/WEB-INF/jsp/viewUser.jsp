<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
      <title><fmt:message key="title"/></title>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
      <link href="<c:url value="/resources/css/hover.css" />" rel="stylesheet" type="text/css" media="all">
      <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
<style>
img {
    display: block;
	max-width:300px;
    max-height:300px;
    width: auto;
    height: auto;
}
.image {
	display: inline-block;
	max-width:300px;
	position:relative;
	margin: 2px; 
}
.image .text {
    position:absolute;
    top:10px;        
    width: 100%;   
    color:white;
    background-color: rgba(0,0,0,0.7);
    opacity: .90; /* Standard: FF gt 1.5, Opera, Safari, CSS3 */
    filter: alpha(opacity=90); /* IE lt 8 */
    -ms-filter: "alpha(opacity=90)"; /* IE 8 */
    -khtml-opacity: .90; /* Safari 1.x */
    -moz-opacity: .90; /* FF lt 1.5, Netscape */
}
</style>
  </head>
  <body>
  <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
          <div class="collapse navbar-collapse navHeaderCollapse">
              <ul class="nav navbar-nav navbar-left navbar">
                  <li><a href="<c:url value="/home"/>">Home</a></li>
                  <li><a href="<c:url value="/upload"/>">Upload</a></li>
                  <li><a href="<c:url value="/settings"/>">Settings</a></li>
                  <li class="active"><a href="<c:url value="/friends"/>">Friends</a></li>
              </ul>
              <ul class="nav navbar-nav navbar-right navbar">
              	<li>
              		<c:if test="${pageContext.request.userPrincipal.name != null}">
        				<a href="javascript:formSubmit()">Logout</a>
        			</c:if>
              	</li>
              </ul>
          </div>
      </div>
  </div>
	<sec:authorize access="hasRole('ROLE_USER')">
 	<div class="container" style="margin-top:60px; position:relative">
    	<div style="padding-left:0%; position:absolute; z-index: 10">
    		<a href="<c:url value="/userProfile/${friendId}"/>">
    			<img src="<c:url value="/resources/images/${friendId}/profile.jpg" />" alt="Sparky" class="grow" style="width:100px;height:auto;display:inline-block" onerror="this.src='<c:url value="/resources/images/sparky1.jpg" />'">
    		</a>	
    		<div style="width:100px;text-align:center; opacity: 0.5; background-color: black"">
    		<a href="<c:url value="/userProfile/${friendId}"/>" style="color:whitesmoke">
    			<p>${fName}<br>${lName}</p>
			</a>    			
    		</div>
    	</div>
    	<div style="padding-left:39%; position:absolute">
    		<h1 style="color: white; padding: 5 25 5 25;opacity: 0.6; background-color: black">${fName}'s Images</h1>
    		<p style="text-align:center; color:black"><c:out value="${imagesMessage}"/></p>
    	</div>
    	<div style="padding-left:80%; position:absolute">
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

  <div align="center" style="margin-top:120px; width:100%; padding: 0px 15px 15px 15px;">
  		<c:if test="${not empty imageObjects}">
  			<br>
  			<c:forEach items="${imageObjects}" var="image">
  				<div class="image" align="center">
  				<img src="<c:url value="${image.imageLocation}"/>" class="grow-rotate" />
  					<div class="text" >
  					 	<p style="padding-top: 10">${image.imageTitle}</p>
  					</div>
  				</div>
  			</c:forEach>
  		</c:if> 
  </div>
  <br>
  <br>
  <br>
  <br>
  </sec:authorize>
  </body>
</html>