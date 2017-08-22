<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="bootstrapLinks.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>welcome page</title>
</head>
<body>
	<div class="container">
		<jsp:include page="headerHome.jsp" />
		<div class="jumbotron">
			<h1>EMS Library</h1>
			
			<h2>Elastic Search</h2>
			<table id="books" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<tr>
					<th>id</th>
					<th>title</th>
					<th>author</th>
					<th>releaseDate</th>
				</tr>
				<c:forEach items="${books}" var="book">
					<tr>
						<td>${book.id}</td>
						<td>${book.title}</td>
						<td>${book.author}</td>
						<td>${book.releaseDate}</td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<a href="${pageContext.request.contextPath}/addBook" class="btn btn-primary" role="button">Add Book</a>
		</div>
	</div>
		 <jsp:include page="footer.jsp" />
</body>
</html>