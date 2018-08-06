
<%@page import="brands.Brand"%>
<%@page import="java.util.List"%>
<%@page import="brands.Brand_DAO_Implt"%>
<%@page import="brands.Brand_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Brands</title>
        <style>
            tr th, tr td{
                text-align: center;
            }
        </style>
    </head>
    <%
        Brand_DAO bd=new Brand_DAO_Implt();
        List<Brand> list=bd.getBrand();
        
        request.setAttribute("brand", list);
    %>
    <body>
        <c:import url="Header.jsp"></c:import>
        <br>
        <br>
        <h2 style="text-align: center; margin: auto;font-size: 50px">BRANDS</h2>
        <table class="table table-striped" >
            <thead>
                <tr>
                    <th>Serial No.</th>
                    <th>Brand Name</th>
                    <th>Supplier Name</th>
                    <th>Contact Number</th>
                    <th>Description</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody id="profiles">
                <c:forEach items="${brand}" var="b">
                    <tr style="text-align: center">
                        <td>${b.getSerial_No()}</td>
                        <td>${b.getBrand_Name()}</td>
                        <td>${b.getSupplier_Name()}</td>
                        <td>${b.getContact_Number()}</td>
                        <td>${b.getDescription()}</td>
                        <td><a href="UpdateBrand.jsp?serial=${b.getSerial_No()}" class="btn btn-success">Update</a></td>
                        <td><a href="DeleteBrand?serial=${b.getSerial_No()}" class="btn btn-danger">Delete</a></td>
                    </tr>
                </c:forEach>            
                    <tr>
                        <td colspan="7"> 
                            <a href="AddBrand.jsp" class="btn btn-default" style="width: 40%; height:90px;" >
                                <span class="glyphicon glyphicon-plus" style="font-size: 300%"></span><br>
                                <b><i>Add Brand</i></b>
                            </a>
                        </td>                        
                    </tr>
            </tbody>
        </table>
        ${message}
    </body>
</html>
