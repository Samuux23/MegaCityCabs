package servlet;

import service.BookingService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;

@WebServlet("/CompleteBookingServlet")
public class CompleteBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();
        try {
            Long bookingId = Long.parseLong(request.getParameter("bookingId"));
            BookingService bookingService = new BookingService();
            bookingService.completeBooking(bookingId);
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Booking marked as COMPLETE and vehicle re-enabled.");
        } catch (Exception e) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Failed to complete booking: " + e.getMessage());
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse.toString());
        }
    }
}

