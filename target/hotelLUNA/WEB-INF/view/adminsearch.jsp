<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     	 <%@ page import="java.util.Date" %>
                <%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="/hotelLUNA/toreachresourcefolder/CSS/style.css" />
	<link rel="stylesheet" type="text/css" href="/hotelLUNA/toreachresourcefolder/CSS/container.css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Admin Search </title>

<script type="text/javascript">

    function searchList(flag)
    {
        if(flag==1){
             document.getElementById('list').style.display="block";
        
        }else{
            document.getElementById('list').style.display="none";
           
        }
    }
	function resetForm()
	{
		document.getElementById('message').innerHTML = "Message";
		document.getElementById('list').style.display="none";
	}

	function checkDelete()
    {
        var con = confirm("Are you sure to delete?");
        if(con)
        {
            window.location.replace('adminsearch.jsp?flag=1')
        }

    }
	
</script>
<style type="text/CSS">
	#header{
		box-sizing: border-box;
		margin: 20px 20px 40px 20px;
		font-size: 30px;
		display: flex;
		justify-content: space-between;
		color: sienna;
	}

	body{
		 background-color: #003945;
		/* background-image:url(https://www.hotelsinheaven.com/wp-content/uploads/2020/10/oneonly-the-palm-dubai-hotel-pool-1500x842.jpg); */
		background-repeat:no-repeat;
	}

	table{
	margin-left:50px;
	border:3px solid #1a4488;
	color:white;
		width: 100vh;
	}
	
	table td{
		border:3px solid #1a4488;
		color:white;
	}

</style>
</head>
<body class="main_body">

	<div id="header">
        <div id="title">
            <a href="/hotelLUNA/adminViewPage" style="color:white;">Hotel Management System</a>
        </div>
          <div id="menuLoginTime">
            <table>
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
   <a href="/hotelLUNA/logout" class="btn btn-outline-primary">Log Out</a>  
    </div>

    <div id="container"> 
            <div id="main_contents">     
                <div id="contents">
                <div class="search_form">
				<h3 style="color: #fff;">User Search</h3>
			
				<form:form action="/hotelLUNA/search" method="POST" modelAttribute="admin">
				<table class="adminreg">
					<tr>
						<td class="label"><label>User ID</label></td>
						<td class="label">
						<form:input path="id" />
						</td>
	
						<td class="label">User Name	</td>
						<td class="label">
						<form:input path="aname" />
						</td>
						
					</tr>
				</table>
				<br/>
				<input type="submit"  value="Search" onclick="searchList(1)" class="button"/>
				</form:form>
				<a href="/hotelLUNA/toinsert">
                    <input type="button" value="Add" class="button" id="userInsert" />
                 </a>
                 <a href="/hotelLUNA/admin">
                 
				<input type="button"  value="Reset" onclick="resetForm()" class="button"/>
				</a>
				
				<br/>
				<br/>	
				<div id="error">
					<label id="message" style="color: #fff;">Message</label>
				</div>
			</div>   
                <br/><br/><br/>
			<div id="list">
				<form name="listForm"  >
					<table class="resultTable1">
						<tr class="tblHeader">
							
							<th width="30%">User ID</th>
							<th width="30%">User Name</th>
							<th width="5%">Delete</th>
							<th width="5%">Update</th>
							
						</tr>
						
						<c:forEach var="a" items="${ad}">
						<tr>
							<td>${a.id}</td>
							<td>${a.aname }</td>
							<td>${a.password }</td>
						 	<td>
						 	<a href="/hotelLUNA/deletea/${a.id }">
                             	<input type="button" value="Delete" id="delete" class="button" onclick="javascript:checkDelete()" />
                            </a>
                            </td>
                            
							<td>
								<a href="/hotelLUNA/toupdate/${a.id }">
                            		<input type="button" value="Update" class="button" id="userUpdate" />
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
            <span>Copyright &#169; ACE Inspiration 2022</span>        
    </div>


     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>