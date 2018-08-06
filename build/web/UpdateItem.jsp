<%--
    Document   : UpdateItem
    Created on : Jul 3, 2018, 6:21:01 PM
    Author     : aamir
--%>

<%@page import="java.util.List"%>
<%@page import="brands.Brand"%>
<%@page import="brands.Brand_DAO_Implt"%>
<%@page import="brands.Brand_DAO"%>
<%@page import="items.Item"%>
<%@page import="items.Item_DAO_Implt"%>
<%@page import="items.Item_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Item</title>
    </head>
    <%
        Item_DAO id=new Item_DAO_Implt();
        Item i=id.getItem(Integer.valueOf(request.getParameter("serial")));

        Brand_DAO bd=new Brand_DAO_Implt();
        List<Brand> list=bd.getBrand();
        request.setAttribute("brand", list);
    %>
    <body>
        <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Updating Item</i></b></center>

        <form action="UpdateItem?serial=<%=i.getSerial_No()%>" method="post" style="width:90%; margin:auto; padding:auto; ">
            <label style="float: left">Item Name</label>
            <input type="text" value="<%=i.getItem_Name()%>" required="" class="form form-control" name="itemName">
            <br>
            <label style="float: left">Brand Name</label>
            <select name="brand" class="form form-control">
                <option selected><%=i.getBrand()%></option>
                <option>Select Brand...</option>
                <c:forEach items="${brand}" var="b">
                    <option>${b.getBrand_Name()}</option>
                </c:forEach>
            </select>
            <br>
            <label style="float: left">Quality</label>
            <input type="text" value="<%=i.getQuality()%>" required="" class="form form-control" name="quality">
            <br>
            <label style="float: left">Color</label>
            <input type="text" value="<%=i.getColor()%>" required="" name="color" class="form form-control">
            <br>
            <label style="float: left">Weight</label>
            <input type="text" value="<%=i.getWeight()%>" required="" name="weight" class="form form-control">
            <br>
            <label style="float: left">Stock</label>
            <input type="number" value="<%=i.getStock()%>" required="" name="stock" class="form form-control">
            <br>
            <label style="float: left">Shelf Location</label>
            <input type="text" value="<%=i.getShelf_Location()%>" required="" name="shelf" class="form form-control">
            <br>
            <label style="float: left">Cost Price</label>
            <input type="number" value="<%=i.getCost_Price()%>" required="" name="cPrice" class="form form-control">
            <br>
            <label style="float: left">Sale Price</label>
            <input type="number" value="<%=i.getSale_Price()%>" required="" name="sPrice" class="form form-control">
            <br>
            <label style="float: left">Tax</label>
            <input type="number" value="<%=i.getTax()%>" required="" name="tax" class="form form-control">
            <br>
            <center><input type="submit" class="btn btn-success" value="Update" style="width:30%; " ></center>
        </form>
    </body>
</html>
