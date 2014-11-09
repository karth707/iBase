<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>

<html>
<head>
<style>
#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Success</title>
</head>
<body background="<c:url value="/resources/images/background_1.jpg" />">
<br>
<br>
<br>
<br>
<br>
    <div align="center" id="login-box">
        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>Registration Succeeded!</h2></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <h3>Thank you for registering! Here's the review of your details:</h3>
                </td>
            </tr>
            <tr>
                <td>First Name:</td>
                <td>${userForm.firstName}</td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td>${userForm.lastName}</td>
            </tr>
            <tr>
                <td>E-mail/UserId:</td>
                <td>${userForm.email}</td>
            </tr>
        </table>
		<p>Return to login page: <a href="<c:url value="/login"/>">Login</a> </p>       
    </div>
</body>
</html>