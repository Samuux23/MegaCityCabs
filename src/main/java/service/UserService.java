package service;

import dao.UserDAO;
import model.User;
import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    
    public boolean authenticate(String username, String password) throws Exception {
        try {
            User user = userDAO.findByUsername(username);
            return user != null && user.getPassword().equals(password);
        } catch (SQLException e) {
            throw new Exception("Authentication failed: " + e.getMessage(), e);
        }
    }
}

