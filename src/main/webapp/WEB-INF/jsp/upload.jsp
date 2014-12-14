<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
        <title><fmt:message key="title"/></title>
        <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
      <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
      <link href="<c:url value="/resources/css/hover.css" />" rel="stylesheet" type="text/css" media="all">
    </head>
    <body>
    <sec:authorize access="hasRole('ROLE_USER')">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="collapse navbar-collapse navHeaderCollapse">
                <ul class="nav navbar-nav navbar-left navbar">
                    <li><a href="<c:url value="/home"/>">Home</a></li>
                    <li class="active"><a href="<c:url value="/upload"/>">Upload</a></li>
                    <li><a href="<c:url value="/friends"/>">Friends</a></li>
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
    
    <div class="container" style="margin-top:60px; position:relative">
    	<div style="padding-left:0%; position:absolute;z-index: 10">
    		<a href="<c:url value="/settings"/>" class="grow">
    			<img src="<c:url value="/resources/images/${userName}/profile.jpg" />" alt="Sparky" style="width:100px;height:auto;display:inline-block" onerror="this.src='<c:url value="/resources/images/sparky1.jpg" />'">
    		</a>
    		<div style="width:100px;text-align:center; opacity: 0.5; background-color: black"">
    		<a href="<c:url value="/settings"/>" style="color:whitesmoke">
    			<p>${fName}<br>${lName}</p>
			</a>  
    		</div>
    	</div>
    	<div style="padding-left:33%; position:absolute">
    		<h3 style="color: white; padding: 5 25 5 25;opacity: 0.6; background-color: black">Share your photo with the world!</h3>
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
	<div class="container" style="width:50%;text-align:center;margin-top:100px">
        <div class="row">
            <div class="col-md-12">
                <br>
                <p class="uploadError">${uploadLimit}</p>
		        <form:form commandName="imageFile" enctype="multipart/form-data" method="POST">
			        <form:errors path="*" cssStyle="color : red;"/>
			        ${errors}
			        <br>
		            <form:input type="file" path="imageFile" align="center"/>
		            <br>
		            <p style="color: white; padding: 6 0 6 0;opacity: 0.6; background-color: black">
		            	Title: <form:input type="text" path="name" style="width: 80%;color:black"/>
		            </p>
		            <br>
			        <input type="submit" value="Upload" />
		         </form:form>
         	</div>
        </div>
    </div>
    </body>
     </sec:authorize>
</html>
