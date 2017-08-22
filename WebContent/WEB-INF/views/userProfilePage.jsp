<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="bootstrapLinks.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>welcome page</title>
</head>
<body>
	<div class="container">
		<jsp:include page="headerUserLogin.jsp" />
		<div class="jumbotron">

			<h1>Welcome User</h1>
			<c:if test="${not empty error}">
				<div class="text-danger">${error}</div>
			</c:if>
			<c:if test="${not empty message}">
				<div class="text-success">${message}</div>
			</c:if>

			<h2>Username:<c:out value="${username}"/></h2>
						<h2>List of Employees(Age>26 and Asc Order by Age)</h2>
			<table id="employees" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<tr>
					<th>Name</th>
					<th>Age</th>
					<th>Email</th>
				</tr>
				<c:forEach items="${employees}" var="employee">
					<tr>
						<td>${employee.name}</td>
						<td>${employee.age}</td>
						<td>${employee.email}</td>
					</tr>
				</c:forEach>
			</table>
			<br />
		</div>
	</div>
		 <jsp:include page="footer.jsp" />
</body>
</html>