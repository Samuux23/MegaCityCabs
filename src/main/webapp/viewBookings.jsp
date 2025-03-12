<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Bookings</title>
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
            margin: 30px auto; /* Added margin for better spacing */
            box-shadow: 0 6px 12px rgba(0,0,0,0.1);
            max-width: 1400px; /* Increased max-width for larger screens */
        }

        h2 {
            color: #333;
            font-weight: 700;
            margin-bottom: 30px;
            text-align: center; /* Center the title */
        }

        .table {
            margin-bottom: 20px;
        }

        .table thead th {
            background-color: #FFECB3;
            color: #333;
            border-bottom: 2px solid #FF9800;
            text-align: center; /* Center the table header text */
        }

        .table td {
            vertical-align: middle;
            text-align: center; /* Center the table data text */
        }

        .btn-primary, .btn-danger, .btn-secondary {
            background-color: #FF9800;
            border: none;
            color: #fff;
            padding: 8px 16px; /* Adjusted padding for smaller buttons */
            border-radius: 8px;
            transition: background-color 0.3s ease;
            font-size: 0.9rem; /* Adjusted font size for buttons */
        }

        .btn-primary:hover, .btn-danger:hover, .btn-secondary:hover {
            background-color: #FB8C00;
        }

        .btn-danger {
            background-color: #f44336; /* Red color for delete */
        }

        .btn-danger:hover {
            background-color: #d32f2f; /* Darker red color for delete hover */
        }

        .btn-secondary {
            background-color: #6c757d; /* Gray color for back button */
        }

        .btn-secondary:hover {
            background-color: #5a6268; /* Darker gray color for back button hover */
        }

        input[type="number"] {
            width: 70px;
            padding: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center; /* Center the text inside the input box */
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h2><i class="fas fa-clipboard-list"></i> All Bookings</h2>
        <table class="table table-bordered table-striped">
            <thead class="table-light">
                <tr>
                    <th>Order #</th>
                    <th>Customer ID</th>
                    <th>Driver ID</th>
                    <th>Vehicle ID</th>
                    <th>Booking Date</th>
                    <th>Destination</th>
                    <th>Distance (KM)</th>
                    <th>Price (LKR)</th>
                    <th>Discount (LKR)</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="b" items="${bookings}">
                <tr>
                    <td>${b.orderNumber}</td>
                    <td>${b.customer != null ? b.customer.id : 'N/A'}</td>
                    <td>${b.driver != null ? b.driver.id : 'N/A'}</td>
                    <td>${b.vehicle != null ? b.vehicle.id : 'N/A'}</td>
                    <td>${b.bookingDate}</td>
                    <td>${b.destination}</td>
                    <td>${b.distance}</td>
                    <td>${b.price}</td>
                    <td>${b.discount}</td>
                    <td>${b.status}</td>
                    <td>
                        <form action="UpdateDiscountServlet" method="post" style="display:inline-block;">
                            <input type="hidden" name="bookingId" value="${b.orderNumber}" />
                            <input type="number" name="discount" step="0.01" placeholder="Discount" style="width: 70px;" />
                            <button type="submit" class="btn btn-sm btn-primary">Update</button>
                        </form>
                        
                        <a href="DeleteBookingServlet?bookingId=${b.orderNumber}" 
                           class="btn btn-sm btn-danger mt-1" 
                           onclick="return confirm('Are you sure you want to delete this booking?');">
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