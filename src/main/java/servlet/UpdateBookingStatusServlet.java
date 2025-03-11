
package servlet;

import dao.BookingDAO;
import model.Booking;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import org.json.JSONObject;

@WebServlet("/UpdateBookingStatusServlet")
public class UpdateBookingStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        try {
            String bookingIdParam = request.getParameter("bookingId");
            String newStatus = request.getParameter("status");
            if (bookingIdParam == null || bookingIdParam.trim().isEmpty() || newStatus == null) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Missing booking ID or status.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }
            Long bookingId = Long.parseLong(bookingIdParam);
            BookingDAO bookingDAO = new BookingDAO();
            Booking booking = bookingDAO.find(bookingId);
            if (booking == null) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Booking not found.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }
            booking.setStatus(newStatus);
            bookingDAO.update(booking);
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Booking status updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Failed to update booking status: " + e.getMessage());
        }
        response.getWriter().write(jsonResponse.toString());
    }
}
