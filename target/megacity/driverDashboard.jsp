<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Driver Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Background Image for the entire page
           Replace "images/your-driver-bg.jpg" with your actual image path or URL */
        body {
            font-family: 'Arial', sans-serif;
            background: url("images/your-driver-bg.jpg") no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
        }

        /* Header bar styling */
        .header-bar {
            background-color: rgba(0, 0, 0, 0.7); /* Slightly transparent black */
            color: #fff;
            padding: 20px;
            text-align: center;
            border-radius: 0 0 10px 10px;
            margin-bottom: 30px;
            position: relative; /* So we can place the sign-out button absolutely */
        }
        .header-bar h2 {
            margin: 0;
        }

        /* Sign Out button in the top-right corner */
        .sign-out-btn {
            position: absolute;
            top: 20px;
            right: 20px;
        }

        /* Semi‑transparent container for main content */
        .content-container {
            max-width: 900px;
            margin: 2rem auto;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            padding: 2rem;
        }

        .form-control {
            margin-bottom: 10px;
        }
        .btn {
            margin-top: 10px;
        }
        /* Additional styling (optional) */
        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>

    <!-- Header with sign-out button -->
    <div class="header-bar">
        <h2>Welcome to Driver Dashboard</h2>
        <!-- Sign Out button linking to login.jsp -->
        <a href="login.jsp" class="btn btn-danger sign-out-btn">Sign Out</a>
    </div>

    <div class="content-container">
        <!-- Form to Add New Vehicle (no image field) -->
        <h3>Add New Vehicle</h3>
        <form id="vehicleForm" action="VehicleServlet" method="post">
            <div class="mb-3">
                <label for="vehicleNumber" class="form-label">Vehicle Number:</label>
                <input type="text" class="form-control" id="vehicleNumber" name="vehicleNumber" required />
            </div>
            <div class="mb-3">
                <label for="vehicleType" class="form-label">Vehicle Type:</label>
                <select class="form-select" id="vehicleType" name="vehicleType" required>
                    <option value="">Select Type</option>
                    <option value="Sedan">Sedan</option>
                    <option value="SUV">SUV</option>
                    <option value="Van">Van</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add Vehicle</button>
        </form>

        <hr class="my-4">

        <a href="DriverGuidelineServlet" class="btn btn-info">View Guidelines</a>
        <hr class="my-4">

        <h3>Your Vehicles</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Vehicle Number</th>
                    <th>Vehicle Type</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody id="vehicleTableBody">
                <!-- Vehicles loaded via JavaScript -->
            </tbody>
        </table>

        <hr class="my-4">

        <h3>Booking Details</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Booking ID</th>
                    <th>Customer Name</th>
                    <th>Customer Number</th>
                    <th>Destination</th>
                    <th>Distance (km)</th>
                    <th>Vehicle Number</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody id="bookingTableBody">
                <!-- Bookings loaded via JavaScript -->
            </tbody>
        </table>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {

            // 1) Load Vehicles
            function loadVehicles() {
                fetch("DriverVehiclesServlet")
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`HTTP error! Status: ${response.status}`);
                        }
                        return response.json();
                    })
                    .then(data => {
                        let tableBody = document.getElementById("vehicleTableBody");
                        tableBody.innerHTML = "";

                        if (!data || data.length === 0) {
                            tableBody.innerHTML = `<tr><td colspan="3" class="text-center">No vehicles found.</td></tr>`;
                            return;
                        }

                        data.forEach(vehicle => {
                            let row = `<tr>
                                <td>${vehicle.vehicleNumber}</td>
                                <td>${vehicle.vehicleType}</td>
                                <td>
                                    <a href="DeleteVehicleServlet?vehicleId=${vehicle.id}"
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('Are you sure you want to delete this vehicle?');">
                                        Delete
                                    </a>
                                </td>
                            </tr>`;
                            tableBody.innerHTML += row;
                        });
                    })
                    .catch(error => {
                        console.error("Error fetching vehicles:", error);
                        alert("Failed to load vehicles.");
                    });
            }

            // 2) Load Bookings
            function loadBookings() {
                fetch("DriverBookingsServlet")
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`HTTP error! Status: ${response.status}`);
                        }
                        return response.text();
                    })
                    .then(data => {
                        document.getElementById("bookingTableBody").innerHTML = data;
                    })
                    .catch(error => {
                        console.error("Error fetching bookings:", error);
                        alert("Failed to load bookings.");
                    });
            }

            // 3) Approve Booking
            window.approveBooking = function(bookingId) {
                fetch("ApproveBookingServlet", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: new URLSearchParams({
                        bookingId: bookingId
                    }).toString()
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert(data.message);
                        loadBookings(); // Reload booking data to see the updated status
                    } else {
                        alert("Failed to approve booking: " + data.message);
                    }
                })
                .catch(error => {
                    console.error("Error approving booking:", error);
                    alert("Failed to approve booking.");
                });
            };

            // 4) Complete Booking
            window.completeBooking = function(bookingId) {
                fetch("CompleteBookingServlet", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: new URLSearchParams({
                        bookingId: bookingId
                    }).toString()
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert(data.message);
                        loadBookings();
                    } else {
                        alert("Failed to complete booking: " + data.message);
                    }
                })
                .catch(error => {
                    console.error("Error completing booking:", error);
                    alert("Failed to complete booking.");
                });
            };

            // 5) Handle Add Vehicle Form Submission
            document.getElementById("vehicleForm").addEventListener("submit", function(event) {
                event.preventDefault();
                const formData = new URLSearchParams(new FormData(this)).toString();

                fetch("VehicleServlet", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: formData
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert("Vehicle added successfully!");
                        loadVehicles();
                        // Optionally, clear the form
                        document.getElementById("vehicleForm").reset();
                    } else {
                        alert("Failed to add vehicle: " + data.message);
                    }
                })
                .catch(error => {
                    console.error("Error adding vehicle:", error);
                    alert("Failed to add vehicle.");
                });
            });

            // On page load, fetch both vehicles and bookings
            loadVehicles();
            loadBookings();
        });
    </script>
</body>
</html>

