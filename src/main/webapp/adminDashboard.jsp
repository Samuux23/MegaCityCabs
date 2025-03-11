<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        /* Modern Yellow-Orange Palette */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #FFF9C4, #FFE0B2); /* light yellow to light orange gradient */
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
        .data-card {
            background-color: #FFECB3;
            border-radius: 15px;
            padding: 30px;
            text-align: center;
            margin-bottom: 30px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .data-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 15px rgba(0,0,0,0.2);
        }
        .data-card i {
            font-size: 3rem;
            margin-bottom: 15px;
            color: #FF9800;
        }
        .data-card h2 {
            font-size: 2.5rem;
            margin: 0;
            font-weight: bold;
            color: #333;
        }
        .data-card p {
            font-size: 1.2rem;
            margin-bottom: 15px;
            color: #666;
        }
        .data-card .btn {
            background-color: #FF9800;
            border: none;
            color: #fff;
            padding: 10px 20px;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }
        .data-card .btn:hover {
            background-color: #FB8C00;
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
        input[type="password"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 8px;
            transition: border-color 0.3s ease;
        }
        input[type="text"]:focus,
        input[type="number"]:focus,
        input[type="password"]:focus {
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
        .btn-success {
            background-color: #FFC107;
            border: none;
            color: #fff;
            padding: 12px 25px;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }
        .btn-success:hover {
            background-color: #FFB300;
        }
    </style>
</head>
<body>
    <!-- Header with sign-out button -->
    <div class="dashboard-header">
        <h2><i class="fas fa-chart-line"></i> Admin Dashboard</h2>
        <!-- Sign Out button linking to login.jsp -->
        <a href="login.jsp" class="btn sign-out-btn">Sign Out</a>
    </div>

    <div class="container">
        <!-- Row for the 3 data cards -->
        <div class="row">
            <!-- Total Bookings Card -->
            <div class="col-md-4">
                <div class="data-card">
                    <i class="fas fa-ticket-alt"></i>
                    <%-- <h2>${totalBookings}</h2> --%>
                    <p>Total Bookings</p>
                    <a href="ViewBookingsServlet" class="btn btn-primary">View Details</a>
                </div>
            </div>
            <!-- Active Drivers Card -->
            <div class="col-md-4">
                <div class="data-card">
                    <i class="fas fa-taxi"></i>
                    <%-- <h2>${activeDrivers}</h2> --%>
                    <p>Active Drivers</p>
                    <a href="ViewDriversServlet" class="btn btn-primary">View Details</a>
                </div>
            </div>
            <!-- Registered Customers Card -->
            <div class="col-md-4">
                <div class="data-card">
                    <i class="fas fa-user-friends"></i>
                    <%-- <h2>${registeredCustomers}</h2> --%>
                    <p>Registered Customers</p>
                    <a href="ViewCustomersServlet" class="btn btn-primary">View Details</a>
                </div>
            </div>
        </div>

        <!-- Section to update a fixed rate per KM, centered -->
        <div class="row justify-content-center mt-4">
            <div class="col-md-6 text-center">
                <h3 class="mb-3">Update Fixed Rate per KM</h3>
                <form action="UpdateRateServlet" method="post">
                    <div class="mb-3">
                        <label for="ratePerKm" class="form-label">New Rate (LKR per KM):</label>
                        <input type="number" step="0.01" class="form-control" id="ratePerKm" name="ratePerKm"
                               value="${currentRate}" required />
                    </div>
                    <button type="submit" class="btn btn-success">Update Rate</button>
                </form>
            </div>
        </div>

        <!-- Registration forms for Customer and Driver -->
        <div class="row mt-4">
            <!-- Register Customer -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h3 class="card-title"><i class="fas fa-user-check"></i> Register Customer</h3>
                        <form action="AdminRegisterServlet" method="post">
                            <div class="form-group">
                                <label for="customerName">Name:</label>
                                <input type="text" class="form-control" id="customerName" name="customerName"
                                       required placeholder="Enter customer name" />
                            </div>
                            <div class="form-group">
                                <label for="customerAddress">Address:</label>
                                <input type="text" class="form-control" id="customerAddress" name="customerAddress"
                                       required placeholder="Enter customer address" />
                            </div>
                            <div class="form-group">
                                <label for="customerNIC">NIC:</label>
                                <input type="text" class="form-control" id="customerNIC" name="customerNIC"
                                       required placeholder="Enter customer NIC" />
                            </div>
                            <div class="form-group">
                                <label for="customerContact">Contact:</label>
                                <input type="text" class="form-control" id="customerContact" name="customerContact"
                                       required placeholder="Enter customer contact" />
                            </div>
                            <div class="form-group">
                                <label for="customerUsername">Username:</label>
                                <input type="text" class="form-control" id="customerUsername" name="customerUsername"
                                       required placeholder="Enter username" />
                            </div>
                            <div class="form-group">
                                <label for="customerPassword">Password:</label>
                                <input type="password" class="form-control" id="customerPassword" name="customerPassword"
                                       required placeholder="Enter password" />
                            </div>
                            <input type="hidden" name="role" value="customer" />
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-user-check"></i> Register Customer
                            </button>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Register Driver -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h3 class="card-title"><i class="fas fa-user-tie"></i> Register Driver</h3>
                        <form action="AdminRegisterServlet" method="post">
                            <div class="form-group">
                                <label for="driverName">Name:</label>
                                <input type="text" class="form-control" id="driverName" name="driverName"
                                       required placeholder="Enter driver name" />
                            </div>
                            <div class="form-group">
                                <label for="driverContact">Contact:</label>
                                <input type="text" class="form-control" id="driverContact" name="driverContact"
                                       required placeholder="Enter driver contact" />
                            </div>
                            <div class="form-group">
                                <label for="driverNIC">NIC:</label>
                                <input type="text" class="form-control" id="driverNIC" name="driverNIC"
                                       required placeholder="Enter driver NIC" />
                            </div>
                            <div class="form-group">
                                <label for="driverVehicle">Vehicle Number:</label>
                                <input type="text" class="form-control" id="driverVehicle" name="driverVehicle"
                                       required placeholder="Enter vehicle number" />
                            </div>
                            <div class="form-group">
                                <label for="driverUsername">Username:</label>
                                <input type="text" class="form-control" id="driverUsername" name="driverUsername"
                                       required placeholder="Enter username" />
                            </div>
                            <div class="form-group">
                                <label for="driverPassword">Password:</label>
                                <input type="password" class="form-control" id="driverPassword" name="driverPassword"
                                       required placeholder="Enter password" />
                            </div>
                            <input type="hidden" name="role" value="driver" />
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-user-tie"></i> Register Driver
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
