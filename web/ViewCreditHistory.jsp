<%@page import="sale.Sale"%>
<%@page import="sale.Sale_DAO_Impltt"%>
<%@page import="sale.Sale_DAO"%>
<%@page import="credit.Credit"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="credit.Credit_DAO_Implt"%>
<%@page import="credit.Credit_DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Credit History</title>
    </head>
    <%
        Credit_DAO cdao=new Credit_DAO_Implt();
        request.setAttribute("cd", cdao);
        request.setAttribute("name", request.getParameter("name"));  
    %>
    <body>
        <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Credit History</i></b></center>
        <br>
        <form action="ViewCreditHistory.jsp?name=" method="post" id="sel" style="width: 50%; margin:auto; padding:auto">
            <div class="col-sm-10">
                <select class="form form-control" onchange="document.getElementById('sel').action='ViewCreditHistory.jsp?name='+this.value">
                    <option value="" selected>Select Creditor's Name...</option>
                    <option value="all">All</option>
                    <c:forEach items="${cd.getCreditor()}" var="c">
                        <option>${c}</option>
                    </c:forEach>
                        <option>Last Transaction of all</option>
                </select>
            </div>
                <input type="submit" value="Find" class="btn btn-success">
        </form>
        <hr>
        <table class="table table-striped" style="width: 100%">
            <thead>
                <tr>
                    <th>Serial No.</th>
                    <th>Creditor Name</th>
                    <th>Creditor Contact</th>
                    <th>Date</th>
                    <th>Amount Credited</th>
                    <th>Amount Payed</th>
                    <th>Amount Balance</th>
                </tr>
            </thead>
            <tbody>         
                <c:forEach items='${cd.getCreditHistory(name)}' var="c">
                    <tr style="text-align: center">
                        <td>${c.getSerial_No()}</td>
                        <td>${c.getCredittor_Name()}</td>
                        <td>${c.getCredittor_Contact()}</td>
                        <td>${c.getDate()}</td>
                        <td>${c.getAmount_Creditted()}</td>
                        <td>${c.getAmount_Payed()}</td>
                        <td>${c.getAmount_Balance()}</td>
                    </tr>
                </c:forEach>
                    <%
                        if(request.getParameter("name").equalsIgnoreCase("all") || request.getParameter("name").equalsIgnoreCase("")){
                    %>
                    <tr>
                        <th colspan="6" style="text-align: right">Total Amount to collect</th>
                        <th  style="text-align: center"><%=cdao.getTotalAmount(request.getParameter("name"))%></th>
                    </tr>
                    <%}%>
                <form id="pay" method="post">
                    <tr>
                        <td colspan="4">
                            <%System.out.println(request.getParameter("name"));%>
                            <select name="namePay" class="form form-control" onchange="document.getElementById('pay').action='Pay?name=<%=request.getParameter("name")%>'">
                                <option value="" selected>Select Creditor's Name...</option>
                                <c:forEach items="${cd.getCreditor()}" var="c">
                                    <option>${c}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td colspan="2"><input type="number" class="form form-control" placeholder="Amount to pay..." name="amountToPay"></td>
                        <td><input type="submit" class="btn btn-success" style="width: 100%" value="Pay"></td>
                    </tr>
                </form>
            </tbody>
        </table>
        ${message}
    </body>
</html>
