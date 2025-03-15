<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard Guidelines</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; 
        }
        .card {
            border: none; 
            box-shadow: 0 0.25rem 0.5rem rgba(0, 0, 0, 0.05); 
        }
        .list-group-item {
            border: none; 
        }
        .gradient-header {
            background: linear-gradient(135deg, #FF9800, #FFEB3B); 
            color: white; 
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header gradient-header">
                <h2 class="mb-0">Customer Dashboard Guidelines</h2>
            </div>
            <div class="card-body">
                <p class="lead">Welcome to your MegaCity Cab customer dashboard! This guide will help you navigate and make the most of our services.</p>

                <h3 class="mt-4">Making a Booking:</h3>
                <ol class="list-group list-group-numbered">
                    <li class="list-group-item"><strong>Date:</strong> Select a date from the calendar for your desired pickup. Please choose a date in the future.</li>
                    <li class="list-group-item">
                        <strong>Vehicle Type:</strong> Choose a vehicle type from the dropdown.  Available options include:
                        <ul class="list-unstyled">
                            <li><i class="fas fa-car"></i> <strong>Sedan:</strong> Ideal for solo travelers or small groups (up to 4 passengers).</li>
                            <li><i class="fas fa-truck"></i> <strong>SUV:</strong> Suitable for families or groups with more luggage.</li>
                            <li><i class="fas fa-bus"></i> <strong>Van:</strong> Best for large groups or those requiring significant cargo space.</li>
                        </ul>
                    </li>
                    <li class="list-group-item"><strong>Destination:</strong> Enter your destination. Be as specific as possible.</li>
                    <li class="list-group-item"><strong>Distance (KM):</strong> Enter the estimated distance of your trip. You can use online mapping tools to get an approximation.</li>
                    <li class="list-group-item"><strong>Book Ride:</strong> Click the <strong>Book Ride</strong> button to submit your booking.</li>
                </ol>

                <h3 class="mt-4">Managing Your Bookings:</h3>
                <ul class="list-group">
                    <li class="list-group-item"><strong>Reservations Table:</strong> View your upcoming and past reservations. Details include Booking ID, Date, Destination, Distance, Driver, Vehicle, Price, and Actions.</li>
                    <li class="list-group-item"><strong>Cancel Booking:</strong> To cancel, click the <strong>Cancel</strong> button next to a booking in the Reservations Table.  Note that cancellation fees may apply.</li>
                    <li class="list-group-item"><strong>Print Bill:</strong> After your ride, click the <strong>Print Bill</strong> button to view and print your invoice.</li>
                </ul>

                <h3 class="mt-4">Need Help in the Cost Guide? Contact Us!</h3>
                <ul class="list-group">
                    <li class="list-group-item">
                        <strong>Phone:</strong> +1-555-MEGACAB (555-6342)
                    </li>
                    <li class="list-group-item">
                        <strong>Email:</strong> <a href="mailto:support@megacitycab.com">support@megacitycab.com</a>
                    </li>
<!--                     <li class="list-group-item"> -->
<!--                         <strong>Live Chat:</strong> Visit our website and click the chat icon in the corner. -->
<!--                     </li> -->
<!--                     <li class="list-group-item"> -->
<!--                         <strong>Social Media:</strong> Find us on Facebook, Twitter, and Instagram @MegaCityCab. -->
<!--                     </li> -->
<!--                     <li class="list-group-item"> -->
<!--                        For a detailed breakdown of pricing factors (base fare, distance rates, surge pricing, etc.), please consult our pricing guide on the website. You can find there a list of rates, that can be change based on the surge price. -->
<!--                     </li> -->
                </ul>

                <a href="customerDashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
            </div>
        </div>
    </div>

    <script src="https://kit.fontawesome.com/your-font-awesome-kit.js" crossorigin="anonymous"></script>
</body>
</html>