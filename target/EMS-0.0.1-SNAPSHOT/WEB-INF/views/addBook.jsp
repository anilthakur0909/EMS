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
			<form class="form-group" method="post" id="ProcessBook" modelAttribute="book" action="processBook">		   		
			  		 <label for="name" class="sr-only">Id</label> 
			  		 <input type="text" name="id" id="id" class="form-control" placeholder="id" required autofocus /> 
			   		<label for="title" class="sr-only">Title</label> 
			  		 <input type="text" name="title" id="title" class="form-control" placeholder="title" required /> 
			  		 <label for="author" class="sr-only">Author</label> 
			  		 <input type="text" name="author" id="author" class="form-control" placeholder="author" required />
			   		<label for="releaseDate" class="sr-only">Release Date</label> 
			  		 <input type="text" name="releaseDate" id="releaseDate" class="form-control" placeholder="releaseDate" required autofocus />			  				   		
			   		<button class="btn btn-lg btn-primary btn-block" type="submit">Add Book</button>
			  		 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			  </form>
			<br />
		</div>
	</div>
		 <jsp:include page="footer.jsp" />
</body>
</html>