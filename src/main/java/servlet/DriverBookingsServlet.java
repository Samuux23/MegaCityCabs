package servlet;

import model.Booking;
import model.DriverCredential;
import service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/DriverBookingsServlet")
public class DriverBookingsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(DriverBookingsServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Ensure the driver is logged in
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("driver") == null) {
                logger.warning("Unauthorized access: Driver not logged in.");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Driver not logged in.");
                return;
            }

            // Fetch the logged-in driver
            DriverCredential driver = (DriverCredential) session.getAttribute("driver");
            BookingService bookingService = new BookingService();

            // Get all bookings assigned to this driver
            List<Booking> bookings = bookingService.getBookingsForDriver(driver.getId());
            logger.info("Fetching bookings for driver ID: " + driver.getId());

            // If no bookings, show a placeholder row
            if (bookings == null || bookings.isEmpty()) {
                out.write("<tr><td colspan='8' class='text-center'>No bookings found.</td></tr>");
                return;
            }

            // Build the HTML table rows
            StringBuilder htmlTable = new StringBuilder();
            for (Booking booking : bookings) {
                htmlTable.append("<tr>");

                // Order Number
                htmlTable.append("<td>")
                         .append(booking.getOrderNumber())
                         .append("</td>");

                // Customer Name
                htmlTable.append("<td>")
                         .append(booking.getCustomer().getName())
                         .append("</td>");

                // Customer Contact
                htmlTable.append("<td>")
                         .append(booking.getCustomer().getContactNumber())
                         .append("</td>");

                // Destination
                htmlTable.append("<td>")
                         .append(booking.getDestination())
                         .append("</td>");

                // Distance
                htmlTable.append("<td>")
                         .append(booking.getDistance())
                         .append("</td>");

                // Vehicle Number (or N/A if not set)
                htmlTable.append("<td>");
                if (booking.getVehicle() != null) {
                    htmlTable.append(booking.getVehicle().getVehicleNumber());
                } else {
                    htmlTable.append("N/A");
                }
                htmlTable.append("</td>");

                // Display current booking status
                htmlTable.append("<td>")
                         .append(booking.getStatus())
                         .append("</td>");

                // Actions column
                htmlTable.append("<td>");

                // If status is Pending, show both Approve & Complete
                if ("Pending".equals(booking.getStatus())) {
                    // Approve button
                    htmlTable.append("<button class='btn btn-sm btn-primary' ")
                             .append("onclick='approveBooking(")
                             .append(booking.getOrderNumber())
                             .append(")'>Approve</button> ");

                    // Complete button
                    htmlTable.append("<button class='btn btn-sm btn-success' ")
                             .append("onclick='completeBooking(")
                             .append(booking.getOrderNumber())
                             .append(")'>Complete</button>");
                }
                // If status is Approved, show only Complete
                else if ("Approved".equals(booking.getStatus())) {
                    htmlTable.append("<button class='btn btn-sm btn-success' ")
                             .append("onclick='completeBooking(")
                             .append(booking.getOrderNumber())
                             .append(")'>Complete</button>");
                }
                // If status is COMPLETE (or anything else), just show "Completed"
                else {
                    htmlTable.append("Completed");
                }

                htmlTable.append("</td>");
                htmlTable.append("</tr>");
            }

            // Write all rows to the response
            out.write(htmlTable.toString());

        } catch (Exception e) {
            logger.severe("Failed to load bookings: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to load bookings.");
        }
    }
}
