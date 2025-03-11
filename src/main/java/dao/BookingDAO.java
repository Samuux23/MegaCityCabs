package dao;

import model.Booking;
import model.CustomerCredential;
import model.DriverCredential;
import model.Vehicle;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public void create(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings (customer_id, driver_id, vehicle_id, bookingDate, destination, distance, price, discount, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, booking.getCustomer().getId());
            if (booking.getDriver() != null) {
                ps.setLong(2, booking.getDriver().getId());
            } else {
                ps.setNull(2, Types.BIGINT);
            }
            if (booking.getVehicle() != null) {
                ps.setLong(3, booking.getVehicle().getId());
            } else {
                ps.setNull(3, Types.BIGINT);
            }
            ps.setTimestamp(4, new Timestamp(booking.getBookingDate().getTime()));
            ps.setString(5, booking.getDestination());
            ps.setDouble(6, booking.getDistance());
            ps.setDouble(7, booking.getPrice());
            ps.setDouble(8, booking.getDiscount());
            ps.setString(9, booking.getStatus());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    booking.setOrderNumber(rs.getLong(1));
                }
            }
        }
    }
    
    public void update(Booking booking) throws SQLException {
        String sql = "UPDATE bookings SET customer_id=?, driver_id=?, vehicle_id=?, bookingDate=?, destination=?, distance=?, price=?, discount=?, status=? WHERE orderNumber=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, booking.getCustomer().getId());
            if (booking.getDriver() != null)
                ps.setLong(2, booking.getDriver().getId());
            else
                ps.setNull(2, Types.BIGINT);
            if (booking.getVehicle() != null)
                ps.setLong(3, booking.getVehicle().getId());
            else
                ps.setNull(3, Types.BIGINT);
            ps.setTimestamp(4, new Timestamp(booking.getBookingDate().getTime()));
            ps.setString(5, booking.getDestination());
            ps.setDouble(6, booking.getDistance());
            ps.setDouble(7, booking.getPrice());
            ps.setDouble(8, booking.getDiscount());
            ps.setString(9, booking.getStatus());
            ps.setLong(10, booking.getOrderNumber());
            ps.executeUpdate();
        }
    }
    
    public void delete(Booking booking) throws SQLException {
        String sql = "DELETE FROM bookings WHERE orderNumber=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, booking.getOrderNumber());
            ps.executeUpdate();
        }
    }
    
    public Booking find(Long orderNumber) throws SQLException {
        String sql = "SELECT * FROM bookings WHERE orderNumber = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, orderNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToBooking(rs);
                }
            }
        }
        return null;
    }
    
    public List<Booking> findBookingsByCustomer(Long customerId) throws SQLException {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE customer_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToBooking(rs));
                }
            }
        }
        return list;
    }
    
    public List<Booking> findBookingsByDriver(Long driverId) throws SQLException {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE driver_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, driverId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToBooking(rs));
                }
            }
        }
        return list;
    }
    
    public List<Booking> findAll() throws SQLException {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRowToBooking(rs));
            }
        }
        return list;
    }
    
    private Booking mapRowToBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setOrderNumber(rs.getLong("orderNumber"));
        
        // Retrieve Customer object using CustomerDAO
        CustomerDAO customerDAO = new CustomerDAO();
        booking.setCustomer(customerDAO.findById(rs.getLong("customer_id")));
        
        // Retrieve Driver object if driver_id is not 0
        long driverId = rs.getLong("driver_id");
        if (driverId != 0) {
            DriverDAO driverDAO = new DriverDAO();
            booking.setDriver(driverDAO.findById(driverId));
        }
        
        // Retrieve Vehicle object if vehicle_id is not 0
        long vehicleId = rs.getLong("vehicle_id");
        if (vehicleId != 0) {
            VehicleDAO vehicleDAO = new VehicleDAO();
            booking.setVehicle(vehicleDAO.find(vehicleId));
        }
        
        booking.setBookingDate(rs.getTimestamp("bookingDate"));
        booking.setDestination(rs.getString("destination"));
        booking.setDistance(rs.getDouble("distance"));
        booking.setPrice(rs.getDouble("price"));
        booking.setDiscount(rs.getDouble("discount"));
        booking.setStatus(rs.getString("status"));
        return booking;
    }
}
