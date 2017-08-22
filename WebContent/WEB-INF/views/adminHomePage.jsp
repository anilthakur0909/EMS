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
		<jsp:include page="headerAdminHome.jsp" />
		<div class="jumbotron">
			<h1>EMS Admin</h1>

			<h2>Employees</h2>
			<table id="employees" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<tr>
					<th>EmployeeId</th>
					<th>Name</th>
					<th>Username</th>
					<th>Password</th>
					<th>Age</th>
					<th>Email</th>
					<th>Delate</th>
				</tr>
				<c:forEach items="${employees}" var="employee">
					<tr>
						<td>${employee.employeeId}</td>
						<td>${employee.name}</td>
						<td>${employee.username}</td>
						<td>${employee.password}</td>
						<td>${employee.age}</td>
						<td>${employee.email}</td>
						<td><a href="${pageContext.request.contextPath}/deleteEmployee/${employee.employeeId}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<a href="${pageContext.request.contextPath}/userRegister" class="btn btn-primary" role="button">Register User</a>
		</div>
	</div>

	 <jsp:include page="footer.jsp" />
	 
	 <script type="text/javascript">
$(document).ready(function() {
    // Setup - add a text input to each footer cell
    $('#employees tfoot th').each( function () {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
    } );
 
    // DataTable
    var table = $('#example').DataTable();
 
    // Apply the search
    table.columns().every( function () {
        var that = this;
 
        $( 'input', this.footer() ).on( 'keyup change', function () {
            if ( that.search() !== this.value ) {
                that
                    .search( this.value )
                    .draw();
            }
        } );
    } );
} );
</script>
	 
</body>


</html>