<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All Registered Customers</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #FFF9C4, #FFE0B2);
            color: #444;
            margin: 0;
            padding: 0;
        }

        .container {
            background-color: #fff;
            border-radius: 15px;
            padding: 40px;
            margin: 30px auto;
            box-shadow: 0 6px 12px rgba(0,0,0,0.1);
            max-width: 1200px;
        }

        h2 {
            color: #333;
            font-weight: 700;
            margin-bottom: 30px;
            text-align: center;
        }

        .table {
            margin-bottom: 20px;
        }

        .table thead th {
            background-color: #FFECB3;
            color: #333;
            border-bottom: 2px solid #FF9800;
            text-align: center;
        }

        .table td {
            vertical-align: middle;
            text-align: center;
        }

        .btn-danger, .btn-secondary {
            background-color: #FF9800;
            border: none;
            color: #fff;
            padding: 8px 16px;
            border-radius: 8px;
            transition: background-color 0.3s ease;
            font-size: 0.9rem;
        }

        .btn-danger:hover, .btn-secondary:hover {
            background-color: #FB8C00;
        }

        .btn-danger {
            background-color: #f44336;
        }

        .btn-danger:hover {
            background-color: #d32f2f;
        }

        .btn-secondary {
            background-color: #6c757d;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h2><i class="fas fa-user-friends"></i> All Registered Customers</h2>
        <table class="table table-bordered table-striped">
            <thead class="table-light">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>NIC</th>
                    <th>Contact Number</th>
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
        <a href="adminDashboard.jsp" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Back to Dashboard</a>
    </div>
</body>
</html>