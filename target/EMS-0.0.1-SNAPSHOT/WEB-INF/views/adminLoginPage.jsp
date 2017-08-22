<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<jsp:include page="bootstrapLinks.jsp" />
</head>

<body onload='document.loginForm.username.focus();'>
	<jsp:include page="headerAdminLogin.jsp" />
	<div class="container"><br/>
		<div class="jumbotron">
			<c:if test="${not empty error}">
				<div class="text-danger">${error}</div>
			</c:if>
			<c:if test="${not empty message}">
				<div class="text-success">${message}</div>
			</c:if>
			
			<form class="form-signin" name='login' action="<c:url value='/adminLoginPage' />" method='POST'>
				<h2 class="form-signin-heading">EMS Admin Login</h2>

				<label for="username" class="sr-only">UserName</label> 
				<input type="text" name="username" id="username" class="form-control"
					placeholder="UserName" required autofocus /> 
					
				<label for="inputPassword" class="sr-only">Password</label> 
				<input type="password" name="password" id="password" class="form-control"
					placeholder="Password" required />
					
				<button class="btn btn-lg btn-primary btn-block" type="submit">Signin</button>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</div>
		 <jsp:include page="footer.jsp" />
</body>
</html>