package service;

import dao.DriverDAO;
import model.DriverCredential;
import java.sql.SQLException;

public class DriverService {
    
    private DriverDAO driverDAO = new DriverDAO();
    
    public boolean authenticate(String username, String password) throws Exception {
        try {
            DriverCredential driver = driverDAO.findByUsername(username);
            return driver != null && driver.getPassword().equals(password);
        } catch (SQLException e) {
            throw new Exception("Error during driver authentication", e);
        }
    }
    
    public void signUpDriver(String name, String contact, String nic, String username, String password)
            throws Exception {
        try {
            DriverCredential driver = new DriverCredential(name, contact, nic, username, password);
            driverDAO.create(driver);
        } catch (SQLException e) {
            throw new Exception("Error signing up driver", e);
        }
    }
    
    public DriverCredential getDriverByUsername(String username) throws Exception {
        try {
            return driverDAO.findByUsername(username);
        } catch (SQLException e) {
            throw new Exception("Error fetching driver by username", e);
        }
    }
}
