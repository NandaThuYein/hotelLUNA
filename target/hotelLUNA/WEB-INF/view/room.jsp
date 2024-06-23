<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/e09933254b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/hotelLUNA/toreachresourcefolder/CSS/room.css">
    
    <title>Room</title>
</head>
<body>
    <!-- header section start -->
    <section class="header ">
        <div class="title max-width">
            <h1>Luna</h1>
            <h2>HOTEL</h2>
            <div class="star">
                <img src="/hotelLUNA/toreachresourcefolder/Image/pngegg (2).png" alt="">
                <img src="/hotelLUNA/toreachresourcefolder/Image/pngegg (2).png" alt="">
                <img src="/hotelLUNA/toreachresourcefolder/Image/pngegg (2).png" alt="">
                <img src="/hotelLUNA/toreachresourcefolder/Image/pngegg (2).png" alt="">
                <img src="/hotelLUNA/toreachresourcefolder/Image/pngegg (2).png" alt="">
            </div>
            <h3>YANGON</h3>
            <span>Rooms and Suites</span>
        </div>
       
         <form:form action="/hotelLUNA/homeSearchRoom" modelAttribute="sroom" method="POST"> 
         <section class="book">
       
            <div class="max-width flex">
                <div class="input grid">
                    <div class="box">
                         <label for="">Check-in:</label><br>
                        <form:input type="date" path="checkIn" placeholder="Check-in-Date" value="${cIn}" />
                     
                     </div>
                     <div class="box">
                        <label for="">Check-out:</label><br>
                      <form:input type="date" path="checkOut" placeholder="Check-out-Date" value="${cOut }" />
                      
                    </div>
                     <div class="box">
                        <label for="">Adults</label><br>
                         <form:input type="number" path="adultNumber" placeholder="0" value="${adultN }" />
                       
                    </div>
                    <div class="box">
                        <label for="">Children</label><br>
                         <form:input type="number" path="childrenNumber" placeholder="0" value="${cNum}" />
                        
                    </div>
               
                <div class="search">
                    <input type="submit" name="" value="Search Room">
                </div>
             </div>
              </div>
       
        </section>
       </form:form> 
    </section> 
     
    <!-- room section start -->
  
  <c:forEach items="${list}" var="data" >
    <section class="room" id="room">
           
                <div class="card">
                <div class="cardimg">
                    <img src="/hotelLUNA/toreachresourcefolder/Image/alexander-kaunas-67-sOi7mVIk-unsplash.jpg" alt="" />
                </div>
               
                
                    <div class="content">
                        <div class="text">
                            <h1>${data.rtypename }</h1>
                            <p>
                                ${data.rprice }
                                <span class="price">Ks</span>
                                <span class="">/per night</span>
                                <p class="aboutroom">This is the ultimate room, a flat TV and a spacious area, which will be more than enough for a company</p>
                            </p>
                             <div class="itt">
                        <div class="icon carparking">
                            <div class="tooltip">Carparking</div>
                            <span><i class="fa-solid fa-car"></i></span>
                        </div>
                        <div class="icon wifi">
                            <div class="tooltip">FreeWifi</div>
                            <span><i class="fa-solid fa-wifi"></i></span>
                        </div>
                        <div class="icon pool">
                            <div class="tooltip">Pool</div>
                            <span><i class="fa-solid fa-water-ladder"></i></span>
                        </div>
                        <div class="icon entertainment">
                            <div class="tooltip">Entertainment</div>
                            <span><i class="fa-solid fa-person-booth"></i></span>
                        </div>
                        <div class="icon television">
                            <div class="tooltip">Television</div>
                            <span><i class="fa-solid fa-tv"></i></span>
                        </div>
                        <div class="icon kitchen">
                            <div class="tooltip">Kitchen</div>
                            <span><i class="fa-solid fa-kitchen-set"></i></span>
                        </div>
                    </div>
                        <div class="search">
                        <a href="/hotelLUNA/bookServlet/${data.rid}/${cIn}/${cOut}/${adultN}/${cNum}">
                        <input type="button" name="" value="Book">
                        </a>
                    	</div>
                    
                        </div>
                    
                    </div>
      </div>
        </section>
             </c:forEach>
   
    <script type="text/javascript" src="Java Script/script.js"></script>
</body>
</html>