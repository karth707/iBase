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
                    <li class="active"><a href="<c:url value="/upload"/>">Upload</a></li>
                    <li><a href="<c:url value="/settings"/>">Settings</a></li>
                </ul>
            </div>
        </div>
    </div>
    
    <div class="container" style="margin-top:60px">
        <img src="<c:url value="/resources/images/${userName}/profile.jpg" />" alt="Sparky" style="width:100px;height:100px;display:inline-block" onerror="this.src='<c:url value="/resources/images/sparky1.jpg" />'">
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
                <h3 style="color:green">Share your photo with the world!</h3> <br>
                <p>${uploadLimit}</p>
		        <form:form commandName="imageFile" enctype="multipart/form-data" method="POST">
			        <form:errors path="*" cssStyle="color : red;"/>
			        ${errors}
			        <br>
		            <form:input type="file" path="imageFile" align="center"/>
		            <br>
		            Title: <form:input type="text" path="name"/>
		            <br>
		            <br>
			        <input type="submit" value="Upload" />
		         </form:form>
         	</div>
        </div>
    </div>
    </body>
     </sec:authorize>
</html>
