<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Romanesco&display=swap"> 
    <link rel="stylesheet" href="/hotelLUNA/toreachresourcefolder/CSS/index.css">
    <script src="https://kit.fontawesome.com/e09933254b.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
        <nav class="navbar">
            <div class="max-width">
                <div class="logo"><a href="#">LUNA</a></div>
                <ul class="menu">
                    <li><a href="/hotelLUNA/">Home</a></li>
                    <li><a href="#ab">About</a></li>
                    <li><a href="/hotelLUNA/roomPage">Room</a></li>
                    <li><a href="#db">Dining & Bar</a></li>
                    <li><a href="#amt">Amenities</a></li>
                    <li><a href="#contact">Contact</a></li>
                </ul>
                <div class="menu-btn">
                    <i class="fas fa-bars"></i>
                </div>
            </div>
        </nav>

        <!-- Home section start -->
        <section class="home" id="home">
            <div class="banner">
                <h1>Welcome</h1>
                <p>to</p>
               <h2>HOTEL <span> Luna</span></h2>
            </div>
			<form:form action="/hotelLUNA/homeSearchRoom" method="POST" modelAttribute="sroom" >
            <section class="book">
                <div class="max-width flex">
                    <div class="input grid">
                        <div class="box">
                             <label for="">Check-in:</label><br>
                            <form:input type="date" path="checkIn" placeholder="Check-in-Date"  value="${sessionScope.checkIn}" required="required" />
                        	<form:errors path="checkIn" style="color:red;"></form:errors>
                         </div>
                         <div class="box">
                            <label for="">Check-out:</label><br>
                           <form:input type="date" path="checkOut" placeholder="Check-out-Date" value="${sessionScope.checkOut}" required="required" />
                        	<form:errors path="checkOut" style="color:red;"></form:errors>
                        </div>
                         <div class="box">
                            <label for="">Adults</label><br>
                             <form:input type="number" path="adultNumber" min="0" max="5" value="0" required="required" />
                        </div>
                        <div class="box">
                            <label for="">Children</label><br>
                             <form:input type="number" path="childrenNumber" min="0" max="5" value="0" required="required"/>
                        </div>
                    </div>
                    <div class="search">
                        <input type="submit" value="Search Room">
                    </div>
                 </div>
            </section>
              </form:form>
            <div style="color:red;">${error}</div>
         <%--     <section class="book">
                <div class="max-width flex">
                    <div class="input grid">
                     <div class="box">
                        <div style="color:red;">${error}</div>
                    </div>
                 </div>
                 </div>
            </section>
        </section> --%>
        <!-- about section start -->
        <section class="about top" id="about">
            <div class="max-width flex">
                <div class="left">
                    <div class="img">
                        <img src="/hotelLUNA/toreachresourcefolder/Image/lino-ogenio-ulGg7CN8qfk-unsplash.jpg" alt="" class="image1">
                        <img src="/hotelLUNA/toreachresourcefolder/Image/mike-swigunski-7CRkneDWu9s-unsplash.jpg" alt="" class="image2">
                    </div>
                </div>
                
                <div class="right">
                    <div class="heading">
                        <h5 id="ab">About</h5>
                        <h2 style="font-weight: lighter;">HOTEL LUNA</h2>
                        <p>Built to represent the sail of an Arabian dhow, the Burj Al Arab is the seventh tallest hotel in the world. Easily one of the most outrageously extravagant hotels in the world, it features a pillow menu with nine types of pillows for the guest to choose from, 24-carat gold leaf walls and full-sized Hermes amenities in every suite.</p>
                        <p>Whilst the official rating of the hotel is 5-star (the highest hotel rating there is), it is widely referred to as a seven-star hotel. This magnificent destination offers world class service-right down to the chauffeur driven Rolls Royce service.</p>
                        <button>Read More</button>
                    </div>
                </div>
            </div>
        </section>
        <!-- room section start -->
        <section class="room top" id="room">
            <div class="max-width">
                <div class="heading_top flex1">
                    <div class="heading">
                        <h5>Raising comfort to the highest level</h5>
                        <h2>Room and Suites</h2>
                    </div>
                    <div class="button">
                        <button>View All</button>
                    </div>
                </div>
                <div class="content grid">
                    <div class="box">
                        <div class="img">
                            <img src="/hotelLUNA/toreachresourcefolder/Image/alexander-kaunas-67-sOi7mVIk-unsplash.jpg" alt="">
                        </div>
                        <div class="text">
                            <h3>Normal Room</h3>
                            <p>
                                150000
                                <span class="ks">Ks</span>
                                <span class="pn">/per night</span>
                            </p>
                        </div>
                    </div>
                    <div class="box">
                        <div class="img">
                            <img src="/hotelLUNA/toreachresourcefolder/Image/visualsofdana-T5pL6ciEn-I-unsplash.jpg" alt="">
                        </div>
                        <div class="text">
                            <h3>Standard Room</h3>
                            <p>
                                200000
                                <span class="ks">Ks</span>
                                <span class="pn">/per night</span>
                            </p>
                        </div>
                    </div>
                    <div class="box">
                        <div class="img">
                            <img src="/hotelLUNA/toreachresourcefolder/Image/isaac-quesada-xc4oxgAbDmw-unsplash.jpg" alt="">
                        </div>
                        <div class="text">
                            <h3>Luxury Room</h3>
                            <p>
                                250000
                                <span class="ks">Ks</span>
                                <span class="pn">/per night</span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="top">   
            <div class="dblf max-width">
                <div class="left">
                    <div class="dbcontent">
                        <h1 id="db">Dining and Bar</h1>
                        <p style="font-size: 18px;">Taste our trademark cocktails & enjoy a truly unique culinary experience... As the sun sets and the sky turns purple, we celebrate the violet hour and the end of the day before welcoming the start of the night.</p>
                        <div class="foodmenu">
                            <h2>Chicken Parmagiana</h2>
                            <h2 class="foodprice">25000KS</h2>
                            <p style="margin-top: 15px;">
                                Chicken Surf & Turf Char grilled garlic chicken breast fillet topped with a garlic seafood sauce & served with garden salad & steak fries or with steamed vegetables and potato.
                            </p>
                            <div class="line"></div>
                        </div>
                        <div class="foodmenu">
                            <h2>Asian noodle salad</h2>
                            <h2 class="foodprice">20000KS</h2>
                            <p style="margin-top: 15px;">
                                Julliene veggies & rice noodles with mint coriander & cashew nuts nestled in mixed leaves and finished with sesame oil and a lime dressing.
                            </p>
                            <div class="line"></div>
                        </div>
                        <button class="foodbtn">View All Menu</button>   
                    </div>
                </div>
                <div class="right">
                    <div class="dbimg">
                        <img src="/hotelLUNA/toreachresourcefolder/Image/d_b.png" alt="">
                    </div>
                </div>
            </div>
        </section>

        <section class="top">
            <div class="amenties max-width">
                <h1 id="amt">Amenities</h1>
                <div><img src="/hotelLUNA/toreachresourcefolder/Image/amen.png" alt=""></div>
                <p>Besides our main services, we always have a lot of extra amenities to offer. Starting with the free parking and WiFi to a SPA center and a Conference hall, we can make all of your wishes come true!</p>
                <button style="margin-top: 30px;">View All</button>
            </div>
        </section>

        <section class="map top">
            <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d30541.51345656782!2d96.203247!3d16.891263249999998!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2smm!4v1652062167869!5m2!1sen!2smm" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </section>
        
        <footer>
            <div class="boxes max-width flex">
                <div class="box">
                    <img src="" alt="">
                    <h1>LUNA HOTEL</h1>
                    <p>Accept payment methods</p>
                    <div class="payment grid">
                        <div><img src="/hotelLUNA/toreachresourcefolder/Image/Payment/pngegg(11).png" alt=""></div>
                        <div><img src="/hotelLUNA/toreachresourcefolder/Image/Payment/pngegg(7).png" alt=""></div>
                        <div><img src="/hotelLUNA/toreachresourcefolder/Image/Payment/pngegg(8).png" alt=""></div>
                        <div><img src="/hotelLUNA/toreachresourcefolder/Image/Payment/pngegg(9).png" alt=""></div>
                    </div>
                </div>
                <div class="box">
                    <h3>Terms and conditions</h3>
                    <ul>
                        <li>Site Map</li>
                        <li>Hotel terms and conditions</li>
                        <li>Privacy and policy</li>
                    </ul>
                </div>
                <div class="box">
                    <h3>For Customers</h3>
                    <ul>
                        <li>Customer Care/Help</li>
                        <li>Corporate Accounts</li>
                        <li>Finicial Information</li>
                        <li>Terms & Condition</li>
                    </ul>
                </div>
                <div class="box">
                    <h3 id="contact">Contact Us</h3>
                    <li><i class="fa-solid fa-location-dot"></i>Location</li>
                    <li><i class="fa-solid fa-envelope"></i> g mail</li>
                    <li><i class="fa-solid fa-phone"></i> phone no</li>
                    <li><i class="fa-solid fa-phone"></i> phone no</li>
                    <li><i class="fa-solid fa-briefcase"></i> 24/7 Customer Service</li>
                </div>
            </div>
        </footer>
        
        <script type="text/javascript" src="/Java Script/script.js"></script>
</body>
</html>