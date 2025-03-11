package service;

import dao.VehicleDAO;
import model.Vehicle;
import model.DriverCredential;
import java.sql.SQLException;

public class VehicleService {
    private VehicleDAO vehicleDAO = new VehicleDAO();

    public void addVehicle(DriverCredential driver, String vehicleNumber, String vehicleType, String vehicleImage) throws Exception {
        try {
            System.out.println("DEBUG: Attempting to add vehicle for driver ID: " + driver.getId());
            System.out.println("DEBUG: Vehicle Details -> Number: " + vehicleNumber + ", Type: " + vehicleType + ", Image: " + vehicleImage);
            Vehicle vehicle = new Vehicle(driver, vehicleNumber, vehicleType, vehicleImage);
            vehicleDAO.create(vehicle);
            System.out.println("DEBUG: Vehicle successfully added with ID: " + vehicle.getId());
        } catch (SQLException e) {
            throw new Exception("Error adding vehicle", e);
        }
    }
}
