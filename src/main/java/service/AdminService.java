package service;

import dao.AdminDAO;
import model.AdminCredential;
import java.sql.SQLException;

public class AdminService {
    
    private AdminDAO adminDAO = new AdminDAO();
    
    public boolean authenticate(String username, String password) throws Exception {
        try {
            AdminCredential admin = adminDAO.findByUsername(username);
            return admin != null && admin.getPassword().equals(password);
        } catch (SQLException e) {
            throw new Exception("Error during admin authentication", e);
        }
    }
    
    public void signUpAdmin(String username, String password) throws Exception {
        try {
            AdminCredential admin = new AdminCredential(username, password);
            adminDAO.create(admin);
        } catch (SQLException e) {
            throw new Exception("Error signing up admin", e);
        }
    }
}


