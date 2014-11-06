<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="login"/> <c:out value="${now}"/></p>
	
	<br>
    <a href="<c:url value="home.htm"/>">goto Home!</a>
    <br>
  </body>
</html>