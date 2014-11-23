<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>

<html>
<head>
<title><fmt:message key="title"/></title>	
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
<title>iBase-Registration</title>
</head>
<body background="<c:url value="/resources/images/background_1.jpg"/>"> 
    <br>
    <br>
    <br>
    <br>
    <br>
    <div align="center" id="login-box">
    	<p style="color: red;">${newUserError}</p>
        <form:form action="register" method="post" commandName="userForm">
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>iBase - Registration</h2></td>
                </tr>
                <tr>
                    <td>First Name:</td>
                    <td><form:input path="firstName" /></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><form:input path="lastName" /></td>
                </tr>
                <tr>
                    <td>E-mail:</td>
                    <td><form:input path="email" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><form:password path="password" /></td>
                </tr>
                    <td colspan="2" align="center"><input type="submit" value="Register" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><p>Login <a href="<c:url value="/login"/>">here!</a></p></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>