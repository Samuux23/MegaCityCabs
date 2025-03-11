<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All Registered Customers</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4">All Registered Customers</h2>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Address</th>
                <th>NIC</th>
                <th>Contact Number</th>
                <!-- New Actions column -->
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.address}</td>
                <td>${customer.nic}</td>
                <td>${customer.contactNumber}</td>
                <!-- Delete button -->
                <td>
                    <a href="DeleteCustomerServlet?customerId=${customer.id}" 
                       class="btn btn-sm btn-danger" 
                       onclick="return confirm('Are you sure you want to delete this customer?');">
                        Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="adminDashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
</div>
</body>
</html>
