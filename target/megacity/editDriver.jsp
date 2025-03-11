<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Driver</title>
</head>
<body>
<h2>Edit Driver</h2>
<form action="ManagementServlet" method="post">
    <input type="hidden" name="entity" value="driver" />
    <input type="hidden" name="action" value="update" />
    <input type="hidden" name="id" value="${driver.id}" />
    Name: <input type="text" name="name" value="${driver.name}" required /><br/>
    License Number: <input type="text" name="licenseNumber" value="${driver.licenseNumber}" required /><br/>
    <input type="submit" value="Update Driver" />
</form>
</body>
</html>
