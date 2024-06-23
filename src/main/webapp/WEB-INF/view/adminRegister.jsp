<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Register</title>
	<link rel="stylesheet" type="text/css" href="/hotelLUNA/toreachresourcefolder/CSS/admin.css">
</head>
<body>
	<div class="admreg">
		<div>
		<h1 title="Welcome!!"><i><u>HotelLUNA</u></i></h1>
		</div>
	<div id="tt">
		
		<h2 title="Hello!"><i>Register Form</i></h2>

	</div>

		<div class="tbform">
	
		<form:form action="/hotelLUNA/insertadmin" method="POST" modelAttribute="admin">

			<table class="register">

				<tr>
				<td><b>User ID</b></td>

				<td id="cuid"><form:input path="id" id="input" placeholder="User ID" required="required" /></td>
				</tr>

				<tr>
				<td><b>User Name</b></td> 
				<td id="input"><form:input path="aname" id="input" placeholder="User Name" required="required" /></td>
				</tr>

				<tr>
				<td><b>Password</b></td>
				<td id="input"><form:input type="password" path="password" id="input" placeholder="User Password" required="required" /></td>
				</tr>

				<tr>
				<td class="button" title="Are you sure?"><input type="submit" name="Submit" class="btn" value="Register" /></td>

			</table>

		</form:form>

		</div>
		
	</div>
</body>
</html>