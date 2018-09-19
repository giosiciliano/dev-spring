<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<title>
		List Customers
	</title>

	<!-- reference our style sheet 	-->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

<div id="wrapper">
	<div id="header">
		CRM - Customer Relationship Manager
	</div>
	
	<div id="container">
		<div id="content">
		
		<!-- add button -->
		<input type="button" value="Add Customer"
			onclick="window.location.href='showFormForAdd'; return false"
			class="add-button"
		/>
		
		<!--  add a search box -->
        <form:form action="search" method="POST">
        	Search customer: <input type="text" name="theSearchName" />
                
            <input type="submit" value="Search" class="add-button" />
        </form:form>
		
		<!-- add html table here -->
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Action</th>
			</tr>
			
			<!--  loop thru customers for print -->
			<c:forEach var="tempCustomer" items="${customers}">
				
				<!--  construct an update link with customer id -->
				<c:url var="updateLink" value="/customer/showFormForUpdate">
					<c:param name="customerId" value="${tempCustomer.id}" />
				</c:url>
				
				<!--  construct a delete link with customer id -->
				<c:url var="deleteLink" value="/customer/delete">
					<c:param name="customerId" value="${tempCustomer.id}" />
				</c:url>
				
				<tr>
					<td>${tempCustomer.firstName}</td>
					<td>${tempCustomer.lastName}</td>
					<td>${tempCustomer.email}</td>
					
					<td>
						<!-- display the update link -->
						<a href="${updateLink}">Update</a>
						|
						<a href="${deleteLink}"
							   onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
					</td>
				</tr>
			
			</c:forEach>
			
		</table>
		
		</div>
	</div>
</div>

</body>

</html>