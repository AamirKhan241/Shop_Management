<%@page import="items.Item_DAO"%>
<%@page import="items.Item_DAO_Implt"%>
<%@page import="shortage.Shortage_DAO_Implt"%>
<%@page import="shortage.Shortage_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="css/Login.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            body{
                font-family: Arial, Helvetica, sans-serif;
            }
        </style>
    </head>
    <%
        try {
                if(request.getSession().getAttribute("status").toString().equalsIgnoreCase("active")){}
            } catch (Exception e) {
                request.getSession().setAttribute("status", "inactive");
                request.getSession().setAttribute("role", "-");
            }
    %>
    <body style="background-color: #ccccff" id="myPage">
    <!-- Navbar -->
    <div class="w3-top" >
        <div class="w3-bar w3-theme-d2 w3-left-align">
            <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-hover-white w3-theme-d2" href="javascript:void(0);" onclick="openNav()"><i class="fa fa-bars"></i></a>
            <a href="index.jsp" class="w3-bar-item w3-button w3-teal"><i class="fa fa-home w3-margin-right">Arvind Paints and Home Solutions</i></a>
            <a href="ViewBrands.jsp" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Brands</a>
            <a href="ViewItem.jsp" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Items</a>
            <div class="w3-dropdown-hover w3-hide-small">
                <button class="w3-button" title="Notifications">Sell<i class="fa fa-caret-down"></i></button>     
                <div class="w3-dropdown-content w3-card-4 w3-bar-block">
                    <a href="Sale.jsp?GST=NO" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Sell Without Tax</a>
                    <a href="Sale.jsp?GST=YES" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Sell With Tax</a>
                </div>
            </div>
            <a href="ViewCreditHistory.jsp?name=" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Credit History</a>
            <a href="ViewShortage.jsp" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Shortage</a>
            <a href="ViewUsers.jsp" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Users</a>
            <%
                if(request.getSession().getAttribute("role").equals("Admin"))
                    out.print("<a href='ViewShortage.jsp' class='w3-bar-item w3-button w3-hide-small w3-hover-white'>Shortage</a>");
                if(!request.getSession().getAttribute("status").toString().equalsIgnoreCase("active")){
            %>
            <a onclick="document.getElementById('LogIn').style.display='block'" class="w3-right  w3-bar-item w3-button w3-hide-small w3-hover-white" title="Login">LogIn  <span class="glyphicon glyphicon-log-in" ></span> </a>
            <%
                }else{
            %>
            <a href="Log" class="w3-right w3-bar-item w3-button w3-hide-small w3-hover-silver"><span class="glyphicon glyphicon-log-out"></span> LogOut</a>
            <%
                }
            %>
        </div>
    </div>
        
    <!-- LogIn Modal -->
    <div id="LogIn" class="modalL">

        <form class="modal-contentL animateL" action="Log" method="post" style="width:60%; padding: auto; margin: auto;">
            <div class="imgcontainer">
                <span onclick="document.getElementById('LogIn').style.display='none'" class="closeL" title="Close Modal">&times;</span>
                <img src="Images//login.jpg" alt="Avatar" class="avatar">
            </div>

            <div class="containerL">
                <label for="uName"><b>Username</b></label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input class=" form-control" type="text" placeholder="Enter Username" name="uName" required>
                </div>

                <label for="uPass"><b>Password</b></label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input class="form-control" type="password" placeholder="Enter Password" name="uPass" required>
                </div>
                <br>
                <center><input class="btn btn-success" type="submit" style="width:20%" value="LogIn"></center>
                <br>
            </div>
        </form>
    </div>
    <br>
    <br>
    <br>
    <div id="shortage">
        <center><b><i style="font-size: 100px; font-family: fantasy">Shortage</i></b></center>
        <br>
        <table class="table table-striped" border=5>
            <thead>
                <th>Serial No.</th>
                <th>Item Id.</th>
                <th>Item Name</th>
                <th>Item Left</th>
            </thead>
            <%
                Shortage_DAO shdao=new Shortage_DAO_Implt();
                request.setAttribute("shdao", shdao);
                
                Item_DAO idao=new Item_DAO_Implt();
                request.setAttribute("idao", idao);
            %>
            <tbody>
                <c:forEach items="${shdao.getShortage()}" var="s">
                    <tr>
                        <td>${s.getSerial_No()}</td>
                        <td>${s.getItem_Id()}</td>
                        <td>${s.getItem_Name()}</td>
                        <td>${idao.getItem(s.getItem_Id()).getStock()}</td>
                    </tr>
                </c:forEach>
                    <tr>
                        <td colspan="4" style="text-align: center">No More Shortage Found</td>
                    </tr>
            </tbody>
        </table>
    </div>
        
    <br>
        <!-- Contact Container -->
        <div class="w3-container w3-padding-64 w3-theme-l5" id="contact">
            <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
            <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
            <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

            <style>
                .jumbotron {
                    background: #358CCE;
                    color: #FFF;
                    border-radius: 0px;
                }
                .jumbotron-sm { padding-top: 24px;
                    padding-bottom: 24px; }
                    .jumbotron small {
                    color: #FFF;
                }
                .h1 small {
                    font-size: 24px;
                }
            </style>
            <!------ Include the above in your HEAD tag ---------->
            
            <div class="jumbotron jumbotron-sm">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12 col-lg-12">
                            <h1 class="h1">
                                Contact Us <small>Feel free to contact us</small></h1>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container" style="width: 100%">
                <div class="row">
