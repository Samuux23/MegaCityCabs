<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Dashboard Guidelines</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* Light gray background */
        }
        .card {
            border: none; /* Remove card borders */
            box-shadow: 0 0.25rem 0.5rem rgba(0, 0, 0, 0.05); /* Subtle shadow */
        }
        .list-group-item {
            border: none; /* Remove list group borders */
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h2 class="mb-0">Driver Dashboard Guidelines</h2>
            </div>
            <div class="card-body">
                <p class="lead">Welcome to your MegaCity Cab driver dashboard! This guide helps you manage vehicles and bookings.</p>

                <h3 class="mt-4">Managing Your Vehicles:</h3>
                <ol class="list-group list-group-numbered">
                    <li class="list-group-item"><strong>Add New Vehicle:</strong> Add a new vehicle to your profile.  Enter the vehicle number and select the vehicle type.</li>
                    <li class="list-group-item"><strong>Your Vehicles Table:</strong> Displays all vehicles registered to your account.</li>
                    <li class="list-group-item"><strong>Delete Vehicle:</strong> Remove a vehicle from your profile by clicking the <strong>Delete</strong> button.</li>
                </ol>

                <h3 class="mt-4">Managing Bookings:</h3>
                <ul class="list-group">
                    <li class="list-group-item"><strong>Booking Details Table:</strong> Shows your assigned bookings and information:
                        <ul>
                            <li>Booking ID</li>
                            <li>Customer Name/Number</li>
                            <li>Destination/Distance</li>
                            <li>Vehicle Number/Status</li>
                        </ul>
                    </li>
                    <li class="list-group-item"><strong>Complete Booking:</strong> After safely completing a ride, click the <strong>Complete</strong> button.</li>
                </ul>

                <h3 class="mt-4">Need Help? Contact Driver Support!</h3>
                <ul class="list-group">
                    <li class="list-group-item">
                        <strong>Phone:</strong> +1-555-DRIVERS (555-3748) - Available 24/7
                    </li>
                    <li class="list-group-item">
                        <strong>Email:</strong> <a href="mailto:driversupport@megacitycab.com">driversupport@megacitycab.com</a>
                    </li>
                    <li class="list-group-item">
                        <strong>Emergency Hotline:</strong> +1-555-911 (for immediate safety concerns)
                    </li>
                    <li class="list-group-item">
                        For assistance with navigating the dashboard, understanding booking statuses, or any technical issues, please contact our support team.
                    </li>
                </ul>

                <a href="DriverVehiclesServlet" class="btn btn-secondary mt-3">Back to Dashboard</a>
            </div>
        </div>
    </div>

    <script src="https://kit.fontawesome.com/your-font-awesome-kit.js" crossorigin="anonymous"></script>
</body>
</html>
