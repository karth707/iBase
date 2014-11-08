<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
      <title><fmt:message key="title"/></title>
      <link rel="stylesheet" href="<c:url value="/Resources/MyTheme/CSS/bootstrap.min.css" />">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/MyTheme/CSS/styles.css">
  </head>
  <body>
  <sec:authorize access="hasRole('ROLE_USER')">
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

  <div class="container">
      <!-- CHANGE IMG TO USER PROFILE PIC-->
      <img src="${pageContext.request.contextPath}/Resources/MyTheme/Images/sparky1.jpg" alt="Sparky" style="width:100px;height:100px;display:inline-block">
      <!-- CHANGE NAME TO USER'S NAME-->
      <h4 style="display:inline-block;">Name</h4>
      <h4 style="display:inline-block; float:right">Logged in as <em><c:out value="${userName}"/></em></h4>

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
          <h4 style="display:inline-block; float:right">
              <a href="javascript:formSubmit()"> Logout</a>
          </h4>
      </c:if>
      <h5 style="float:right; margin-right:110px"><a href="<c:url value="/upload"/>">Upload another photo!</a></h5>
  </div>

  <h1 style="color: #6699FF; text-align:center"><fmt:message key="photoHeading"/></h1>
  </sec:authorize>
  </body>
</html>