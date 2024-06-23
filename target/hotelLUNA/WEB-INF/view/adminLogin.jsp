<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>

<link rel="stylesheet" type="text/css" href="/hotelLUNA/toreachresourcefolder/CSS/admin.css">

</head>

<body>
	<div class="login">

		<div id="header">

		<div id="title">
			<i>Hotel Management System</i>
		</div>

		</div>

	</div>

	<div class="img">
		<h3>WELCOME</h3>

	<div id="container">
		<div id="main_contents">

			<div class="tb1">
				
					<table class="admintb">

						<thead>
							<h1><i>HotelLUNA's</i></h1>
							<h2><i>Admin Login</i></h2>
						</thead>

						<tr>
							<td id="errormsg">${wrong}</td>
						</tr>

						<form:form  action="/hotelLUNA/process" modelAttribute="admin" method="POST">
						
						<tr>
							<td id="id"><b>Admin ID</b></td>
							<td>
								<form:input path="id" required="required" placeholder="Admin ID" />
								<form:errors path="id" style="color:red;"></form:errors>
							</td>
						</tr>
						
						<tr>
							<td id="psw"><b>Password</b></td>
							<td>
								<form:input type="password" path="password" required="required" placeholder="Admin Password" />

								<form:errors path="password" style="color:red;"></form:errors>
							</td>
						</tr> 
					
						<tr>
							<td>
								<input type="submit" name="Submit" id="sub" class="button" value="LOGIN" />
							</td>
						</tr>

						</form:form>
					</table>	
			</div>
		</div>
	</div>
</div>
</body>
</html>