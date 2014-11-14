<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
        <title><fmt:message key="title"/></title>
        <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
      <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
    </head>
    <body>
    <sec:authorize access="hasRole('ROLE_USER')">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="collapse navbar-collapse navHeaderCollapse">
                <ul class="nav navbar-nav navbar-left navbar">
                    <li><a href="<c:url value="/home"/>">Home</a></li>
                    <li><a href="<c:url value="/upload"/>">Upload</a></li>
                    <li class="active"><a href="<c:url value="/settings"/>">Settings</a></li>
                </ul>
            </div>
        </div>
    </div>
    
    <div class="container" style="margin-top:60px">
        <!-- CHANGE NAME TO USER'S NAME-->
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
    <div class="container" style="width:50%;text-align:center">
        <div class="row">
            <div class="col-md-12">
            <img src="<c:url value="/resources/images/${userName}/profile.jpg" />" alt="Sparky" style="width:200px;height:200px;display:inline-block" onerror="this.src='<c:url value="/resources/images/sparky1.jpg" />'">
                <h3 style="color:green">Update your profile picture!</h3> <br>
				<p style="color:orange">${uploadInfo}</p>
    			<form:form commandName="profileImageFile" enctype="multipart/form-data" method="POST">
        			<form:errors path="*" cssStyle="color : red;"/>
        			${errors2}
        			<form:input type="file" path="profileImageFile" />
        			<input type="submit" value="Update" />
    		 	</form:form>
    		 </div>
        </div>
    </div>
</body>
</body>
</sec:authorize>
</html>
