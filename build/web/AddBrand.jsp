<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Brand</title>
    </head>
    <body>
        <c:import url="Header.jsp"></c:import>
        <br>
        <br>
        <h2 style="font-size: 100px; color: silver; font-family: fantasy; text-align: center; margin: auto">Add Brand</h2>
        <form action="AddBrand" method="post" style="width: 90%; text-align: center; margin: auto">
            <input type="text" placeholder="Brand Name" name="brandName" class="form form-control" required >
            <br>
            <input type="text" placeholder="Supplier Name" name="supplierName" class="form form-control" required >
            <br>
            <input type="number" placeholder="Contact Number" name="contactNumber" class="form form-control" required >
            <br>
            <textarea class="form form-control" placeholder="Description" name="description" ></textarea>
            <br>
            <input type="submit" class="btn btn-success" value="Add Brand">
        </form>
        ${error}
    </body>
</html>
