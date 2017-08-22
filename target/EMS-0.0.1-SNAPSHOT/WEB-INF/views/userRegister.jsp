<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="bootstrapLinks.jsp" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register User</title>
</head>
<body>
<jsp:include page="headerAdminHome.jsp" />
	<div class="container">
		 <div class="jumbotron">
		 <h2 class="form-signin-heading">User Registration</h2>
			<div class="checkbox checkbox-primary">
				<label> <input id="toggleEmployee" type="checkbox" checked="true"><span>Regular	Employee (Unchecked for Contract)</span>
				</label>
			</div>
			<div id="regularEmployee">
			<form class="form-signin" method="post" id="regForm1" modelAttribute="regularEmployee" action="registerProcess">		   		
			  		 <label for="name" class="sr-only">Name</label> 
			  		 <input type="text" name="name" id="name" class="form-control" placeholder="Name" required autofocus /> 
			   		<label for="inputEmail" class="sr-only">Email</label> 
			  		 <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required /> 
			  		 <label for="age" class="sr-only">Age</label> 
			  		 <input type="text" name="age" id="age" class="form-control" placeholder="Age" required />
			   		<label for="username" class="sr-only">UserName</label> 
			  		 <input type="text" name="username" id="username" class="form-control" placeholder="UserName" required autofocus />
			  		 <label for="inputPassword" class="sr-only">Password</label> 
			   		<input type="password" name="password" id="password" class="form-control" placeholder="Password" required/> 
			   		
			   		<label for="salary" class="sr-only">Salary</label> 
			   		<input type="text" name="salary" id="salary" class="form-control" placeholder="Salary" required />
			   		
			   		<label for="bonus" class="sr-only">Bonus</label> 
			   		<input type="text" name="bonus" id="bonus" class="form-control" placeholder="bonus" required />
			   		
			   		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
			  		 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			  </form>
			  </div>
			  <div id="contractEmployee" style="display: none;">
			  	<form class="form-signin" method="post" id="regForm2" modelAttribute="contractEmployee" action="registerProcess">
			  		 <label for="name" class="sr-only">Name</label> 
			  		 <input type="text" name="name" id="name" class="form-control" placeholder="Name" required autofocus /> 
			   		<label for="inputEmail" class="sr-only">Email</label> 
			  		 <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required /> 
			  		 <label for="age" class="sr-only">Age</label> 
			  		 <input type="text" name="age" id="age" class="form-control" placeholder="Age" required />
			   		<label for="username" class="sr-only">UserName</label> 
			  		 <input type="text" name="username" id="username" class="form-control" placeholder="UserName" required autofocus />
			  		 <label for="inputPassword" class="sr-only">Password</label> 
			   		<input type="password" name="password" id="password" class="form-control" placeholder="Password" required/> 
			   		
			   		<label for="payPerHour" class="sr-only">Pay Per Hour</label> 
			   		<input type="text" name="payPerHour" id="payPerHour" class="form-control" placeholder="payPerHour" required />
			   		
			   		<label for="contractDuration" class="sr-only">Contract Duration</label> 
			   		<input type="text" name="contractDuration" id="contractDuration" class="form-control" placeholder="contractDuration" required />
			   		
			   		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
			  		 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			  </form>
			  
			  </div>
		 </div>
	 </div>
	 <jsp:include page="footer.jsp" />
	 
	 <script type="text/javascript">
	$("#toggleEmployee").change(function() {
		if (this.checked) {
			$("#contractEmployee").hide();
			$("#regularEmployee").show();
		} else {
			$("#regularEmployee").hide();
			$("#contractEmployee").show();
		}
	});
	
</script>
</body>
</html>
