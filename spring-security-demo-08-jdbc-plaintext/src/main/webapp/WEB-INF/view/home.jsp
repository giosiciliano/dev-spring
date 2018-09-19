<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company Home Page</title>
</head>
<body>
<h2> Company Home Page!</h2>
<hr>
	<p>
		Welcome to company home page!
	</p>

	<hr>
	<!-- display username and role  -->
	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
	</p>
	
	
	<security:authorize access="hasRole('MANAGER')">
	<!-- link to point to /leaders (managers) -->

	<p>
		<a href="${pageContext.request.contextPath}/leaders">Leadership meeting</a>
		(Only for Managers)
	</p>
	</security:authorize>
	
	
	<security:authorize access="hasRole('ADMIN')">
	<!-- link to point to /systems (admins) -->
	<p>
		<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
		(Only for Admins)
	</p>

	</security:authorize>
	
	<hr>

	<!-- logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
		method="POST">
		
		<input type="submit" value="Logout" />
	
	</form:form>

</body>
</html>