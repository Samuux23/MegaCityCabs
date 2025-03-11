package servlet;

import service.BookingService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;

@WebServlet("/ApproveBookingServlet")
public class ApproveBookingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();
        try {
            // Parse booking ID from request
            Long bookingId = Long.parseLong(request.getParameter("bookingId"));

            // Use your existing BookingService
            BookingService bookingService = new BookingService();
            // Set status to "Approved"
            bookingService.updateBookingStatus(bookingId, "Approved");

            jsonResponse.put("success", true);
            jsonResponse.put("message", "Booking approved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Failed to approve booking: " + e.getMessage());
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse.toString());
        }
    }
}
