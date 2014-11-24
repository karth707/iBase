<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
  <head>
      <title><fmt:message key="title"/></title>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
      <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
<style>
#img {
    -webkit-filter: brightness(1);
    -webkit-filter: sepia(0%);
    -webkit-filter: grayscale(0%);
    -webkit-filter: contrast(1);
    width:500px;
    display: block;
    margin-left: auto;
    margin-right: auto;
}
input{  	
    margin-left: auto;
    margin-right: auto;
}
p { text-align: center }
h2 { text-align: center;
     color: blue; }
h5 { text-align: center;
	 color: orange; }
</style>
</head>
  <body>
  
  <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
          <div class="collapse navbar-collapse navHeaderCollapse">
              <ul class="nav navbar-nav navbar-left navbar">
                  <li class="active"><a href="<c:url value="/home"/>">Home</a></li>
                  <li><a href="<c:url value="/upload"/>">Upload</a></li>
                  <li><a href="<c:url value="/settings"/>">Settings</a></li>
              </ul>
          </div>
      </div>
  </div>     
  
  <sec:authorize access="hasRole('ROLE_USER')">
 	<div class="container" style="margin-top:60px">
    <img src="<c:url value="/resources/images/${userName}/profile.jpg" />" alt="Sparky" style="width:100px;height:100px;display:inline-block" onerror="this.src='<c:url value="/resources/images/sparky1.jpg" />'">
    <h4 style="display:inline-block; float:right">Logged in as <em><c:out value="${userName}"/></em>
        	<c:if test="${pageContext.request.userPrincipal.name != null}">
        		<a href="javascript:formSubmit()">  Logout</a>
        	</c:if>
        </h4>
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
</div>


<h2>${imageTitle}</h2>
<br>
<img id="img" src="<c:url value="${imageLocation}"/>"/>				
<h5> Play with Image filters! </h5>
<br>
	<p>Brightness: <input type="range" min="0" max="200" step="10" id="brightness" style="width:200px"/></p>		
	<script type='text/javascript'>
		//<![CDATA[ 
		document.getElementById("brightness").addEventListener('change', function() {
    	document.getElementById("img").setAttribute("style", "-webkit-filter:brightness(" + this.value + "%)");	
		}, false);
		//]]>  
	</script>  
	<br>
	<p>Grayscale: <input type="range" min="0" max="200" step="10" id="gray" style="width:200px"/></p>		
	<script type='text/javascript'>
		//<![CDATA[ 
		document.getElementById("gray").addEventListener('change', function() {
    	document.getElementById("img").setAttribute("style", "-webkit-filter:grayscale(" + this.value + "%)");	
		}, false);
		//]]>  
	</script>  
	<br>
	<p>Sepia: <input type="range" min="0" max="100" step="10" id="sepia" style="width:200px"/></p>		
	<script type='text/javascript'>
		//<![CDATA[ 
		document.getElementById("sepia").addEventListener('change', function() {
    	document.getElementById("img").setAttribute("style", "-webkit-filter:sepia(" + this.value + "%)");	
		}, false);
		//]]>  
	</script>  
	<br>
	<p>Contrast: <input type="range" min="0" max="200" step="10" id="contrast" style="width:200px"/></p>		
	<script type='text/javascript'>
		//<![CDATA[ 
		document.getElementById("contrast").addEventListener('change', function() {
    	document.getElementById("img").setAttribute("style", "-webkit-filter:contrast(" + this.value + "%)");	
		}, false);
		//]]>  
	</script>  
	<br>
<br>
</sec:authorize>
  </body>
</html>