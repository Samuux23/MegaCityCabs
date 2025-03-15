<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
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
        .dashboard-header {
            background: linear-gradient(135deg, #FFEB3B, #FF9800);
            color: #fff;
            padding: 30px;
            text-align: center;
            border-bottom-left-radius: 15px;
            border-bottom-right-radius: 15px;
            position: relative;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
        }
        .dashboard-header h2 {
            margin: 0;
            font-size: 2rem;
            font-weight: 700;
        }
        .sign-out-btn {
            position: absolute;
            top: 20px;
            right: 20px;
            background-color: rgba(255,255,255,0.9);
            color: #FF9800;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: 600;
            transition: background-color 0.3s ease;
        }
        .sign-out-btn:hover {
            background-color: #fff;
        }
        .container {
            background-color: #fff;
            border-radius: 15px;
            padding: 40px;
            margin: -20px auto 30px;
            box-shadow: 0 6px 12px rgba(0,0,0,0.1);
            max-width: 1200px;
        }
        .card {
            border: none;
            border-radius: 15px;
            margin-bottom: 30px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .card-body {
            padding: 30px;
        }
        .card-title {
            font-size: 1.8rem;
            font-weight: 700;
            color: #333;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            font-weight: 600;
            color: #555;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="number"],
        input[type="date"],
        select {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 8px;
            transition: border-color 0.3s ease;
        }
        input[type="text"]:focus,
        input[type="number"]:focus,
        input[type="date"]:focus,
        select:focus {
            border-color: #FF9800;
            outline: none;
            box-shadow: 0 0 5px rgba(255,152,0,0.5);
        }
        .btn-primary {
            background-color: #FF9800;
            border: none;
            color: #fff;
            padding: 12px 25px;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }
        .btn-primary:hover {
            background-color: #FB8C00;
        }
        .btn-info {
            background-color: #FFC107;
            border: none;
            color: #fff;
            padding: 12px 25px;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }
        .btn-info:hover {
            background-color: #FFB300;
        }

       /* Custom "Cancel"*/
         .btn-cancel {
            background-color: #9c0c0c;
            border: none;
            color: #fff;
            width: 38px;     
            height: 38px;
            padding: 0;       
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 0.9em;
            box-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);
            display: inline-flex; 
            align-items: center;
            justify-content: center;
            line-height: 1;    
            text-align: center;
        }

        .btn-cancel:hover {
            background-color: #d32f2f;
        }

        /* Custom "Print Bill" button*/
        .btn-print {
            background-color: #f5c425;
            border: none;
            color: #fff;
            width: 38px;      
            height: 38px;
            padding: 0;        
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 0.9em;
            box-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);
            display: inline-flex; 
            align-items: center;
            justify-content: center;
            line-height: 1;    
            text-align: center;
        }

        .btn-print:hover {
            background-color: #d69f06;
        }

        /* Styles for action buttons  */
        .btn-actions {
            border: none;
            color: #fff;
            padding: 0;   
            border-radius: 5px;
            transition: background-color 0.3s ease;
            font-size: 0.9rem;
             width: 38px;      
            height: 38px;
              display: inline-flex; 
            align-items: center;
            justify-content: center;
             text-align: center;
        }

        .btn-danger {
            background-color: #e57373;
            border: none;
            color: #fff;
            padding: 12px 25px;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }
        .btn-danger:hover {
            background-color: #d32f2f;
        }
        .btn-success {
            background-color: #81c784;
            border: none;
            color: #fff;
            padding: 12px 25px;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }
        .btn-success:hover {
            background-color: #66bb6a;
        }
        .table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24); 
            border-radius: 8px; 
            overflow: hidden; 
        }

        .table th,
        .table td {
            text-align: center;
            vertical-align: middle;
            padding: 12px 10px;
            border-bottom: 1px solid #eee; 
        }

        .table th {
            background-color: #f5f5f5;
            font-weight: 500; 
            color: #555;
        }

        .table tbody tr:hover {
            background-color: #f9f9f9;
        }
        .table tbody tr:last-child td {
            border-bottom: none;
        }

    </style>
</head>
<body>

    <!-- Header with sign-out button -->
    <div class="dashboard-header">
        <h2><i class="fas fa-user"></i> Customer Dashboard</h2>
        <!-- Sign Out button linking to login.jsp -->
        <a href="login.jsp" class="btn sign-out-btn">Sign Out</a>
    </div>

    <div class="container">

        <!-- Create a New Booking Section -->
        <div class="card">
            <div class="card-body">
                <h3 class="card-title"><i class="fas fa-plus-circle"></i> Create a New Booking</h3>
                <form id="bookingForm">
                    <div class="form-group">
                        <label for="bookingDate">Date:</label>
                        <input type="date" class="form-control" id="bookingDate" name="bookingDate" required />
                    </div>
                    <div class="form-group">
                        <label for="vehicleType">Vehicle Type:</label>
                        <select class="form-control" id="vehicleType" name="vehicleType" required />
                            <option value="">Select Type</option>
                            <option value="Sedan">Sedan</option>
                            <option value="SUV">SUV</option>
                            <option value="Van">Van</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="destination">Destination:</label>
                        <input type="text" class="form-control" id="destination" name="destination" required />
                    </div>
                    <div class="form-group">
                        <label for="distance">Distance (KM):</label>
                        <input type="number" step="0.1" class="form-control" id="distance" name="distance" required />
                    </div>
                    <button type="submit" class="btn btn-primary"><i class="fas fa-calendar-plus"></i> Book Ride</button>
                </form>
            </div>
        </div>

        <!-- Guidelines Section -->
        <div class="text-center mt-4">
            <a href="CustomerGuidelineServlet" class="btn btn-info"><i class="fas fa-info-circle"></i> View Guidelines</a>
        </div>

        <hr class="my-4">

        <!-- Your Reservations Section -->
        <div class="card">
            <div class="card-body">
                <h3 class="card-title"><i class="fas fa-list-alt"></i> Your Reservations</h3>
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

    </div> 

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
                                     <button class="btn btn-cancel btn-actions" onclick="cancelBooking(${booking.orderNumber})">
                                        <i class="fas fa-times"></i>
                                    </button>
                                    <button class="btn btn-print btn-actions"
                                            onclick="printBill(
                                                ${booking.orderNumber},
                                                '${bookingDateStr}',
                                                '${booking.destination}',
                                                '${booking.distance}',
                                                '${booking.driver ? booking.driver.name : 'N/A'}',
                                                '${booking.vehicle ? booking.vehicle.vehicleNumber : 'N/A'}',
                                                '${booking.price}'
                                            )">
                                        <i class="fas fa-print"></i>
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