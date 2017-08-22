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
			<h1>Welcome To EMS</h1>
			<p>
				<img alt="Error" src="<c:url value="/resources/images/ems.jpg" />">
			</p>
		
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>