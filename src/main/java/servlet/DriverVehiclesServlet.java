package servlet;

import dao.VehicleDAO;
import dao.BookingDAO;
import model.Vehicle;
import model.DriverCredential;
import model.Booking;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/DriverVehiclesServlet")
public class DriverVehiclesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(DriverVehiclesServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("driver") == null) {
            logger.warning("Unauthorized access: No driver found in session.");
            response.sendRedirect("login.jsp");
            return;
        }
        DriverCredential driver = (DriverCredential) session.getAttribute("driver");
        if (driver == null || driver.getId() == null) {
            logger.severe("Session issue: Driver ID is null.");
            response.sendRedirect("error.jsp?message=Invalid driver session.");
            return;
        }
        logger.info("DEBUG: Retrieved driver from session: ID = " + driver.getId() 
                + ", Username = " + driver.getUsername() + ", Name = " + driver.getName());
        try {
            VehicleDAO vehicleDAO = new VehicleDAO();
            List<Vehicle> vehicles = vehicleDAO.findVehiclesByDriver(driver.getId());
            request.setAttribute("vehicles", vehicles);
            BookingDAO bookingDAO = new BookingDAO();
            List<Booking> driverBookings = bookingDAO.findBookingsByDriver(driver.getId());
            request.setAttribute("bookings", driverBookings);
            request.getRequestDispatcher("driverDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch vehicles or bookings.", e);
            response.sendRedirect("error.jsp?message=Failed to load driver data.");
        }
    }
}


