package servlet;

import model.Booking;
import model.CustomerCredential;
import model.Vehicle;
import model.DriverCredential;
import service.BookingService;
import service.SystemSettingService;
import service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(BookingServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();
        try (PrintWriter out = response.getWriter()) {

            // 1) Retrieve the bookingDate from the request
            String bookingDateStr = request.getParameter("bookingDate");
            if (bookingDateStr != null) {
                bookingDateStr = URLDecoder.decode(bookingDateStr, StandardCharsets.UTF_8.name());
            }

            // 2) Decode other request parameters
            String destination = request.getParameter("destination");
            String distanceStr = request.getParameter("distance");
            String discountStr = request.getParameter("discount");
            String vehicleType = request.getParameter("vehicleType");

            if (destination != null) {
                destination = URLDecoder.decode(destination, StandardCharsets.UTF_8.name());
            }
            if (distanceStr != null) {
                distanceStr = URLDecoder.decode(distanceStr, StandardCharsets.UTF_8.name());
            }
            if (discountStr != null) {
                discountStr = URLDecoder.decode(discountStr, StandardCharsets.UTF_8.name());
            }
            if (vehicleType != null) {
                vehicleType = URLDecoder.decode(vehicleType, StandardCharsets.UTF_8.name());
            }

            logger.info("Booking request received:"
                    + " Date: " + bookingDateStr
                    + ", Destination: " + destination
                    + ", Distance: " + distanceStr
                    + ", Discount: " + discountStr
                    + ", Vehicle Type: " + vehicleType);

            // 3) Validate required fields
            if (bookingDateStr == null || bookingDateStr.trim().isEmpty() ||
                destination == null || destination.trim().isEmpty() ||
                vehicleType == null || vehicleType.trim().isEmpty()) {

                jsonResponse.put("success", false);
                jsonResponse.put("message", "Booking date, destination, and vehicle type are required.");
                out.print(jsonResponse.toString());
                return;
            }

            // 4) Parse numeric values for distance and discount
            Double distance = 0.0, discount = 0.0;
            try {
                distance = (distanceStr != null && !distanceStr.isEmpty()) ? Double.parseDouble(distanceStr) : 0.0;
                discount = (discountStr != null && !discountStr.isEmpty()) ? Double.parseDouble(discountStr) : 0.0;
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, "Invalid number format for distance or discount.", e);
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Invalid number format for distance or discount.");
                out.print(jsonResponse.toString());
                return;
            }

            // 5) Parse the user-chosen date, then combine with current time
            Date bookingDateTime;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOnly = sdf.parse(bookingDateStr);

                Calendar now = Calendar.getInstance(); // current time
                Calendar dateCal = Calendar.getInstance();
                dateCal.setTime(dateOnly);

                // Merge date from user with current hours/minutes/seconds
                now.set(Calendar.YEAR, dateCal.get(Calendar.YEAR));
                now.set(Calendar.MONTH, dateCal.get(Calendar.MONTH));
                now.set(Calendar.DAY_OF_MONTH, dateCal.get(Calendar.DAY_OF_MONTH));

                bookingDateTime = now.getTime();
            } catch (ParseException e) {
                logger.warning("Failed to parse booking date. Using current date/time instead.");
                bookingDateTime = new Date(); // fallback
            }

            // 6) Verify customer is logged in
            HttpSession session = request.getSession();
            CustomerCredential customer = (CustomerCredential) session.getAttribute("customer");
            if (customer == null) {
                logger.warning("Customer not logged in.");
                jsonResponse.put("success", false);
                jsonResponse.put("message", "User not logged in.");
                out.print(jsonResponse.toString());
                return;
            }

            // 7) Find an available vehicle for the selected type
            VehicleService vehicleService = new VehicleService();
            List<Vehicle> availableVehicles = new dao.VehicleDAO().findAvailableVehicles(vehicleType);
            if (availableVehicles.isEmpty()) {
                logger.warning("No available vehicles of type: " + vehicleType);
                jsonResponse.put("success", false);
                jsonResponse.put("message", "No available vehicles for the selected type.");
                out.print(jsonResponse.toString());
                return;
            }

            // 8) Select the first available vehicle and its driver
            Vehicle assignedVehicle = availableVehicles.get(0);
            DriverCredential assignedDriver = assignedVehicle.getDriver();
            if (assignedDriver == null) {
                logger.warning("No assigned driver for vehicle: " + assignedVehicle.getVehicleNumber());
                jsonResponse.put("success", false);
                jsonResponse.put("message", "No available drivers for the selected vehicle.");
                out.print(jsonResponse.toString());
                return;
            }

            // 9) Retrieve current fixed rate per KM
            SystemSettingService settingService = new SystemSettingService();
            double ratePerKm = settingService.getCurrentRatePerKm();

            // 10) Calculate total price = (distance * ratePerKm) - discount
            Double price = (distance * ratePerKm) - discount;
            if (price < 0) price = 0.0;
            logger.info("Calculated Price: " + price);

            // 11) Create a new Booking
            Booking booking = new Booking(customer, destination.trim(), distance, price, discount);
            booking.setVehicle(assignedVehicle);
            booking.setDriver(assignedDriver);

            // Set the combined date + current time
            booking.setBookingDate(bookingDateTime);

            // 12) Save the booking using BookingService
            BookingService bookingService = new BookingService();
            bookingService.addBooking(booking);

            // 13) Mark the assigned vehicle as unavailable
            assignedVehicle.setStatus(false);
            new dao.VehicleDAO().update(assignedVehicle);

            logger.info("Booking successfully added with vehicle: " + assignedVehicle.getVehicleNumber());
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Booking successful. Total price is " + price + " LKR.");

            out.print(jsonResponse.toString());

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Booking creation failed.", e);
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Booking creation failed: " + e.getMessage());
            try (PrintWriter out = response.getWriter()) {
                out.print(jsonResponse.toString());
            }
        }
    }
}
