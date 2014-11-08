<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
    <body>
        <h2>Upload Image:</h2>
        <form:form commandName="imageFile" enctype="multipart/form-data" method="POST">
	        <form:errors path="*" cssStyle="color : red;"/>
	        ${errors}
	        Name :
            <form:input type="file" path="imageFile" />
	        <input type="submit" value="Upload" />
         </form:form>
    </body>
</html>
