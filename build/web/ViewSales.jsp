<%@page import="saleReport.Sale_Report_DAO_Implt"%>
<%@page import="saleReport.Sale_Report_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales</title>
    </head>
    <%
        Sale_Report_DAO sd=new Sale_Report_DAO_Implt();
        request.setAttribute("sd", sd);
    %>
    <body>
        <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Sales</i></b></center>
        <br>
        <table class="table table-striped" style="width: 100%">
            <thead>
                <tr>
                    <th>Invoice Number</th>
                    <th>Customer Details</th>
                    <th>Sale Date</th>
                    <th>Payment Mode</th>
                    <th>Transportation</th>
                    <th>Amount</th>
                    <th>Invoice</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${sd.getSale_Report()}" var="s">
                    <tr style="text-align: center">
                        <td>${s.getInvoice_Number()}</td>
                        <td title="Full Address: ${s.getBuyer_Address()} ${s.getBuyer_State()} ${s.getBuyer_PinCode()}">
                            ${s.getBuyer_Name()} (${s.getBuyer_Contact()}) of ${s.getBuyer_State()} 
                        </td>
                        <td>${s.getSale_Date()}</td>
                        <td>${s.getPayment_Mode()}</td>
                        <td>${s.getTransportation()}</td>
                        <td>${s.getAmount()}</td>
                        <td><a href="GetInvoice.jsp?GST=${s.getGST()}&invoice=${s.getInvoice_Number()}" class="btn btn-default">Get Invoice</a></td>
                        <td><a href="DeleteSale?invoice=${s.getInvoice_Number()}" class="btn btn-danger">Delete</a></td>
                    </tr>
                </c:forEach>            
            </tbody>
        </table>
        ${message}
    </body>
</html>
