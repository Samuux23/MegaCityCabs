package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Booking;
import model.CustomerCredential;
import service.BookingService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/CustomerBookingsServlet")
public class CustomerBookingsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(CustomerBookingsServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("customer") == null) {
                logger.warning("Unauthorized access: User not logged in.");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in.");
                return;
            }
            
            CustomerCredential customer = (CustomerCredential) session.getAttribute("customer");
            BookingService bookingService = new BookingService();
            List<Booking> bookings = bookingService.getBookingsForCustomer(customer.getId());
            logger.info("Fetching bookings for customer ID: " + customer.getId());
            
            if (bookings == null || bookings.isEmpty()) {
                logger.info("No bookings found for customer: " + customer.getId());
                out.write("[]");
                return;
            }
            
            // Build Gson instance to convert Booking objects to JSON.
            // We do not exclude fields without @Expose so that the "status" field is included.
            Gson gson = new GsonBuilder()
                    //.excludeFieldsWithoutExposeAnnotation() // Ensure this is commented out/removed
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .disableHtmlEscaping()
                    .setPrettyPrinting()
                    .create();
                    
            String jsonBookings = gson.toJson(bookings);
            logger.info("Generated JSON Response: " + jsonBookings);
            out.write(jsonBookings);
        } catch (Exception e) {
            logger.severe("Failed to load bookings: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to load bookings.");
        }
    }
}
