package dao;

import model.Vehicle;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DriverCredential;

public class VehicleDAO {

    private static final Logger logger = Logger.getLogger(VehicleDAO.class.getName()); 

    public void create(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO vehicles (driver_id, vehicleNumber, vehicleType, status) VALUES (?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, vehicle.getDriver().getId());
            ps.setString(2, vehicle.getVehicleNumber());
            ps.setString(3, vehicle.getVehicleType());
            ps.setBoolean(4, vehicle.getStatus());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    vehicle.setId(rs.getLong(1));
                }
            }
        }
    }
    
    public void update(Vehicle vehicle) throws SQLException {
        String sql = "UPDATE vehicles SET driver_id=?, vehicleNumber=?, vehicleType=?, status=? WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, vehicle.getDriver().getId());
            ps.setString(2, vehicle.getVehicleNumber());
            ps.setString(3, vehicle.getVehicleType());
            ps.setBoolean(4, vehicle.getStatus());
            ps.setLong(5, vehicle.getId());
            ps.executeUpdate();
        }
    }
    
    public void delete(Vehicle vehicle) throws SQLException {
        String sql = "DELETE FROM vehicles WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, vehicle.getId());
            ps.executeUpdate();
        }
    }
    
    public Vehicle find(Long id) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapRowToVehicle(rs);
                }
            }
        }
        return null;
    }
    
    public List<Vehicle> findVehiclesByDriver(Long driverId) throws SQLException {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE driver_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, driverId);
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    list.add(mapRowToVehicle(rs));
                }
            }
        }
        return list;
    }
    
  public List<Vehicle> findAvailableVehicles(String vehicleType) throws SQLException {
    logger.info("Entering findAvailableVehicles with vehicleType: " + vehicleType);
    if (vehicleType == null || vehicleType.trim().isEmpty()) {
        logger.warning("Vehicle type is null or empty. Returning empty list.");
        return Collections.emptyList();
    }

    List<Vehicle> list = new ArrayList<>();
    String sql = "SELECT * FROM vehicles WHERE vehicleType = ? AND status = true";

    try (Connection con = DBUtil.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, vehicleType);
        logger.info("Executing query: " + sql + " with vehicleType: " + vehicleType);  
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vehicle vehicle = mapRowToVehicle(rs);
                list.add(vehicle);
                logger.info("Found available vehicle: " + vehicle.getVehicleNumber());   
            }
        }
        logger.info("Number of available vehicles found: " + list.size());   
    } catch (SQLException e) {
        logger.severe("SQL Exception in findAvailableVehicles: " + e.getMessage());   
        throw e; 
    }
    logger.info("Exiting findAvailableVehicles, found " + list.size() + " vehicles.");
    return list;
}
    
    private Vehicle mapRowToVehicle(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(rs.getLong("id"));
        DriverCredential driver = new DriverCredential();
        driver.setId(rs.getLong("driver_id"));
        vehicle.setDriver(driver);
        vehicle.setVehicleNumber(rs.getString("vehicleNumber"));
        vehicle.setVehicleType(rs.getString("vehicleType"));
        vehicle.setStatus(rs.getBoolean("status"));
        return vehicle;
    }
}


