package servlet;

import service.BookingService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;

@WebServlet("/CancelBookingServlet")
public class CancelBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();
        try {
            Long bookingId = Long.parseLong(request.getParameter("bookingId"));
            BookingService bookingService = new BookingService();
            bookingService.cancelBooking(bookingId);
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Booking cancelled successfully");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Failed to cancel booking: " + e.getMessage());
        }
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse.toString());
        }
    }
}

