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
	<title>Registration List</title>
		
	<link href="https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	<link rel="stylesheet" type="text/css" href="/hotelLUNA/toreachresourcefolder/CSS/style.css" />
	<link rel="stylesheet" type="text/css" href="/hotelLUNA/toreachresourcefolder/CSS/container.css" />
	
	
	<script type="text/javascript">
		 function searchList(flag)
    {
        if(flag==1){
             document.getElementById('bookinglist').style.display="block";
        
        }else{
            document.getElementById('bookinglist').style.display="none";
           
        }
    }
	function resetForm()
	{
		document.getElementById('message').innerHTML = "Message";
		document.getElementById('bookinglist').style.display="none";
	}

	function checkDelete()
    {
        var con = confirm("Are you sure to delete?");
        if(con)
        {
            window.location.replace('bookinglist.jsp?flag=1')
        }

    }
	</script>
	<style type="text/css">
	.roomscroll
	{		
			margin-left: 100px;
			margin-right: 200px;
    		overflow-y: scroll;
    		height: 300px;
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
                <h3 style="color:brown;">Registeration List</h3>
                
              <form:form action="/hotelLUNA/searchrbcustomer" method="Post" modelAttribute="rebo">
                	<table class="tableForm">
                		<tr>
							<td class="bookId"><label>Registration ID</label></td>
							<td class="idInput">
							<form:input path="rb.rbid" required="required"/>
							</td>
						
							<td class="customerId"> Customer Name</td>
							<td class="idInput">
							<form:input path="cname" required="required"/>
							</td>
						
						</tr>
                	</table>
                <br/>
					<input type="submit"  value="Search" onClick="searchList(1)" class="button"/>
					<input type="reset"  value="Reset" onClick="resetForm()" class="button"/>
                </form:form>
                <div id="errormsg">
					<label id="message">Message</label>
				</div>

				  <br/><br/><br/>

                <div id="bookinglist">
                <div class="roomscroll">
                	<form name="listForm">
                		<table class="resultTable">
                			<tr class="tblHeader">
							
							<th width="15%">Registration ID</th>
							<th width="15%">Customer ID</th>
							<th width="15%">Room Number</th>
							<th width="15%">Checkin</th>
							<td width="">Checkout</td>
							<td width="">Total Price</td>
							<td width="">Status</td>							
							<th width="5%">Delete</th>
							<th width="3%">Update</th>
							<th width="7%">Status</th>
							
						</tr>
					
						<c:forEach var="t" items="${rb}">					
						<tr>
							<td>${t.rb.rbid}</td>
							<td>${t.cid}</td>
							<td>${t.room.rid }</td>
							<td>${t.rb.cin }</td>
							<td>${t.rb.cout }</td>
							<td>${t.rb.tprice }</td>
							<td>${t.rb.status }</td>
						
						
						<td>	
						<a href="/hotelLUNA/deletereg/${t.cid}/${t.rb.rbdid}">
                             	<input type="button" value="Delete" id="delete" class="button" onClick="javascript:checkDelete()" />
                           </a>
							<td>
								<a href="/hotelLUNA/updatereg/${t.cid}/${t.rb.rbdid}">
                            		<input type="button" value="Update" class="button" id="userUpdate" />
                        		</a>
                        	</td>
                        	<td>
                        		<a href="/hotelLUNA/aandireg/${t.cid}/${t.rb.rbdid}/${t.rb.status }">
                        			<input type="button" value="${t.rb.status }" id="active" class="button" />
                        		</a>
                        	</td>
						</tr>
						</c:forEach>
						
					
						 	
                		</table>
                	</form>
                	</div>
                </div>
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