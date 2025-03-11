package servlet;

import service.BookingService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/AssignDriverServlet")
public class AssignDriverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long bookingId = Long.parseLong(request.getParameter("bookingId"));
            Long driverId = Long.parseLong(request.getParameter("driverId"));
            Long vehicleId = Long.parseLong(request.getParameter("vehicleId"));

            BookingService bookingService = new BookingService();
            bookingService.assignDriverToBooking(bookingId, driverId, vehicleId);

            response.sendRedirect("viewBookings.jsp?success=DriverAssigned");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("viewBookings.jsp?error=" + e.getMessage());
        }
    }
}

