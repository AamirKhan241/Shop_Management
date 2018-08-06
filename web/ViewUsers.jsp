<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="user.Users"%>
<%@page import="user.Users_DAO_Implt"%>
<%@page import="user.Users_DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
    <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Add User</i></b></center>
        <br>
        <table class="table table-striped" style="width: 70%; padding: auto; margin: auto">
            <thead>
                <tr>
                    <th>User ID.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <%
                Users_DAO uDAO=new Users_DAO_Implt();
                List<Users> list=uDAO.getUser();
                for (Users u : list) {
                        System.out.println(u.getUsername());
                    }
                request.setAttribute("user", list);
            %>
            <tbody id="profiles">
                <c:forEach items="${user}" var="u">
                    <tr style="text-align: center">
                        <td>${u.getUser_id()}</td>
                        <td>${u.getUsername()}</td>
                        <td><input type="button" class="btn btn-default" value="View Password" onclick="alert('Password: ${u.getPassword()}')"></td>
                        <td><a href="UpdateUser.jsp?serial=${u.getUser_id()}" class="btn btn-success">Update</a></td>
                        <td><a href="DeleteUser?serial=${u.getUser_id()}" class="btn btn-danger">Delete</a></td>
                    </tr>
                </c:forEach>            
            <style>
                input{
                    width:100%;
                    height:4vh;
                }
            </style>
                    <tr style="text-align: center; background-color: #ccccff; border: 1px solid" >
                        <form action="AddUser" method="post">
                            <td>New Item's Details: </td>
                            <td><input type="text" placeholder="Username..." required name="username" class="form form-control"></td>
                            <td><input type="password" placeholder="Password..." required="" name="password" class="form form-control"></td>
                            <td colspan="2"><input type="submit" class="btn btn-danger" value="Submit"></td>
                        </form>
                    </tr>                    
            </tbody>
        </table>
    </body>
</html>
