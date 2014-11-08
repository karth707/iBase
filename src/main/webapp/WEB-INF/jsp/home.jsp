<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
  <h1><fmt:message key="heading"/></h1>
   <p><fmt:message key="greeting"/> <c:out value="${now}"/></p>
    
  <sec:authorize access="hasRole('ROLE_USER')">
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
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>
 
 	<br>
    <a href="<c:url value="upload.htm"/>">Upload an Image!</a>
    <br>
 
  	<p>User Logged in: <c:out value="${userName}"/></p>
	</sec:authorize>
  </body>
</html>