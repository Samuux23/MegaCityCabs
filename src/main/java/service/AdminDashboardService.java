package service;

import dao.BookingDAO;
import dao.CustomerDAO;
import dao.DriverDAO;
import model.Booking;
import java.sql.SQLException;
import java.util.List;

public class AdminDashboardService {
    private BookingDAO bookingDAO = new BookingDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private DriverDAO driverDAO = new DriverDAO();
    
    public List<Booking> getAllBookings() throws Exception {
        try {
            return bookingDAO.findAll();
        } catch (SQLException e) {
            throw new Exception("Error fetching all bookings", e);
        }
    }
    
    public int getActiveDriversCount() throws Exception {
        try {
            return driverDAO.getActiveDriversCount();
        } catch (SQLException e) {
            throw new Exception("Error fetching active drivers count", e);
        }
    }
    
    public int getRegisteredCustomersCount() throws Exception {
        try {
            return customerDAO.findAll().size();
        } catch (SQLException e) {
            throw new Exception("Error fetching registered customers", e);
        }
    }
}



