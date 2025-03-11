<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Mega City Cabs</title>
    <style>
        /* Reset margins and padding */
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
        }

        /* Set the background image */
        body {
            background: url("Cover.jpg") no-repeat center center;
            background-size: cover;
            font-family: Arial, sans-serif;
        }

        /* Container for the buttons */
        .header-buttons {
            position: absolute;
            top: 20px;
            right: 20px;
        }

        /* Enhanced Button Styles */
        .header-buttons a {
            display: inline-block;
            text-decoration: none;
            color: #222; /* Even darker text */
            background: linear-gradient(to bottom, #ffdd57, #ffbb33); /* Lighter, brighter gradient */
            padding: 12px 24px;
            margin-left: 10px;
            border-radius: 8px;
            font-weight: bold;
            border: none; /* Remove the border for a cleaner look */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3); /* Stronger shadow */
            transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out, background 0.2s ease-in-out; /* Smooth transitions */
            cursor: pointer;
        }

        .header-buttons a:hover {
            transform: translateY(-2px);  /* Slight upward movement */
            box-shadow: 0 6px 10px rgba(0, 0, 0, 0.5); /* Increased shadow on hover */
            background: linear-gradient(to bottom, #ffcc00, #ff9900); /* Even brighter hover gradient */
        }

        /* Alternative: Inverted style */
        /*
        .header-buttons a {
           display: inline-block;
            text-decoration: none;
            color: #ffbb33; /* Same color as the button accent */
            background-color: transparent;
            padding: 12px 24px;
            margin-left: 10px;
            border-radius: 8px;
            font-weight: bold;
            border: 2px solid #ffbb33;
            box-shadow: none;
            transition: background-color 0.3s ease, color 0.3s ease;
            cursor: pointer;
        }

        .header-buttons a:hover {
            background-color: #ffbb33; /* Fill the button */
            color: #222; /* Darken the text */
        }
        */
    </style>
</head>
<body>
    <div class="header-buttons">
        <!-- Link to login.jsp -->
        <a href="login.jsp">Log In</a>
        <!-- Link to signup.jsp -->
        <a href="signup.jsp">Sign Up</a>
    </div>
</body>
</html>