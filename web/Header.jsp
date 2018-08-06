<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
    <%
        try {
                if(request.getSession().getAttribute("status").toString().equalsIgnoreCase("active")){}              
            } catch (Exception e) {
                request.getSession().setAttribute("status", "inactive");
            }
    %>
<!-- Navbar -->
<div class="w3-top">
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
            <%
                if(request.getSession().getAttribute("role").equals("Admin"))
                    out.print("<a href='ViewShortage.jsp' class='w3-bar-item w3-button w3-hide-small w3-hover-white'>Shortage</a>");
            %>
            <%
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
<!--LogIn Modal--> 
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