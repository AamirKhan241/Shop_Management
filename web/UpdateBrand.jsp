<%@page import="brands.Brand"%>
<%@page import="brands.Brand_DAO_Implt"%>
<%@page import="brands.Brand_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Brand</title>
    </head>
    <%
        Brand_DAO bd=new Brand_DAO_Implt();
        Brand b=bd.getBrand(Integer.valueOf(request.getParameter("serial")));
    %>
    <body>
        <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Updating Profile</i></b></center>
        <form action="UpdateBrand?serial=<%=b.getSerial_No()%>" method="post" style="text-align: center; width: 80%; margin: auto">
            <label style="float: left">Brand Name:</label>
            <input type="text" value="<%=b.getBrand_Name()%>" name="brandName" class="form form-control" required >
            <br>
            <label style="float: left">Supplier Name:</label>
            <input type="text" value="<%=b.getSupplier_Name()%>" name="supplierName" class="form form-control" >
            <br>
            <label style="float: left">Contact Number:</label>
            <input type="number" value="<%=b.getContact_Number()%>" name="contactNumber" class="form form-control" >
            <br>
            <label style="float: left">Description:</label>
            <textarea name="description" class="form form-control" required><%=b.getDescription()%></textarea>
            <br>
            <input type="submit" value="Update" class="btn btn-success" style="width: 30%">
        </form>
        ${error}
    </body>
</html>
