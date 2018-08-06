<%@page import="items.Item_DAO_Implt"%>
<%@page import="items.Item_DAO"%>
<%@page import="shortage.Shortage_DAO_Implt"%>
<%@page import="shortage.Shortage_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shortage</title>
    </head>
    <body>
        <c:import url="Header.jsp"></c:import>
        <br>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Shortage</i></b></center>
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
    </body>
</html>
