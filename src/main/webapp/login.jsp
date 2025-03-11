<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>Login</title>
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
            background-image: url('Cover.jpg'); 
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
            background: rgba(0, 0, 0, 0.5); 
            z-index: 1;
        }
        .login-container {
            background: rgba(255, 255, 255, 0.9); /* Semi-transparent white background */
            padding: 2.5rem;
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            width: 350px;
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
        .signup-link {
            margin-top: 1.5rem;
            font-size: 0.9rem;
            color: #555;
        }
        .signup-link a {
            color: #fbc02d;
            text-decoration: none;
            font-weight: 600;
            transition: color 0.3s ease;
        }
        .signup-link a:hover {
            color: #ff9800;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="LoginServlet" method="post">
            <div class="role-select">
                <label for="roleSelect">Select Role:</label>
                <select name="role" id="roleSelect">
                    <option value="admin">Admin</option>
                    <option value="customer">Customer</option>
                    <option value="driver">Driver</option>
                </select>
            </div>
            <label for="username">Username:</label>
            <input type="text" name="username" id="username" required />
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required />
            <input type="submit" value="Login" />
        </form>
        <div class="signup-link">
            Don't have an account? <a href="signup.jsp">Sign Up</a>
        </div>
    </div>
</body>
</html>