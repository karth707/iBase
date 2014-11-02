<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${now}"/></p>
	
	<br>
    <a href="<c:url value="upload.htm"/>">Upload an Image!</a>
    <br>
  </body>
</html>