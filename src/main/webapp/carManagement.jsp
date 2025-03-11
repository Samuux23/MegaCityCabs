<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Car Management</title>
</head>
<body>
<h2>Car Management</h2>
<h3>Add New Car</h3>
<form action="ManagementServlet" method="post">
    <input type="hidden" name="entity" value="car" />
    <input type="hidden" name="action" value="add" />
    Model: <input type="text" name="model" required /><br/>
    Registration Number: <input type="text" name="registrationNumber" required /><br/>
    <input type="submit" value="Add Car" />
</form>
<br/>
<h3>Existing Cars</h3>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Model</th>
        <th>Registration Number</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>${car.id}</td>
            <td>${car.model}</td>
            <td>${car.registrationNumber}</td>
            <td>
                <a href="ManagementServlet?action=edit&entity=car&id=${car.id}">Edit</a>
                <a href="ManagementServlet?action=delete&entity=car&id=${car.id}" 
                   onclick="return confirm('Are you sure you want to delete this car?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

