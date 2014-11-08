<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
    <head>
        <title><fmt:message key="title"/></title>
        <link rel="stylesheet" href="<c:url value="/Resources/MyTheme/CSS/bootstrap.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/Resources/MyTheme/CSS/styles.css" />">
    </head>
    <body>
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="collapse navbar-collapse navHeaderCollapse">
                <ul class="nav navbar-nav navbar-left navbar">
                    <li><a href="<c:url value="/home.htm"/>">Home</a></li>
                    <li class="active"><a href="<c:url value="/upload.htm"/>">Upload</a></li>
                    <li><a href="<c:url value="/settings.htm"/>">Settings</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container">
        <!-- CHANGE IMG TO USER PROFILE PIC-->
        <img src="${pageContext.request.contextPath}/Resources/MyTheme/Images/sparky1.jpg" alt="Sparky" style="width:100px;height:100px;display:inline-block">
        <!-- CHANGE NAME TO USER'S NAME-->
        <h4 style="display:inline-block;">Name</h4>
        <!-- CHANGE USERNAME TO USER'S USERNAME-->
        <h4 style="display:inline-block; float:right">Logged in as <em>username</em> <a href="logout.html">Log Out</a></h4>
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
</html>
