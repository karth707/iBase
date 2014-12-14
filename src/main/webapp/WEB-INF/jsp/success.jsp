<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>

<html>
<head>
		<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
      	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
     	<link href="<c:url value="/resources/css/hover.css" />" rel="stylesheet" type="text/css" media="all">
<title>iBase-Signup</title>
<style>
#login-box {
	width: 100%;	
	margin: 0 auto 0 auto;
	background: transparent;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
}
#headerBack {
	width:100%;
	height:220px;
	background-image:url("<c:url value="/resources/images/background_4.jpg" />");
}
#headerBack2 {
	width:100%;
	height:150px;
	background-image:url("<c:url value="/resources/images/background_4.jpg" />");
}
#backLogin{
	width:100%;
	height:auto;
}
#header{
	width:100%;
	height:80px;
	padding: 5 25 5 25;
	opacity: 0.8; 
	background-color:black;
}

h1 {	
	color: whitesmoke;
	text-align: center;	
}

userfont {
	font-size: large;
	font-weight: bold;
}

h4, h3 {
	color: white; 
	padding: 5 25 5 25;
	opacity: 0.6; 
	background-color: black;
}
</style>
</head>
<body> 
    
    <div id="headerBack">
    	<br>
    	<br>
    	<br>
    	<div id="header">
    		<h1>iBase - Your Personal Image Database</h1>
    	</div>   
    </div>
    <div id="backLogin">
    	<div class="container" style="margin-top:10px;width:50%;text-align:center">
        	<div class="row">
            	<div class="col-md-12">
            		<h2 style="color: white; padding: 5 25 5 25;opacity: 0.6; background-color: black">Registration Successful!</h2>
            		<h3>Thank you for registering! Your details:</h3>
            		<br>
					<p><h4>First Name:</h4>&nbsp; <userfont>${userForm.firstName}</userfont></p>
					<p><h4>Last Name:</h4>&nbsp; <userfont>${userForm.lastName}</userfont></p>
					<p><h4>Email address:</h4>&nbsp; <userfont style="font-style: italic; ">${userForm.email}</userfont></p>
    		 	</div>
        	</div>
        	<div class="row">
        		<p style="font-size:large; font-weight: bold;">Return to login page: <a href="<c:url value="/login"/>">Login</a> </p>
        	</div>
        	<br>
    	</div>
	</div>       
    <div id="headerBack2">
    </div>
</body>
</html>