<!--                    <div class="col-md-8">
                        <div class="well well-sm">
                            <form action="UserQuery" method="post">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">
                                            Name</label>
                                        <input type="text" class="form-control" id="name" name="Qname" placeholder="Enter name" required="required" />
                                    </div>
                                    <div class="form-group">
                                        <label for="email">
                                            Email Address</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span>
                                            </span>
                                            <input type="email" class="form-control" id="email" name="Qemail" placeholder="Enter email" required="required" /></div>
                                    </div>
                                    <div class="form-group">
                                        <label for="subject">
                                            Subject</label>
                                        <input type="text" id="subject" name="Qsubject" class="form-control" placeholder="Subject"required="required">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">
                                            Message</label>
                                        <textarea name="Qmessage" id="message" class="form-control" rows="9" cols="25" maxlength="500" required="required"
                                            placeholder="Message"></textarea>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <button type="submit" class="btn btn-primary pull-right" id="btnContactUs">
                                        Send Message</button>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>-->
                    <div style="text-align: center">    
                        <legend><span class="glyphicon glyphicon-globe"></span> Our office</legend>
                        <address>
                            <strong>Arvind Paint and Home Solutions</strong><br>
                                L- I, House No. 22/A, Ground Floor, Bandh Road, Sangam Vihar<br>
                            Delhi, India<br>
                            Pincode: 110080<br>
                        </address>
                    </div>
            <hr style="border: 2px solid black;"/>

            <div class="w3-row" style="text-align: center"><br>
                <h2>Contact Person</h2>
                <div class="col-md-8" >
                    <h3>Arvind Kumar</h3>
                    <p>Contact No. (+91) 9811148445 <span class="glyphicon glyphicon-phone" style="font: 20px"></span></p>
                    <a>arvind886037@gmail.com</a>                            
                </div>
                <div class="col-md-2">
                    <h3>Govind Kumar</h3>
                    <p>Contact No. (+91) 9990330223 <span class="glyphicon glyphicon-phone" style="font: 20px"></span></p>
                    <a>gk271861@gmail.com</a>                            
                </div>
            </div>
                </div>
            </div>
        </div>
        <%--<c:import url="Footer.jsp"></c:import>--%>    
        ${message}
        ${error}
    </body>    
</html>
