<%-- 
    Document   : ViewItem
    Created on : Jul 3, 2018, 4:49:44 PM
    Author     : aamir
--%>

<%@page import="brands.Brand"%>
<%@page import="brands.Brand_DAO_Implt"%>
<%@page import="brands.Brand_DAO"%>
<%@page import="items.Item"%>
<%@page import="java.util.List"%>
<%@page import="items.Item_DAO_Implt"%>
<%@page import="items.Item_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Items</title>
    </head>
    <%
        Item_DAO id=new Item_DAO_Implt();
        List<Item> list=id.getItem();
        request.setAttribute("item", list);
        
        Brand_DAO bd=new Brand_DAO_Implt();
        List<Brand> listB=bd.getBrand();
        request.setAttribute("brand", listB);

    %>
    <body>
        <c:import url="Header.jsp"></c:import>
        <br>
        <br>
        <h2 style="text-align: center; margin: auto;font-size: 50px">ITEMS</h2>
        <input class="form-control" style="float: right; width: 30%" id="filter" type="text" placeholder="Filter Items..">
              
      <script>
            $(document).ready(function(){
              $("#filter").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("#item tr").filter(function() {
                  $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
              });
            });
        </script>
        <table class="table table-striped" style="width: 100%">
            <thead>
                <tr>
                    <th style="width: 7%">Serial No.</th>
                    <th>Item Name</th>
                    <th style="width: 10%">Brand Name</th>
                    <th style="width: 10%">Quality</th>
                    <th>Color</th>
                    <th>Weight</th>
                    <th>Stock</th>
                    <th>Shelf Location</th>
                    <th>Cost Price</th>
                    <th>Sale Price</th>
                    <th>CGST (in %)</th>
                    <th>SGST (in %)</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody id="item" >
                <%int i=1;%>
                <c:forEach items="${item}" var="i">
                    <tr style="text-align: center">
                        <td><%=i++%></td>
                        <td>${i.getItem_Name()}</td>
                        <td>${i.getBrand()}</td>
                        <td>${i.getQuality()}</td>
                        <td>${i.getColor()}</td>
                        <td>${i.getWeight()}</td>
                        <td>${i.getStock()}</td>
                        <td>${i.getShelf_Location()}</td>
                        <td>${i.getCost_Price()}</td>
                        <td>${i.getSale_Price()}</td>
                        <td>${i.getTax()/2}</td>
                        <td>${i.getTax()/2}</td>
                        <td><a href="UpdateItem.jsp?serial=${i.getSerial_No()}" class="btn btn-success">Update</a></td>
                        <td><a href="DeleteItem?serial=${i.getSerial_No()}" class="btn btn-danger">Delete</a></td>
                    </tr>
                </c:forEach>            
            <style>
                input{
                    width:100%;
                    height:4vh;
                }
            </style>
                    <tr style="text-align: center; background-color: #ccccff; border: 1px solid" >
                        <form action="AddItem" method="post">
                            <td>New Item's Details: </td>
                            <td><input type="text" placeholder="Item Name..." required="" name="itemName" class="form form-control"></td>
                            <td>
                                <select name="brand" class="form form-control">
                                    <option selected>Brand...</option>
                                    <c:forEach items="${brand}" var="b">
                                        <option>${b.getBrand_Name()}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <select required="" name="quality" class="form form-control">
                                    <option value="">Quality...</option>
                                    <option>First</option>
                                    <option>Second</option>
                                </select>
                            </td>
                            <td><input type="text" placeholder="Color..." required="" name="color" class="form form-control"></td>
                            <td><input type="text" placeholder="Weight..." required="" name="weight" class="form form-control"></td>
                            <td><input type="number" placeholder="Stock..." required="" name="stock" class="form form-control"></td>
                            <td><input type="text" placeholder="Shelf Location..." required="" name="shelf" class="form form-control"></td>
                            <td><input type="number" placeholder="Cost Price..." required="" name="cPrice" class="form form-control"></td>
                            <td><input type="number" placeholder="Sale Price..." required="" name="sPrice" class="form form-control"></td>
                            <td colspan="2"><input type="number" placeholder="GST (in %)..." required="" name="tax" class="form form-control"></td>
                            <td colspan="2"><input type="submit" class="btn btn-danger" value="Add Item"></td>
                        </form>
                    </tr>                    
            </tbody>
        </table>
        ${message}
    </body>
</html>
