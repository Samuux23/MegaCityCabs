<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Bookings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4">All Bookings</h2>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>Order #</th>
                <th>Customer ID</th>
                <th>Driver ID</th>
                <th>Vehicle ID</th>
                <th>Booking Date</th>
                <th>Destination</th>
                <th>Distance (KM)</th>
                <th>Price (LKR)</th>
                <!-- New Discount column -->
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
                <!-- Show the current discount value -->
                <td>${b.discount}</td>
                <td>${b.status}</td>
                <td>
                    <!-- Form to update the discount -->
                    <form action="UpdateDiscountServlet" method="post" style="display:inline-block;">
                        <input type="hidden" name="bookingId" value="${b.orderNumber}" />
                        <input type="number" name="discount" step="0.01" placeholder="Discount" style="width: 80px;" />
                        <button type="submit" class="btn btn-sm btn-primary">Update</button>
                    </form>
                    
                    <!-- Delete button -->
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
    <a href="adminDashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
</div>
</body>
</html>
