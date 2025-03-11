package servlet;

import service.BookingService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UpdateDiscountServlet")
public class UpdateDiscountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long bookingId = Long.parseLong(request.getParameter("bookingId"));
            double discount = Double.parseDouble(request.getParameter("discount"));

            BookingService bookingService = new BookingService();
            bookingService.applyDiscount(bookingId, discount);

            // Redirect back to viewBookings.jsp with a success message
            response.sendRedirect("ViewBookingsServlet?success=DiscountUpdated");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ViewBookingsServlet?error=" + e.getMessage());
        }
    }
}
