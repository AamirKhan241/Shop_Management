<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="user.Users"%>
<%@page import="user.Users_DAO_Implt"%>
<%@page import="user.Users_DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update User</title>
    </head>
    <%
        Users_DAO uDAO=new Users_DAO_Implt();
        Users u=uDAO.getUser(Integer.valueOf(request.getParameter("serial")));
    %>
    <body>
        <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Updating User</i></b></center>

        <form action="UpdateUser?serial=<%=u.getUser_id()%>" method="post">
            <label style="float: left">Username</label>
            <input type="text" value="<%=u.getUsername()%>" class="form form-control" name="username" readonly>
            <br>
            <label style="float: left">Password</label>
            <input type="password" value="<%=u.getPassword()%>" required="" class="form form-control" name="password">
            <br>
            <input type="submit" class="btn btn-Success" value="Update" style="width:30%">
        </form>
    </body>
</html>
