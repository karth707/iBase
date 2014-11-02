<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<body>
<h2>Spring MVC file upload example</h2>

<form:form method="POST" commandName="imageFile"  enctype="multipart/form-data">
    Please select a file to upload : 
    <input type="file" name="imageFile" />
    <input type="submit" value="upload" />
	<form:errors path="file" cssStyle="color: #ff0000;" />
</form>

</body>
</html>
