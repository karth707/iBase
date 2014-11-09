<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
    <head>
        <title><fmt:message key="title"/></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
        <style>
            body {
                margin-top: 60px;
                margin-bottom: 80px;
            }

            .page-header {
                margin-top: 0;
            }

            .panel-body {
                padding-top: 0;
            }

            .featuredImg {
                margin-bottom: 15px;
            }

            .JakeHelloWorldTitle {
                text-align: center;
            }

            .WarningPotentialEmailersText {
                color:red;
            }

            #jake {
                margin: 3px;
                margin-right:20px;
                width: 200px;
                height: 300px;
                float: left;
            }

            .smrow {
                margin-top: 50px;
            }

            div.c-wrapper{
                padding-left:0px;
                padding-right:0px;
                margin:auto;
                margin-bottom:20px;
                padding-top: 40px;
                padding-bottom: 40px;
            }



            .jakebuttons {
                margin-top: 60px;
                margin-left: 20px;
            }

            .jakebutton {
                padding-top: 10px;
                padding-bottom: 10px;
                margin-top: 10px;
                margin-bottom: 10px;
                height: 50px;
                width: 100px;
            }

            .welcome-header
            {
                color: green;
            }

            .jakeLabel{
                display: inline-block;
                float: left;
                clear: left;
                width: 250px;
                text-align: right;
            }
            .jakeInput {
                display: inline-block;
                float: left;
            }

        </style>
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
    <div class="container">
        <!-- CHANGE IMG TO USER PROFILE PIC-->
        <img src="${pageContext.request.contextPath}/Resources/MyTheme/Images/sparky1.jpg" alt="Sparky" style="width:100px;height:100px;display:inline-block">
        <!-- CHANGE NAME TO USER'S NAME-->
        <h4 style="display:inline-block;">Name</h4>
        <h4 style="display:inline-block; float:right">Logged in as <em><c:out value="${userName}"/></em></h4>

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
            <h4 style="display:inline-block; float:right">
                <a href="javascript:formSubmit()"> Logout</a>
            </h4>
        </c:if>
    </div>
    <div class="container" style="width:50%;text-align:center">
        <div class="row">
            <div class="col-md-12">
                <h3 style="color:green">Share your photo with the world!</h3> <br>
                <form action="uploadSuccess.jsp">
                    <label class="jakeLabel">Title&nbsp;&nbsp;</label> <input class="jakeInput" type="text" name="title"><br><br>
                    <label style="font-size: large" class="jakeLabel">Photo&nbsp;&nbsp;</label> <input class="jakeInput" type="file" name="photoToUpload" accept="image/*"><br><br>
                    <label class="jakeLabel"></label><input class="jakeInput" type="submit" value="Upload Photo">
                </form>
            </div>
        </div>
    </div>
        <!--
        <h2>Upload Image:</h2>
        <form:form commandName="imageFile" enctype="multipart/form-data" method="POST">
	        <form:errors path="*" cssStyle="color : red;"/>
	        ${errors}
	        Name :
            <form:input type="file" path="imageFile" />
	        <input type="submit" value="Upload" />
         </form:form>
         -->
    </body>
    </sec:authorize>
</html>
