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
	max-width:200px;
    max-height:200px;
    width: auto;
    height: auto;
}

user {
    display: inline-block;
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
                  <li class="active"><a href="<c:url value="/friends"/>">Friends</a></li>
              </ul>
              <ul class="nav navbar-nav navbar-right navbar">
              	<li><a href="<c:url value="/settings"/>">Settings</a></li>
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
    		<a href="<c:url value="/settings"/>">
    			<img src="<c:url value="/resources/images/${userName}/profile.jpg" />" alt="Sparky" class="grow" style="width:100px;height:auto;display:inline-block" onerror="this.src='<c:url value="/resources/images/sparky1.jpg" />'">
    		</a>	
    		<div style="width:100px;text-align:center; opacity: 0.5; background-color: black"">
    		<a href="<c:url value="/settings"/>" style="color:whitesmoke">
    			<p>${fName}<br>${lName}</p>
			</a>    			
    		</div>
    	</div>
    	<div style="padding-left:39%; position:absolute">
    		<h1 style="color: white; padding: 5 25 5 25;opacity: 0.6; background-color: black">Your Friends</h1>
    		<p style="text-align:center; color:black"><c:out value="${friendsMessage}"/></p>
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

  <div align="center" style="margin-top:100px; width:100%; padding: 0px 15px 15px 15px;">
	
	<div class="container" style="width:50%;text-align:center">
		<div class="row">
            <div class="col-md-12">
                <br>
			        <form:form commandName="friend" method="POST">
			            <p style="color: white; padding: 6 0 6 0;opacity: 0.6; background-color: black">
			            Friend ID: <form:input type="text" path="userId" style="width: 80%;color:black"/>
			            </p>
			            <br>
				        <input type="submit" value="Add" />
			        </form:form>
		         <p>${friendError}</p>
         	</div>
        </div> 	
  	</div>
	<div align="center" style="width:70%;height:100%; padding: 0px 15px 15px 15px;">  
  		<c:if test="${not empty friendObjects}">
  			<br>  			
  			<c:forEach items="${friendObjects}" var="friendObj">
  				<div class="user grow" align="center" style="margin:0 4 0 4">
    				<a href="<c:url value="/user/${friendObj.userId}"/>">
    					<img src="<c:url value="/resources/images/${friendObj.userId}/profile.jpg" />" alt="Sparky" style="width:auto;height:auto;display:inline-block" onerror="this.src='<c:url value="/resources/images/sparky1.jpg" />'">
    				</a>	
    				<div style="width:auto;text-align:center; opacity: 0.5; background-color: black"">
    					<a href="<c:url value="/userProfile/${friendObj.userId}"/>" style="color:whitesmoke">
    						<p>${friendObj.fName}<br>${friendObj.lName}</p>
						</a>    			
    				</div>					
  				</div>
  			</c:forEach>
  		</c:if>
  	</div> 
  </div>
  <br>
  </sec:authorize>
  </body>
</html>