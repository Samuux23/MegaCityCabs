package servlet;

import model.Booking;
import service.BookingService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteBookingServlet")
public class DeleteBookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        String bookingIdParam = request.getParameter("bookingId");
        if (bookingIdParam == null || bookingIdParam.trim().isEmpty()) {
            // No bookingId provided, redirect with error
            response.sendRedirect("ViewBookingsServlet?error=NoBookingIdProvided");
            return;
        }

        try {
            long bookingId = Long.parseLong(bookingIdParam);
            BookingService bookingService = new BookingService();

            // Construct a Booking object or fetch it from DB if needed
            Booking bookingToDelete = new Booking();
            bookingToDelete.setOrderNumber(bookingId);

            // Use your existing service method to delete
            bookingService.deleteBooking(bookingToDelete);

            // Redirect to the bookings page with a success message
            response.sendRedirect("ViewBookingsServlet?success=BookingDeleted");
        } catch (Exception e) {
            e.printStackTrace();
            // On failure, redirect with error
            response.sendRedirect("ViewBookingsServlet?error=" + e.getMessage());
        }
    }
}
