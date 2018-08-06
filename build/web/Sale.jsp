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
        <title>Sale</title>
        <style>
            .sale input{
                width: 100%;
            }
            td{
                text-align: left;
            }
        </style>
    </head>
    <%
        try {
                System.out.println(request.getSession().getAttribute("LogIn"));
            } catch (NullPointerException e) {
                request.setAttribute("error", "<script>alert('You have to login First');</script>");
                RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        
        Item_DAO id=new Item_DAO_Implt();
        request.setAttribute("id", id);
    %>
    <body style="background-color: antiquewhite">
        <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">ARVIND PAINTS AND HOME SOLUTIONS</i></b></center>
        <form id="sale" action="Sale?LogIn=<%=request.getSession().getAttribute("LogIn")%>&tax=<%=request.getParameter("GST")%>" method="post" style="text-align: center; ">
            <table class="table table-striped" style="width: 99%; padding: auto; margin: auto ">
                <tr>
                    <td>
                        <table style=" width: 100%; border:2px solid black">
                            <tr>
                                <td colspan="4" style="text-align: center; font-size: 30px"> <b><i>Customer Details</i></b></td>
                            </tr>
                            <tr>
                                <th style="text-align: left"> Name: </th>
                                <td colspan="3" style="text-align: left;"> <input type="text" name="buyerName"  class="form form-control" value="Customer"></td>
                            </tr>
                            <tr>
                                <th style="text-align: left"> Contact Number:  </th>
                                <td colspan="3" style="text-align: left;"> <input type="text" name="buyerContact" class="form form-control" value="--"></td>
                            </tr>
                            <tr>
                                <th style="text-align: left"> Address: </th>
                                <td colspan="3" style="text-align: left;"> <input type="text" name="buyerAddress" value="Sangam Vihar" class="form form-control"></td>
                            </tr>
                            <tr>
                                <th style="text-align: left"> State: </th>
                                <td style="text-align: left;"> <input type="text" name="buyerState" value="Delhi" class="form form-control"></td>
                                <th style="text-align: left"> Pincode: </th>
                                <td style="text-align: left;"> <input type="number" name="buyerPincode" value="110080" class="form form-control"></td>
                            </tr>
                            <tr>
                                <th style="text-align: left"> GSTIN (if any): </th>
                                <td colspan="3" style="text-align: left;"> <input type="text" name="buyerGSTIN" value="-NO-" class="form form-control"></td>
                            </tr>
                        </table>
                    </td>
                    <td style="width: 40%">
                        <table style=" width: 100%; border:2px solid black">
                            <tr>
                                <td colspan="2" style="text-align: center; font-size: 30px;height: 55px;" > <b><i>Transportation Details</i></b></td>
                            </tr>
                            <tr>
                                <th> Vehicle Number: </th>
                                <td style="text-align: left;"> <input type="text" name="vNumber" value="Self" class="form form-control"></td>
                            </tr>
                            <tr>
                                <th> Contact Number: </th>
                                <td style="text-align: left;"> <input type="text" name="vContactNumber" value="--" class="form form-control"></td>
                            </tr>
                            <%
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
                                LocalDate localDate = LocalDate.now();                                
                            %>
                            <tr>
                                <th> Date of Supply: </th>
                                <td style="text-align: left;"> <input type="text" value="<%=dtf.format(localDate)%>" name="sDate" class="form form-control"></td>
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
            <div class="form-group">
                <select id="itemC" name="itemName" class="form form-control" onchange="setHref()">
                    <option selected value="">Select Item...</option>
                    <c:forEach items="${id.getItem()}"  var="i">
                        <option value="${i.getSerial_No()}" title="Item Name - Brand Name - Quality - Weight">
                            ${i.getItem_Name()} - ${i.getBrand()} -  ${i.getQuality()} - ${i.getWeight()}
                        </option>
                    </c:forEach>
                </select>
                <br>
                <a href="" id="Add" class="btn btn-success">Add</a>
                <script>
                    function setHref(){  
                        document.getElementById("Add").href="AddOrder?serial="+document.getElementById("itemC").value+"&tax=<%=request.getParameter("GST")%>";
                    }
                </script>
            </div>
            
            <table class="table table-striped" style="width: 99%; padding: auto; margin: auto " border=5 id="current">
                <thead>
                    <tr style="text-align: center">
                        <th>Serial No.</th>
                        <th>Remove Item</th>
                        <th style="width: 30%">Item Desription</th>
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
                        List<Order> list=od.getOrders(Integer.valueOf(request.getSession().getAttribute("LogIn").toString()));
                        request.setAttribute("order", list);
                        int i=1;
                    %>
                    <c:forEach items="${order}" var="o">
                        <tr>
                            <td><%=i++%>.</td>
                            <td><a href="DeleteOrder?serial=${o.getSerial_No()}&tax=<%=request.getParameter("GST")%>" class="btn btn-danger">Delete</a></td>
                            <td title="Location: ${id.getItem(o.getItem_Id()).getShelf_Location()}">
                                ${id.getItem(o.getItem_Id()).getItem_Name()} - ${id.getItem(o.getItem_Id()).getBrand()} -  
                                ${id.getItem(o.getItem_Id()).getQuality()} - ${id.getItem(o.getItem_Id()).getWeight()}
                            </td>
                            <td>${id.getItem(o.getItem_Id()).getSale_Price()}</td>
                            <td>
                                <a class="btn btn-default" href="UpdateOrder?id=min,${o.getSerial_No()},${id.getItem(o.getItem_Id()).getSerial_No()}&tax=<%=request.getParameter("GST")%>">
                                    <span style="font-family: cursive; font-size: 20px;">-</span>
                                </a> 
                                ${o.getQuantity()}
                                
                                <a class="btn btn-default" href="UpdateOrder?id=plus,${o.getSerial_No()},${id.getItem(o.getItem_Id()).getSerial_No()}&tax=<%=request.getParameter("GST")%>">
                                <span style="font-family: cursive; font-size: 20px;">+</span>
                                </a>
                            </td>
                            <%
                                if(request.getParameter("GST").equalsIgnoreCase("yes")){
                            %>
                            <td>${id.getItem(o.getItem_Id()).getTax()/2}</td>
                            <td>${(id.getItem(o.getItem_Id()).getTax()/2) * id.getItem(o.getItem_Id()).getSale_Price() * o.getQuantity()/100}</td>
                            <td>${id.getItem(o.getItem_Id()).getTax()/2}</td>
                            <td>${(id.getItem(o.getItem_Id()).getTax()/2) * id.getItem(o.getItem_Id()).getSale_Price() * o.getQuantity()/100}</td>
                            <td>
                                ${
                                    (
                                        (
                                            Integer.valueOf(id.getItem(o.getItem_Id()).getSale_Price()) * Integer.valueOf(id.getItem(o.getItem_Id()).getTax())/100
                                        ) + Integer.valueOf(id.getItem(o.getItem_Id()).getSale_Price())
                                    ) * o.getQuantity()
                                }
                            </td><%
                                }else if(request.getParameter("GST").equalsIgnoreCase("NO")){
                            %>
                            <td>0</td>
                            <td>0</td>
                            <td>0</td>
                            <td>0</td>
                            <td>${Integer.valueOf(id.getItem(o.getItem_Id()).getSale_Price()) * o.getQuantity()}</td>
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
                            <td></td>
                        </tr>
                        <%}%>
                        <tr>
                            <td><%=i%>.</td>
                            <td></td>
                            <td><b>CARTAGE</b></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td style="width: 5%">
                                <input type="number" name="cartage" id="crt" class="form form-control" value="0"
                                       onkeyup="if(this.value !==0)document.getElementById('amt').innerHTML=parseInt(<%=od.getAmount(Integer.valueOf(request.getSession().getAttribute("LogIn").toString()),request.getParameter("GST"))%>) + parseInt(this.value); else document.getElementById('amt').innerHTML='<%=od.getAmount(Integer.valueOf(request.getSession().getAttribute("LogIn").toString()),request.getParameter("GST"))%>';" >
                            </td>
                        </tr>
                </tbody>
                <tr>
                    <th colspan="5" style="text-align: right">Grand Total</th>
                    <th></th>
                    <%
                        if(request.getParameter("GST").equalsIgnoreCase("yes")){
                    %>
                    <th><%=od.getTax(Integer.valueOf(request.getSession().getAttribute("LogIn").toString()))%></th>
                    <th></th>
                    <th><%=od.getTax(Integer.valueOf(request.getSession().getAttribute("LogIn").toString()))%></th>
                    <%
                        }else if(request.getParameter("GST").equalsIgnoreCase("NO")){
                    %>
                    <th>0</th>
                    <th></th>
                    <th>0</th>
                    <%}%>
                    <th id="amt" style="font-size: 5vh"> <%=od.getAmount(Integer.valueOf(request.getSession().getAttribute("LogIn").toString()),request.getParameter("GST"))%></th>
                </tr>
                <tr>
                    <td colspan="9"></td>
                    <td style="text-align: center"><input type="submit" style="width: 100px;" class="btn btn-Success" value="Sell"></td>
                </tr>
            </table>
            <table class="table table-striped" style="width: 99%; padding: auto; margin: auto ">
                <tr>
                    <td>
                        <table style=" width: 100%; border:2px solid black">
                            <tr>
                                <td colspan="2" style="text-align: center; font-size: 30px"> <b><i>Transaction Mode</i></b></td>
                            </tr>
                            <tr>
                                <th style="text-align: left " colspan="2" > 
                                    <select name="transactionMode" class="form form-control" style="width: 50%;margin: auto; padding: auto;" required="">
                                        <option selected >Cash</option>
                                        <option>Cheque</option>
                                        <option>Credit</option>
                                    </select>
                                </th>
                            </tr>
                            <tr>
                                <th>Cheque Number</th>
                                <td><input type="text" name="chqNumber" class="form form-control"></td>
                            </tr>
                            <tr>
                                <th>Bank Name</th>
                                <td><input type="text" name="bName" class="form form-control"></td>
                            </tr>
                        </table>
                    </td>
                    <td style="width: 40%">
                        <table style=" width: 100%; border:2px solid black">
                            <tr>
                                <td colspan="2" style="text-align: center; font-size: 30px;height: 55px;" > <b><i>Total Payable Amount</i></b></td>
                            </tr>
                            <tr>
                                <th style="width: 15%"><i>In Figures</i></th>
                                <th class="form-control"><i><%=od.getAmount(Integer.valueOf(request.getSession().getAttribute("LogIn").toString()),request.getParameter("GST"))%></i></th>
                            </tr>
                            <tr>
                                <th style="width: 15%"><i>In Words</i></th>
                                <th class="form-control"><i>&#8377; 
                                    <% int amt=0;
                                    try{

                                        amt=Integer.valueOf(od.getAmount(Integer.valueOf(request.getSession().getAttribute("LogIn").toString()),request.getParameter("GST")));
                                        }catch(Exception e){
                                            amt=Integer.valueOf(od.getAmount(Integer.valueOf(request.getSession().getAttribute("LogIn").toString()),request.getParameter("GST")));}%>
                                    <%= database.NumberConvert.getInstance().Convert(amt)%></i>
                                </th>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
        ${Message}
    </body>
</html>
