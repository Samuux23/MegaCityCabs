<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>Sign Up</title>
    <link href="https://fonts.googleapis.com/css2?family=Afacad:wght@400;500;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Afacad', sans-serif;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background-image: url('Cover.jpg'); /* Add your background image here */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            position: relative;
        }
        body::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: rgba(0, 0, 0, 0.5); /* Semi-transparent overlay */
            z-index: 1;
        }
        .signup-container {
            background: rgba(255, 255, 255, 0.9); /* Semi-transparent white background */
            padding: 2.5rem;
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            width: 400px;
            text-align: center;
            animation: fadeIn 0.5s ease-in-out;
            position: relative;
            z-index: 2;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        h2 {
            margin-bottom: 1.5rem;
            color: #333;
            font-size: 2rem;
            font-weight: 600;
        }
        label {
            display: block;
            margin-bottom: 0.5rem;
            color: #555;
            font-weight: 500;
            text-align: left;
        }
        select, input[type="text"], input[type="password"] {
            width: 100%;
            padding: 0.75rem;
            margin-bottom: 1rem;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 1rem;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }
        select:focus, input[type="text"]:focus, input[type="password"]:focus {
            border-color: #ffeb3b;
            box-shadow: 0 0 8px rgba(255, 235, 59, 0.5);
            outline: none;
        }
        input[type="submit"] {
            width: 100%;
            padding: 0.75rem;
            background: #ffeb3b;
            color: #333;
            border: none;
            border-radius: 8px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.3s ease, transform 0.2s ease;
        }
        input[type="submit"]:hover {
            background: #fdd835;
            transform: translateY(-2px);
        }
        input[type="submit"]:active {
            transform: translateY(0);
        }
        .role-select {
            margin-bottom: 1.5rem;
        }
        .role-select label {
            color: #333;
        }
        .role-select select {
            background: #fff;
            color: #333;
        }
        .login-link {
            margin-top: 1.5rem;
            font-size: 0.9rem;
            color: #555;
        }
        .login-link a {
            color: #fbc02d;
            text-decoration: none;
            font-weight: 600;
            transition: color 0.3s ease;
        }
        .login-link a:hover {
            color: #ff9800;
        }
        .form-section {
            display: none;
            margin-top: 1.5rem;
            animation: slideIn 0.5s ease-in-out;
        }
        @keyframes slideIn {
            from { opacity: 0; transform: translateY(-10px); }
            to { opacity: 1; transform: translateY(0); }
        }
        .form-section h3 {
            margin-bottom: 1rem;
            color: #333;
            font-size: 1.25rem;
            font-weight: 600;
        }
        .form-section label {
            margin-top: 0.5rem;
        }
    </style>
    <script>
        function showForm() {
            var role = document.getElementById("roleSelect").value;
            
            // Get form sections
            var adminForm = document.getElementById("adminForm");
            var customerForm = document.getElementById("customerForm");
            var driverForm = document.getElementById("driverForm");
            
            // Get input fields in each section
            var adminInputs = adminForm.querySelectorAll("input");
            var customerInputs = customerForm.querySelectorAll("input");
            var driverInputs = driverForm.querySelectorAll("input");
            
            // Hide all sections and disable their inputs so they won't trigger HTML5 validation
            adminForm.style.display = "none";
            customerForm.style.display = "none";
            driverForm.style.display = "none";
            adminInputs.forEach(function(input) { input.disabled = true; });
            customerInputs.forEach(function(input) { input.disabled = true; });
            driverInputs.forEach(function(input) { input.disabled = true; });
            
            // Show the relevant section and enable its inputs
            if (role === "admin") {
                adminForm.style.display = "block";
                adminInputs.forEach(function(input) { input.disabled = false; });
            } else if (role === "customer") {
                customerForm.style.display = "block";
                customerInputs.forEach(function(input) { input.disabled = false; });
            } else if (role === "driver") {
                driverForm.style.display = "block";   
                driverInputs.forEach(function(input) { input.disabled = false; });
            }
        }
    </script>
</head>
<body onload="showForm();">
    <div class="signup-container">
        <h2>Sign Up</h2>
        <form action="SignUpServlet" method="post">
            <div class="role-select">
                <label for="roleSelect">Select Role:</label>
                <select name="role" id="roleSelect" onchange="showForm();">
                    <option value="admin">Admin</option>
                    <option value="customer">Customer</option>
                    <option value="driver">Driver</option>
                </select>
            </div>
            
            <!-- Admin Sign Up Section -->
            <div id="adminForm" class="form-section">
                <h3>Admin Sign Up</h3>
                <label for="adminUsername">Username:</label>
                <input type="text" name="adminUsername" id="adminUsername" required />
                <label for="adminPassword">Password:</label>
                <input type="password" name="adminPassword" id="adminPassword" required />
            </div>
            
            <!-- Customer Sign Up Section -->
            <div id="customerForm" class="form-section">
                <h3>Customer Sign Up</h3>
                <label for="customerName">Name:</label>
                <input type="text" name="customerName" id="customerName" required />
                <label for="customerAddress">Address:</label>
                <input type="text" name="customerAddress" id="customerAddress" required />
                <label for="customerNIC">NIC:</label>
                <input type="text" name="customerNIC" id="customerNIC" required />
                <label for="customerContact">Contact Number:</label>
                <input type="text" name="customerContact" id="customerContact" required />
                <label for="customerUsername">Username:</label>
                <input type="text" name="customerUsername" id="customerUsername" required />
                <label for="customerPassword">Password:</label>
                <input type="password" name="customerPassword" id="customerPassword" required />
            </div>
            
            <!-- Driver Sign Up Section -->
            <div id="driverForm" class="form-section">
                <h3>Driver Sign Up</h3>
                <label for="driverName">Name:</label>
                <input type="text" name="driverName" id="driverName" required />
                <label for="driverContact">Contact Number:</label>
                <input type="text" name="driverContact" id="driverContact" required />
                <label for="driverNIC">NIC:</label>
                <input type="text" name="driverNIC" id="driverNIC" required />
                <label for="driverUsername">Username:</label>
                <input type="text" name="driverUsername" id="driverUsername" required />
                <label for="driverPassword">Password:</label>
                <input type="password" name="driverPassword" id="driverPassword" required />
            </div>
            
            <input type="submit" value="Sign Up" />
        </form>
        <div class="login-link">
            Already have an account? <a href="login.jsp">Log In</a>
        </div>
    </div>
</body>
</html>