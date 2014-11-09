<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title>
  
  <style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}
 
.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}
 
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
  </head>
  
  <body onload='document.loginForm.username.focus();' background="<c:url value="/resources/images/background_1.jpg" />">
   	<div style="text-align:center">
   	<br>
   	<br>
         <h1><font color="White"><fmt:message key="heading"/></font></h1>
    	 <p><font color="White"><fmt:message key="login"/> <c:out value="${now}"/></font></p>
	</div>
	<div id="login-box">
 
		<h3>Login with Username and Password</h3> 
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if> 
		<form name='loginForm'
		  action="<c:url value='/j_spring_security_check' />" method='POST' role="form">
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='userId'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
				  value="submit" /></td>
			</tr>
			<tr>
				<p>New User? Signup<a href="<c:url value="/signup"/>"><font color="Green"> Here!</font></a></p> 
			</tr>
		  </table>
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		</form>
	</div>
	
	
  </body>
</html>