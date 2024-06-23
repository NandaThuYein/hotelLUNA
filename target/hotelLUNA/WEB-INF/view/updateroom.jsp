<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ page import="java.util.Date" %>
      <%@ page import="java.text.SimpleDateFormat" %>
 <!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Update Room</title>
<link href="https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	<link rel="stylesheet" type="text/css" href="/hotelLUNA/toreachresourcefolder/CSS/style.css" />
	<link rel="stylesheet" type="text/css" href="/hotelLUNA/toreachresourcefolder/CSS/container.css" />


<script type="text/javascript">
   
	
	function update()
    {
        var con = confirm("Are you sure to register?");
        if(con)
        {
            window.close();
            window.location.replace('searchroom.jsp')
        }
        
    }
</script>
<style type="text/css">
	table td{
		border-collapse: collapse;
		border: 1px solid black;
		text-align: center;
  		vertical-align: center;
  		width: auto;
  		height: 30px;
	}
</style>
</head>
<body>
<div class="header">
		<div class="title">
			<h3>Hotel Registration and Booking Management</h3>
		</div>

		<div id="menuLoginTime">
            <table>
                <tr>
                    <td>User</td><td>: ${sessionScope.adminName}</td>
                </tr>
                <tr>
                     <td>Current Date</td>
                    <td>:
                     <%
                    SimpleDateFormat formatter = new SimpleDateFormat();
                    Date date = new Date();
                    out.println(formatter.format(date));
                    %>
                     </td>
                </tr>
            </table>
        </div>
	</div>
	<div class="sidebar">

		<div class="logo-details">
			<span class="logo_name">Lunar Hotel</span>
		</div>

		<ul class="nav-links">
			<li>
			<a href="/hotelLUNA/admin" class="active"><span class="links_name">Admin Dashboard</span></a>
			</li>
  
      	  <li class="log_out">
          <!-- <a href="#"> <i class='bx bx-log-out'></i><span class="links_name">Log out</span></a> -->
         <!--  <button class="btn btn-outline-primary"><a href="LoginPage.html"></a>Log Out</button> -->
         <a href="/hotelLUNA/logout" class="btn btn-outline-primary">Log Out</a>
          </li>


		</ul>


		<div class="dropdown">
		<button class="btn btn-danger btn-md rounded dropdown-toggle" data-bs-toggle="dropdown">Customer List</button>
		<ul class="dropdown-menu">
			<li><a href="/hotelLUNA/customerlist" class="btn btn-outline-secondary">Customer List Page</a></li>
			
		</ul>
		</div>

		<br/>

		<div class="dropdown">
		<button class="btn btn-danger btn-md rounded dropdown-toggle" data-bs-toggle="dropdown">Room Editing</button>
		<ul class="dropdown-menu">
			<li><a href="/hotelLUNA/addroom" class="btn btn-outline-secondary">Add Room</a></li>
			<li><a href="/hotelLUNA/searchroom" class="btn btn-outline-secondary">Search Room</a></li>
			
		</ul>
		</div>

		<br/>

		<div class="dropdown">
		<button class="btn btn-danger btn-md rounded dropdown-toggle" data-bs-toggle="dropdown">Room Register</button>
		<ul class="dropdown-menu">
			<li><a href="/hotelLUNA/bookinglist" class="btn btn-outline-secondary">Booking List</a></li>
			<li><a href="/hotelLUNA/registrationlist" class="btn btn-outline-secondary">Registration List</a></li>
		</ul>

		</div>

	</div>

<div class="container"> 
            <div class="main_contents">     
                <div class="contents"> 
                <h3 style="color:brown;">Update Room</h3>
                 <label id="errormsg" > Message </label><br/><br/><br/>
              
                <form:form action="/hotelLUNA/updateroomdata" method="POST" modelAttribute="room">
          
                	<table class="tableForm">
                		<tr>
                			<td class="label">Room ID</td>
                			<td class="idInput"><form:input path="rid"/></td>
                		</tr>
                		<tr>
                			<td class="label">Capacity</td>
                			<td class="nameInput"><form:input path="rcapacity"/></td>
                		</tr>
                		<tr>
                			<td class="label">Price</td>
                			<td class="price"><form:input path="rprice"/></td>
                		</tr>
                		<tr>
                			<td class="label">available</td>
                			<td class="nameInput"><form:input path="avaliable"/></td>
                		</tr>
                		<tr>
                			<td class="label">Room Type</td>
                			<td class="roomType">
                		<form:select path="rtype">
                			<form:option value="1" label="Luxury"></form:option>
                			<form:option value="2" label="Normal"></form:option>
                			<form:option value="3" label="Basic"></form:option>
                		</form:select>
                				
                			</td>
                		</tr>
              
                	</table>
                	<br/>
								
					<input type="submit" value="Update" class="button" onClick="javascript:update()"/>
					<a href="/hotelLUNA/searchroom">
					<input type="button" value="Back" class="button" onClick="window.location.replace('customerlistpage.jsp')"/>
                    </a>
                    </form:form>

                </div>

            </div> 

            <div class="footer">
				 <span class="footer">Copyright &#169; ACE Inspiration 2022</span>
			</div>
	</div>












	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>