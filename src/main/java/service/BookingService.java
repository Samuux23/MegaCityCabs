package service;

import dao.BookingDAO;
import dao.DriverDAO;
import dao.VehicleDAO;
import model.Booking;
import model.DriverCredential;
import model.Vehicle;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class BookingService {
    private static final Logger logger = Logger.getLogger(BookingService.class.getName());
    private final BookingDAO bookingDAO = new BookingDAO();
    private final DriverDAO driverDAO = new DriverDAO();
    private final VehicleDAO vehicleDAO = new VehicleDAO();

    // Fetch all bookings
    public List<Booking> getAllBookings() throws Exception {
        try {
            List<Booking> bookings = bookingDAO.findAll();
            logger.info("Fetched " + bookings.size() + " bookings.");
            return bookings;
        } catch (SQLException e) {
            throw new Exception("Error fetching bookings", e);
        }
    }

    // Add a new booking
    public void addBooking(Booking booking) throws Exception {
        if (booking == null) {
            throw new Exception("Cannot add a null booking.");
        }
        try {
            bookingDAO.create(booking);
            logger.info("Booking successfully added: " + booking);
        } catch (SQLException e) {
            throw new Exception("Error adding booking", e);
        }
    }

    // Update an existing booking
    public void updateBooking(Booking booking) throws Exception {
        if (booking == null || booking.getOrderNumber() == null) {
            throw new Exception("Invalid booking update request.");
        }
        try {
            bookingDAO.update(booking);
            logger.info("Booking successfully updated: " + booking);
        } catch (SQLException e) {
            throw new Exception("Error updating booking", e);
        }
    }

    // Delete a booking
    public void deleteBooking(Booking booking) throws Exception {
        if (booking == null || booking.getOrderNumber() == null) {
            throw new Exception("Invalid booking deletion request.");
        }
        try {
            bookingDAO.delete(booking);
            logger.info("Booking successfully deleted: " + booking);
        } catch (SQLException e) {
            throw new Exception("Error deleting booking", e);
        }
    }

    // Apply discount to a booking
    // Now recalculates the price = (distance * ratePerKm) - discount
    public void applyDiscount(Long bookingId, double discount) throws Exception {
        if (bookingId == null) {
            throw new Exception("Booking ID cannot be null.");
        }
        try {
            Booking booking = bookingDAO.find(bookingId);
            if (booking != null) {
                // Set the new discount
                booking.setDiscount(discount);

                // Re-fetch current rate from system settings
                SystemSettingService settingService = new SystemSettingService();
                double ratePerKm = settingService.getCurrentRatePerKm();

                // Calculate new price
                double newPrice = booking.getDistance() * ratePerKm - discount;
                if (newPrice < 0) {
                    newPrice = 0; // Safety check if discount exceeds total cost
                }
                booking.setPrice(newPrice);

                // Update the booking record in DB
                bookingDAO.update(booking);
                logger.info("Discount of " + discount + " applied to booking ID: " + bookingId);
            } else {
                logger.warning("Booking not found for discount application: " + bookingId);
            }
        } catch (SQLException e) {
            throw new Exception("Error applying discount", e);
        }
    }

    // Fetch bookings for a specific customer
    public List<Booking> getBookingsForCustomer(Long customerId) throws Exception {
        if (customerId == null) {
            throw new Exception("Customer ID cannot be null.");
        }
        try {
            List<Booking> customerBookings = bookingDAO.findBookingsByCustomer(customerId);
            if (customerBookings.isEmpty()) {
                logger.info("No bookings found for customer ID: " + customerId);
                return List.of();
            }
            logger.info("Fetched " + customerBookings.size() + " bookings for customer ID: " + customerId);
            return customerBookings;
        } catch (SQLException e) {
            throw new Exception("Error fetching customer bookings", e);
        }
    }

    // Cancel a booking
    public void cancelBooking(Long bookingId) throws Exception {
        if (bookingId == null) {
            throw new Exception("Booking ID cannot be null.");
        }
        try {
            Booking booking = bookingDAO.find(bookingId);
            if (booking != null) {
                bookingDAO.delete(booking);
                logger.info("Booking cancelled: " + bookingId);
            } else {
                logger.warning("Attempted to cancel a non-existing booking: " + bookingId);
            }
        } catch (SQLException e) {
            throw new Exception("Error cancelling booking", e);
        }
    }

    // Update booking status
    public void updateBookingStatus(Long bookingId, String status) throws Exception {
        if (bookingId == null || status == null || status.trim().isEmpty()) {
            throw new Exception("Invalid booking ID or status.");
        }
        try {
            Booking booking = bookingDAO.find(bookingId);
            if (booking != null) {
                booking.setStatus(status);
                bookingDAO.update(booking);
                logger.info("Booking status updated to '" + status + "' for booking ID: " + bookingId);
            } else {
                logger.warning("Attempted to update status for non-existing booking: " + bookingId);
            }
        } catch (SQLException e) {
            throw new Exception("Error updating booking status", e);
        }
    }

    // Fetch bookings assigned to a specific driver
    public List<Booking> getBookingsForDriver(Long driverId) throws Exception {
        if (driverId == null) {
            throw new Exception("Driver ID cannot be null.");
        }
        try {
            List<Booking> driverBookings = bookingDAO.findBookingsByDriver(driverId);
            if (driverBookings.isEmpty()) {
                logger.info("No bookings found for driver ID: " + driverId);
                return List.of();
            }
            logger.info("Fetched " + driverBookings.size() + " bookings for driver ID: " + driverId);
            return driverBookings;
        } catch (SQLException e) {
            throw new Exception("Error fetching driver bookings", e);
        }
    }

    // Assign a driver and vehicle to a booking
    public void assignDriverToBooking(Long bookingId, Long driverId, Long vehicleId) throws Exception {
        if (bookingId == null || driverId == null || vehicleId == null) {
            throw new Exception("Booking ID, Driver ID, and Vehicle ID cannot be null.");
        }
        try {
            Booking booking = bookingDAO.find(bookingId);
            if (booking == null) {
                throw new Exception("Booking not found with ID: " + bookingId);
            }
            DriverCredential driver = driverDAO.findById(driverId);
            if (driver == null) {
                throw new Exception("Driver not found with ID: " + driverId);
            }
            Vehicle vehicle = vehicleDAO.find(vehicleId);
            if (vehicle == null) {
                throw new Exception("Vehicle not found with ID: " + vehicleId);
            }
            booking.setDriver(driver);
            booking.setVehicle(vehicle);
            bookingDAO.update(booking);
            logger.info("Driver (ID: " + driverId + ") and vehicle (ID: " + vehicleId 
                        + ") assigned to booking ID: " + bookingId);
        } catch (SQLException e) {
            throw new Exception("Error assigning driver and vehicle", e);
        }
    }

    // Complete a booking (only if status is Pending)
    public void completeBooking(Long bookingId) throws Exception {
        if (bookingId == null) {
            throw new Exception("Booking ID cannot be null.");
        }
        try {
            Booking booking = bookingDAO.find(bookingId);
            if (booking == null) {
                throw new Exception("Booking not found with ID: " + bookingId);
            }
            if (!"Pending".equals(booking.getStatus())) {
                throw new Exception("Booking cannot be completed as it is not in 'Pending' status.");
            }

            booking.setStatus("COMPLETE");
            bookingDAO.update(booking);

            // Re-enable the vehicle
            if (booking.getVehicle() != null) {
                Vehicle vehicle = booking.getVehicle();
                vehicle.setStatus(true);
                vehicleDAO.update(vehicle);
                logger.info("Vehicle (ID: " + vehicle.getId() + ") re-enabled after booking completion.");
            }

            logger.info("Booking (ID: " + bookingId + ") marked as COMPLETED.");
        } catch (SQLException e) {
            throw new Exception("Error completing booking: " + e.getMessage(), e);
        }
    }
}
