<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/e09933254b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/hotelLUNA/toreachresourcefolder/CSS/finalreceipt.css">
    <title>Document</title>
    <style type="text/css">
    	#printarea *{
			visibility: visible;
		}
		
		.subtotal{
			margin-right:30px;
		}
		
		.printbtn{
			border-radius:5px;
		}
		
		#btncan{
			background:red;
			color:white; 	
			padding:5px;
			border-radius:5px;
		}
    </style>
</head>
<body class="max-width">
    <section class="all">
        <div class="rtop">
            <div class="title">
                <h1>LUNA</h1>
                <h5>YANGON</h5>
            </div>
            
            <div class="thanks">
                <i class="fa-solid fa-circle-check" style="margin-right: 5px;"></i>
                <p>Thanks, <span>${sessionScope.cName}</span>! Your reservation is made.</p>
            </div>
            <a href="/hotelLUNA/customRoomRegistration">
            <button class="printbtn" onclick="printme()"><i class="fa-solid fa-print">Print</i></button>
            </a>
        </div>

        <div class="rmid">
            <div class="rleft">
                <div class="rdata">
                    <h1 style="margin-bottom: 25px;">Reservation Data</h1>
                    <p>Name : <span>${sessionScope.cName}</span></p>
                    <p>E-mail : <span>${sessionScope.cEmail }</span></p>
                    <p>Phone: <span>${sessionScope.cPhone}</span></p>
                    <p>NRC.No:  <span>${sessionScope.cNRCno}</span></p>
                </div>
                <div class="hotelinfo">
                    <h1 style="margin-bottom: 20px;">Our Address and phone number</h1>
                    <i style="margin-bottom: 15px;" class="fa-solid fa-location-dot"><span>Myanmar, Yangon, Minthantkyaw Street</span></i><br>
                    <i style="margin-bottom: 15px;" class="fa-solid fa-phone" >092003810</i><br>
                    <a style="margin-bottom: 15px; color: #49B3FF;" href="/hotelLUNA/" >Our Website</a>
                </div>
            </div>
            <div class="rright">
                <div class="date">
                    <div class="checkin">
                        <h5>Check-in</h5>
                        <h3>${sessionScope.checkIn}</h3>
                    </div>
                    <div class="line"></div>
                    <div class="checkout">
                         <h5>Check-out</h5>
                         <h3>${sessionScope.checkOut}</h3>
                    </div>
                </div> 
                
                <div class="roomscroll">
                <c:forEach items="${sessionScope.list }" var="data">
                    <div class="room">
                        <div class="roomtotal">
                            <div class="fakeimg"></div>
                            <div class="content">
                                <h3>${data.roomType } Room</h3><br>
                                <div class="person top">
                                    <i class="fa-solid fa-user-large"><span style="margin-left: 5px;">${data.adultNumber}</span></i>
                                    <i class="fa-solid fa-child"><span style="margin-left: 5px;">${data.childNumber}</span></i>
                                </div>
                                <h4 class="top"><span>${data.perNight}</span> Nights Offer</h4>
                                <h3 class="subtotal">Subtotal-<span>${data.roomTotalPrice}</span>Ks</h3>
                            </div>
                            <div clas="canceldiv">
                            	<a href="/hotelLUNA/cancelRoomPage/${data.roomId}/${data.roomTotalPrice}">
                           	 		<input type="button" id="btncan" value="Cancel"> 
                            	</a>
                            </div>
                        </div>
                        <div style="width: 670px; background-color: rgba(255, 255, 255, 0.25); height: 2px; margin: auto;"></div>
                    </div>
                    </c:forEach>
                </div>
              
                <div class="reservationtotal">
                    <h1>Reservation total</h1>
                    <h1><span>${sessionScope.allTotalPrice}</span>Ks</h1>
                </div>          
        </div>
        
       </div>
   
    </section>
    <div class="comfirm">
    	<a href="/hotelLUNA/customRoomRegistration"><button class="cancelbtn">Sure To Booking</button></a>
    </div>
    <div class="button">
        <a href="/hotelLUNA/cancelPage"><button class="cancelbtn">Cancel this reservation</button></a>
        
     	<a href="/hotelLUNA/"><button class="cancelbtn">Continue Select Room</button></a>
 	</div>
    <script type="text/javascript">

	//call button onclick 
	function printme(){
		//console.log('hay');
		window.print();
	}
    </script>
</body>
</html>
