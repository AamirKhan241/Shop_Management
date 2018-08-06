<%@page import="sale.Sale_DAO"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="saleReport.Sale_Report"%>
<%@page import="orders.Order"%>
<%@page import="orders.Order_DAO_Implt"%>
<%@page import="orders.Order_DAO"%>
<%@page import="items.Item"%>
<%@page import="java.util.List"%>
<%@page import="items.Item_DAO_Implt"%>
<%@page import="items.Item_DAO"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="sun.util.calendar.BaseCalendar"%>
<%@page import="sun.util.calendar.BaseCalendar.Date"%>
<%@page import="saleReport.Sale_Report_DAO_Implt"%>
<%@page import="saleReport.Sale_Report_DAO"%>
<%@page import="sale.Sale_DAO_Impltt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice</title>
        <style>
            .sale input{
                width: 100%;
            }
            td{
                text-align: left;
            }
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <%
        Item_DAO id=new Item_DAO_Implt();
        Sale_Report_DAO srd=new Sale_Report_DAO_Implt();
        Sale_Report sr=srd.getSale_Report(request.getParameter("invoice"));
        request.setAttribute("sr", sr);
        request.setAttribute("id", id);
    %>
    <body style="background-color: antiquewhite">
        <center><b><i style="font-size: 5vh; color: silver; font-family: fantasy">ARVIND PAINTS AND HOME SOLUTIONS</i></b></center>

        <table class="table table-striped" style="width: 99%; padding: auto; margin: auto ">
            <caption><b style="font-size: 3vh;">Invoice No. : <%=sr.getInvoice_Number()%> </b>
                <i style="padding-left: 90vh"><input type="button" onclick="window.print();" value="Print" class="btn btn-success" style="width: 10vh"></i>
        </caption>
            <tr>
                <td style="width: 60%">
                    <table style=" width: 100%; border:2px solid black">
                        <tr>
                            <th style="text-align: left"> Name: </th>
                            <td colspan="3" style="text-align: left;"> <input type="text" name="buyerName"  class="form form-control" value="<%=sr.getBuyer_Name()%>"></td>
                        </tr>
                        <tr>
                            <th style="text-align: left"> Contact Number:  </th>
                            <td colspan="3" style="text-align: left;"> <input type="text" name="buyerContact" class="form form-control" value="<%=sr.getBuyer_Contact()%>"></td>
                        </tr>
                        <tr>
                            <th style="text-align: left"> Address: </th>
                            <td colspan="3" style="text-align: left;"> <input type="text" name="buyerAddress" value="<%=sr.getBuyer_Address()%>" class="form form-control"></td>
                        </tr>
                        <tr>
                            <th style="text-align: left"> State: </th>
                            <td style="text-align: left;"> <input type="text" name="buyerState" value="<%=sr.getBuyer_State()%>" class="form form-control"></td>
                            <th style="text-align: left"> Pincode: </th>
                            <td style="text-align: left;"> <input type="number" name="buyerPincode" value="<%=sr.getBuyer_PinCode()%>" class="form form-control"></td>
                        </tr>
                        <tr>
                            <th style="text-align: left"> GSTIN (if any): </th>
                            <td colspan="3" style="text-align: left;"> <input type="text" name="buyerGSTIN" value="<%=sr.getBuyer_GSTIN()%> " class="form form-control"></td>
                        </tr>
                    </table>
                </td>
                <td style="width: 40%">
                    <table style=" width: 100%; border:2px solid black">
                        <tr>
                            <td colspan="2" style="text-align: center;" > <b><i>Transportation Details</i></b></td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: center; font-size: 3vh;" ><%=sr.getTransportation()%></td>
                        </tr>
                        <tr>
                            <th> Date of Supply: </th>
                            <td style="text-align: left;"> <input type="text" value="<%=sr.getSale_Date()%>" name="sDate" class="form form-control"></td>
                        </tr>
                        <tr>
                            <th> Place of Supply: </th>
                            <td style="text-align: left;"> <input type="text" name="sPlace" value="NEW DELHI" class="form form-control"></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <hr>

        <table class="table table-striped" style="width: 99%; padding: auto; margin: auto " border=5 id="current">
            <caption><b style="font-size: 5vh;"><center>Order Description</center></b></caption>
            <thead>
                <tr style="text-align: center">
                    <th>Serial No.</th>
                    <th style="width: 40%">Item Desription</th>
                    <th>Unit Price</th>
                    <th>Quantity</th>
                    <th>CGST Rate</th>
                    <th>CGST Amount</th>
                    <th>SGST Rate</th>
                    <th>SGST Amount</th>
                    <th>AMOUNT</th>
                </tr>
            </thead>
            <tbody class="sale" >
                <%  
                    Order_DAO od=new Order_DAO_Implt();
                    Sale_DAO sd=new Sale_DAO_Impltt();
                    request.setAttribute("sd", sd);
                    int i=1;
                %>
                <c:forEach var="i" items="${sd.getSales(sr.getInvoice_Number())}">
                    <tr style="text-align: center">
                        <td><%=i++%></td>
                        <td title="Location: ${id.getItem(i.getItem_Id()).getShelf_Location()}">
                            ${id.getItem(i.getItem_Id()).getItem_Name()} - ${id.getItem(i.getItem_Id()).getBrand()} -  
                            ${id.getItem(i.getItem_Id()).getQuality()} - ${id.getItem(i.getItem_Id()).getWeight()}</td>
                        <td>${id.getItem(i.getItem_Id()).getSale_Price()}</td>
                        <td>${i.getQuantity()}</td>
                        <%
                            if(request.getParameter("GST").equalsIgnoreCase("yes")){
                        %>
                        <td>${id.getItem(i.getItem_Id()).getTax()/2}</td>
                        <td>${(id.getItem(i.getItem_Id()).getTax()/2) * id.getItem(i.getItem_Id()).getSale_Price() * i.getQuantity()/100}</td>
                        <td>${id.getItem(i.getItem_Id()).getTax()/2}</td>
                        <td>${(id.getItem(i.getItem_Id()).getTax()/2) * id.getItem(i.getItem_Id()).getSale_Price() * i.getQuantity()/100}</td>
                        <td>
                            ${
                                (
                                    (
                                        Integer.valueOf(id.getItem(i.getItem_Id()).getSale_Price()) * Integer.valueOf(id.getItem(i.getItem_Id()).getTax())/100
                                    ) + Integer.valueOf(id.getItem(i.getItem_Id()).getSale_Price())
                                ) * i.getQuantity()
                            }
                        </td><%
                            }else if(request.getParameter("GST").equalsIgnoreCase("NO")){
                        %>
                        <td>0</td>
                        <td>0</td>
                        <td>0</td>
                        <td>0</td>
                        <td>${Integer.valueOf(id.getItem(i.getItem_Id()).getSale_Price()) * i.getQuantity()}</td>
                        <%}%>
                    </tr>
                </c:forEach>
                    <%
                        while (i<=5) {
                    %>
                    <tr>
                        <td><%=i++%>.</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%}%>

                    <tr>
                        <td><%=i%></td>
                        <td><b>CARTAGE</b></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><%=sr.getCartage()%></td>
                    </tr>
            </tbody>
            <tr>
                <th colspan="4" style="text-align: right">Grand Total</th>
                <th></th>
                <%
                    if(request.getParameter("GST").equalsIgnoreCase("yes")){
                %>
                <th><%=sd.getTax(sr.getInvoice_Number())%></th>
                <th></th>
                <th><%=sd.getTax(sr.getInvoice_Number())%></th>
                <%
                    }else if(request.getParameter("GST").equalsIgnoreCase("NO")){
                %>
                <th>0</th>
                <th></th>
                <th>0</th>
                <%}%>
                <th style="font-size: 5vh"><%=sr.getAmount()%></th>
 
            </tr>
        </table>
        <table class="table table-striped" style="width: 99%; padding: auto; margin: auto ">
            <caption></caption>
            <tr>
                <td>
                    <table style=" width: 100%; border:2px solid black; height:26vh">
                        <tr>
                            <td colspan="2" style="text-align: center; font-size: 50px"> <b><i>Transaction Mode</i></b></td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: center;" >
                                <h3><b><i><%=sr.getPayment_Mode()%></i></b></h3>
                            </th>
                        </tr>
                    </table>
                </td>
                <td style="width: 50%">
                    <table class="table table-striped"style=" width: 100%; border:2px solid black; height:26vh">
                        <tr>
                            <td colspan="3" style="text-align: center; font-size: 30px;height: 55px;" > <b><i>Total Payable Amount</i></b></td>
                        </tr>
                        <tr>
                            <th style="text-align: left"> Taxable Amount </th>
                            <th style="text-align: left"> Amount excluding tax </th>
                            <th style="text-align: left"> Amount including tax </th>
                        </tr>
                        <tr>
                            <%
                                if(request.getParameter("GST").equalsIgnoreCase("yes")){
                            %>
                            <td style="text-align: left; width: 30%"><%=Double.valueOf(sd.getTax(sr.getInvoice_Number()))*2%> </td>
                            <td style="text-align: left; width: 30%"><%=Double.valueOf(sr.getAmount())-Double.valueOf(sd.getTax(sr.getInvoice_Number()))*2%> </td>
                            <td style="text-align: left; width: auto"> <%=sr.getAmount()%></td>
                            <%
                                } else if(request.getParameter("GST").equalsIgnoreCase("no")){
                            %>

                            <td style="text-align: left;"> 0</td>
                            <td style="text-align: left;"> <%=sr.getAmount()%></td>
                            <td style="text-align: left;"> <%=sr.getAmount()%></td>
                            <%}%>
                        </tr>
                        <tr>
                            <th style="width: 15%"><i>Total Amount <br>(in Words)</i></th>
                            <th style="font-family: inherit" colspan="2"><i>&#8377; 
                                <% int amt=0;
                                try{

                                    amt=Integer.valueOf(sr.getAmount());
                                    }catch(Exception e){
                                        amt=Integer.valueOf(sr.getAmount());
                                    }
                                out.println(database.NumberConvert.getInstance().Convert(amt));%></i>
                            </th>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <hr>
        <table style="border: 2px solid black; width: 99%; padding: auto; margin: auto">
            <tr>
                <td colspan="3"><b>Terms & Conditions: </b></td>
                <td style="text-align: right">For <b>ARVIND PAINTS AND HARDWARE SOLUTIONS</b> </td>
            </tr>
            <tr>
                <td colspan="4">E.&O.E.</td>
            </tr>
            <tr>
                <td>1.</td>
                <td colspan="3">Goods once sold will not be taken back</td>
            </tr>
            <tr>
                <td>2.</td>
                <td>Interest @18% p.a. will be charged if the payment is </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td>not made with in the stipulated time.</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>3.</td>
                <td colspan="3"> Subject to "Delhi" Jurisdiction only.</td>
            </tr>
            <tr>
                <td colspan="3" style="text-align: right;">Reciever's Signature</td>
                <td style="text-align: right; width: 30%">Authorized Signature</td>
            </tr>
            <tr>
                <td colspan="4"><b>Note: </b>Cartage Charges extra.</td>
            </tr>
        </table>                                
    </body>
</html>
