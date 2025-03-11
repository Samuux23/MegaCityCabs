<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Car</title>
</head>
<body>
<h2>Edit Car</h2>
<form action="ManagementServlet" method="post">
    <input type="hidden" name="entity" value="car" />
    <input type="hidden" name="action" value="update" />
    <input type="hidden" name="id" value="${car.id}" />
    Model: <input type="text" name="model" value="${car.model}" required /><br/>
    Registration Number: <input type="text" name="registrationNumber" value="${car.registrationNumber}" required /><br/>
    <input type="submit" value="Update Car" />
</form>
</body>
</html>

