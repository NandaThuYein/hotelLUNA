<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/e09933254b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/hotelLUNA/toreachresourcefolder/CSS/booking.css">
    <title>Booking</title>
    <style type="text/CSS">
    	.book #btn{
    		padding:10px;
    		width:100px;
    		color:#ffff;
    		font-size:20px;
    		background-color:#004755;
    		border-radius:5px;
    	}
    	
    	#btn:hover{
    		background-color:rgb(0, 255, 195, 0.5);
    		color:#004755;
    		
    	}
    </style>
</head>
<body> 
    <form:form action="/hotelLUNA/customBooking" modelAttribute="book" method="POST">
    <div class="bgpng">
        <img src="/hotelLUNA/toreachresourcefolder/Image/reservation/cherry-563.png" alt="">
    </div>
    <div class="info max-width">
        <section class="reservation">
            <div class="title">
                <h1>Luna</h1>
                <span>Reservation Data</span>
            </div>
            <div class="wrapper">
                <div class="input-data">
                <form:input type="text" path="customName" required="required" value="${sessionScope.cName}" />
                <form:errors path="customName" style="color: red;"></form:errors>
                <div class="underline"></div>
                <label>Name</label>
                </div>

                <div class="input-data">
                    <form:input type="text" path="customEmail" required="required" value="${sessionScope.cEmail}" />
                    <form:errors path="customEmail" style="color: red;"></form:errors>
                    <div class="underline"></div>
                    <label>E-mail</label>
                </div>

                <div class="input-data">
                    <form:input type="text" path="customPhone" required="required" value="${sessionScope.cPhone}" />
                    <form:errors path="customPhone" style="color: red;"></form:errors>
                    <div class="underline"></div>
                    <label>Phone Number</label>
                </div>

                <div class="input-data">
                    <form:input type="text" path="customNRCno" required="required" value="${sessionScope.cNRCno}" />
                    <form:errors path="customNRCno" style="color: red;"></form:errors>
                    <div class="underline"></div>
                    <label>NRC.No</label>
                </div>

                <div class="comment">
                    <textarea id="message" cols="41" rows="4" placeholder="We will try to handle your requests, but we cannot always guaramtee it"></textarea>
                    <label>Comment</label>
 					</div>
 					 <div class="book">
                <input type="submit" value="Book" id="btn" style="margin-top: 30px;" />
           			 </div>
 					</div>
        </section>
        <section class="receipt">
            <div class="date">
                <div class="checkin">
                    <h5>Check-in</h5>
                    <h3 id="checkIn"><form:input type="date" path="bookCheckIn" id="checkin" value="${checkIn}" onchange="getNumberOfDays(this.value)"  /></h3>
                </div>
                <div class="line"></div>
                <div class="checkout">
                    <h5>Check-out</h5>
                    <h3 id="checkOut"><form:input type="date" path="bookCheckOut" id="checkout" value="${checkOut}" onchange="getNumberOfDays(this.value)" /></h3>
                </div>
            </div>
            <div class="room">
                <div class="roomtotal">
                    <div class="fakeimg"></div>
                    <div class="content">
                        <h3><span>${type}</span> Room</h3><!-- Selected Room  Types -->
                        <h3>Basic Room Price : <span>${price}</span>Ks</h3> <!-- selected room price -->
                        <div class="person top">
                                                                                             <!-- adult nunber -->
                            <i class="fa-solid fa-user-large"><span style="margin-left: 5px;">Adult no : ${aNumber}</span></i>
                                                                                         <!-- child number -->
                            <i class="fa-solid fa-child"><span style="margin-left: 5px;">Child no : ${cNumber}</span></i>
                        </div>
                        <br><br>
                        <form:hidden path="roomId" value="${roomid}"/>
                        <form:hidden path="roomPrice" value="${price}" id="rPrice" />
                        <form:hidden path="roomType" value="${type}" />
                         <form:hidden path="adultNumber" value="${aNumber}" />
                          <form:hidden path="childNumber" value="${cNumber}" />
                        <form:hidden path="perNight" id="pernight" />
                         <input type="hidden" name="mode" value="PinRequest" />
                           <%--  <label class="w3-text-green"><b>Number Of Room</b></label>
                              <form:select name="tot_pin_requested" path="roomCount" id="numberSelect" onchange="findTotalcalculateAmount(this.value)" required="required">
                              <form:option value="1">1</form:option>
                               <form:option value="2">2</form:option>
                                  <form:option value="3">3</form:option>
                             </form:select> --%>
                             <br><br>
                        <label><b>Total Stay Day</b> </label><h1 id="goeshere" style="display:inline;">message</h1>
                        <br><br>
                         <label><b>Total SUM Of Amount</b></label>
                        <form:input class="w3-input w3-border"  path="bookTotalPrice" id="totalValue"  value="" readonly="readonly" />
                    </div>
                </div>
                <div style="width: 615px; background-color: rgb(0, 116, 85, 0.25); height: 2px; margin: auto;"></div>
            </div>
            <!-- <div class="reservationtotal">
            <h1>Reservation Total</h1>
            <h1 id="totalPrice">message</h1><h1>Ks</h1>
            </div> -->
            </section>
            </div>
             </form:form>
    <script>
    
    let divobj = document.getElementById('totalValue');
    let pnight = document.getElementById('pernight');
    let roomPrice = document.getElementById('rPrice').value;
         //Calculate with room price and stayDate

              function getNumberOfDays(start, end) {

                    //Date > Can Acess Only Number
                    let date1 = new Date(document.getElementById('checkin').value);
                    let date2 = new Date(document.getElementById('checkout').value);
                  //  let num = document.getElementById('numberSelect').value;

                    // One day in milliseconds
                    let oneDay = 1000 * 60 * 60 * 24;

                    // Calculating the time difference between two dates
                    let diffInTime = date2.getTime() - date1.getTime();

                    // Calculating the no. of days between two dates
                    let diffInDays = Math.round(diffInTime / oneDay);
                    
                    document.getElementById('goeshere').innerHTML = diffInDays;
                    
                    let dayTotal = roomPrice*diffInDays;
                    
                    divobj.value = dayTotal;
                    pnight.value = diffInDays;
 
                return diffInDays;
}

console.log(getNumberOfDays());

//Total Price Show Function

/*  function findTotalcalculateAmount(val) {
    let tot_price = roomPrice * getNumberOfDays();
    divobj.value = tot_price;
    pnight.value =getNumberOfDays(); */
    /* see bellow here the result*/
   // document.getElementById('totalPrice').innerHTML = tot_price;
    //divobj.value = tot_price;
    
/*     return val;
} */



        const d = new Date();
        d.setFullYear(2020, 11, 3);
        </script>

</body>
</html>