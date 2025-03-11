<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Driver Management</title>
</head>
<body>
<h2>Driver Management</h2>
<h3>Add New Driver</h3>
<form action="ManagementServlet" method="post">
    <input type="hidden" name="entity" value="driver" />
    <input type="hidden" name="action" value="add" />
    Name: <input type="text" name="name" required /><br/>
    License Number: <input type="text" name="licenseNumber" required /><br/>
    <input type="submit" value="Add Driver" />
</form>
<br/>
<h3>Existing Drivers</h3>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>License Number</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="driver" items="${drivers}">
        <tr>
            <td>${driver.id}</td>
            <td>${driver.name}</td>
            <td>${driver.licenseNumber}</td>
            <td>
                <a href="ManagementServlet?action=edit&entity=driver&id=${driver.id}">Edit</a>
                <a href="ManagementServlet?action=delete&entity=driver&id=${driver.id}" 
                   onclick="return confirm('Are you sure you want to delete this driver?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>


