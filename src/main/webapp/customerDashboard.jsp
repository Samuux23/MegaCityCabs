<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Background Image for entire page */
        body {
            font-family: 'Arial', sans-serif;
            background: url("images/your-customer-bg.jpg") no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
        }
        /* Header bar styling */
        .header-bar {
            background-color: rgba(0, 0, 0, 0.7);
            color: #fff;
            padding: 20px;
            text-align: center;
            border-radius: 0 0 10px 10px;
            margin-bottom: 30px;
            position: relative; /* For placing the sign-out button */
        }
        .header-bar h2 {
            margin: 0;
        }
        .sign-out-btn {
            position: absolute;
            top: 20px;
            right: 20px;
        }
        /* Container styling */
        .content-container {
            max-width: 900px;
            margin: 2rem auto;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            padding: 2rem;
        }
        /* Table and form styling */
        .table th,
        .table td {
            text-align: center;
            vertical-align: middle;
        }
        .form-control {
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
            padding: 0.75rem;
        }
        .form-control:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }
        .btn {
            margin-top: 10px;
            padding: 0.75rem;
            border-radius: 5px;
            font-size: 1rem;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .btn-info {
            background-color: #17a2b8;
            border: none;
        }
        .btn-info:hover {
            background-color: #138496;
        }
        .btn-danger {
            background-color: #dc3545;
            border: none;
        }
        .btn-danger:hover {
            background-color: #c82333;
        }
        .btn-success {
            background-color: #28a745;
            border: none;
        }
        .btn-success:hover {
            background-color: #218838;
        }
        /* Card styling */
        .card {
            margin-bottom: 1.5rem;
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .card-header {
            background-color: #007bff;
            color: white;
            font-weight: bold;
            border-radius: 10px 10px 0 0;
        }
    </style>
</head>
<body>

    <!-- Header with sign-out button -->
    <div class="header-bar">
        <h2>Welcome to Customer Dashboard</h2>
        <a href="login.jsp" class="btn btn-danger sign-out-btn">Sign Out</a>
    </div>

    <div class="content-container">
        <!-- Create a New Booking Section -->
        <div class="card">
            <div class="card-header">
                <h3 class="card-title mb-0">Create a New Booking</h3>
            </div>
            <div class="card-body">
                <form id="bookingForm">
                    <label for="bookingDate" class="form-label">Date:</label>
<input type="date" class="form-control" 
       id="bookingDate" 
       name="bookingDate" required />

                    <div class="mb-3">
                        <label for="vehicleType" class="form-label">Vehicle Type:</label>
                        <select class="form-select" id="vehicleType" name="vehicleType" required>
                            <option value="">Select Type</option>
                            <option value="Sedan">Sedan</option>
                            <option value="SUV">SUV</option>
                            <option value="Van">Van</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="destination" class="form-label">Destination:</label>
                        <input type="text" class="form-control" id="destination" name="destination" required />
                    </div>
                    <div class="mb-3">
                        <label for="distance" class="form-label">Distance (KM):</label>
                        <input type="number" step="0.1" class="form-control" id="distance" name="distance" required />
                    </div>
                    <!-- Optional discount field if needed -->
                    <button type="submit" class="btn btn-primary">Book Ride</button>
                </form>
            </div>
        </div>

        <!-- Guidelines Section -->
        <div class="text-center mt-4">
            <a href="CustomerGuidelineServlet" class="btn btn-info">View Guidelines</a>
        </div>

        <hr class="my-4">

        <!-- Your Reservations Section -->
        <div class="card">
            <div class="card-header">
                <h3 class="card-title mb-0">Your Reservations</h3>
            </div>
            <div class="card-body">
                <table class="table table-striped" id="reservationsTable">
                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Date & Time</th>
                            <th>Destination</th>
                            <th>Distance (KM)</th>
                            <th>Discount (LKR)</th>
                            <th>Driver</th>
                            <th>Vehicle</th>
                            <th>Price</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="reservationsBody">
                        <!-- Bookings will be loaded here via AJAX -->
                    </tbody>
                </table>
            </div>
        </div>
    </div> <!-- end content-container -->

    <script>
        // Handle new booking form submission
        document.getElementById("bookingForm").addEventListener("submit", function(event) {
            event.preventDefault();
            let formData = new URLSearchParams(new FormData(this)).toString();

            fetch("BookingServlet", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("Booking successful!");
                    loadBookings();
                } else {
                    alert("Failed to book ride: " + data.message);
                }
            })
            .catch(error => console.error("Error:", error));
        });

        // Load the bookings for the logged-in customer
        function loadBookings() {
            fetch("CustomerBookingsServlet")
                .then(response => response.json())
                .then(data => {
                    let tableBody = document.getElementById("reservationsBody");
                    tableBody.innerHTML = "";

                    if (data.length === 0) {
                        tableBody.innerHTML = `<tr><td colspan="10" class="text-center">No bookings found.</td></tr>`;
                        return;
                    }

                    data.forEach(booking => {
                        // Format the booking date/time for display
                        let bookingDateStr = booking.bookingDate 
                            ? new Date(booking.bookingDate).toLocaleString() 
                            : "N/A";

                        // Highlight status if Approved
                        let statusHtml = booking.status === "Approved"
                            ? '<span class="text-success fw-bold">Approved</span>'
                            : booking.status;

                        // Build the row HTML
                        let row = `
                            <tr>
                                <td>${booking.orderNumber}</td>
                                <td>${bookingDateStr}</td>
                                <td>${booking.destination}</td>
                                <td>${booking.distance}</td>
                                <td>${booking.discount ? booking.discount : 0}</td>
                                <td>${booking.driver ? booking.driver.name : "N/A"}</td>
                                <td>${booking.vehicle ? booking.vehicle.vehicleNumber : "N/A"}</td>
                                <td>${booking.price} LKR</td>
                                <td>${statusHtml}</td>
                                <td>
                                    <button class="btn btn-danger btn-sm" 
                                            onclick="cancelBooking(${booking.orderNumber})">
                                        Cancel
                                    </button>
                                    <button class="btn btn-success btn-sm"
                                            onclick="printBill(
                                                ${booking.orderNumber},
                                                '${bookingDateStr}',
                                                '${booking.destination}',
                                                '${booking.distance}',
                                                '${booking.driver ? booking.driver.name : 'N/A'}',
                                                '${booking.vehicle ? booking.vehicle.vehicleNumber : 'N/A'}',
                                                '${booking.price}'
                                            )">
                                        Print Bill
                                    </button>
                                </td>
                            </tr>
                        `;
                        tableBody.innerHTML += row;
                    });
                })
                .catch(error => console.error("Error fetching bookings:", error));
        }

        // Cancel an existing booking
        function cancelBooking(bookingId) {
            if (confirm("Are you sure you want to cancel this booking?")) {
                fetch("CancelBookingServlet", {
                    method: "POST",
                    headers: { "Content-Type": "application/x-www-form-urlencoded" },
                    body: new URLSearchParams({ bookingId: bookingId }).toString()
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert("Booking cancelled successfully.");
                        loadBookings();
                    } else {
                        alert("Failed to cancel booking: " + data.message);
                    }
                })
                .catch(error => console.error("Error cancelling booking:", error));
            }
        }

        // Print the booking bill in a new window
        function printBill(orderNumber, dateTime, destination, distance, driver, vehicle, price) {
            let billWindow = window.open('', '_blank');
            billWindow.document.write(`
                <html>
                <head>
                    <title>Booking Invoice</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif; 
                            padding: 20px; 
                            text-align: center;
                        }
                        .bill-container {
                            width: 400px; 
                            margin: auto; 
                            border: 1px solid #ddd; 
                            padding: 20px; 
                            text-align: left;
                        }
                        h2 { text-align: center; }
                        table {
                            width: 100%;
                            border-collapse: collapse;
                            margin-top: 20px;
                        }
                        td {
                            padding: 8px;
                            border-bottom: 1px solid #ddd;
                        }
                        .total {
                            font-weight: bold;
                            font-size: 18px;
                        }
                    </style>
                </head>
                <body>
                    <div class="bill-container">
                        <h2>MegaCab Booking Receipt</h2>
                        <table>
                            <tr><td><strong>Booking ID:</strong></td><td>${orderNumber}</td></tr>
                            <tr><td><strong>Date & Time:</strong></td><td>${dateTime}</td></tr>
                            <tr><td><strong>Destination:</strong></td><td>${destination}</td></tr>
                            <tr><td><strong>Distance (KM):</strong></td><td>${distance}</td></tr>
                            <tr><td><strong>Driver:</strong></td><td>${driver}</td></tr>
                            <tr><td><strong>Vehicle:</strong></td><td>${vehicle}</td></tr>
                            <tr><td class="total"><strong>Total Price:</strong></td><td>${price} LKR</td></tr>
                        </table>
                    </div>
                </body>
                </html>
            `);
            billWindow.document.close();
            billWindow.print();
        }

        // Load bookings on page load
        loadBookings();
    </script>
</body>
</html>
