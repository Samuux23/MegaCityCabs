package dao;

import model.AdminCredential;
import util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {

    /**
     * Finds an AdminCredential by username.
     * @param username the admin username to search for.
     * @return the AdminCredential if found; otherwise, null.
     * @throws Exception if a database access error occurs.
     */
    public AdminCredential findByUsername(String username) throws Exception {
        AdminCredential admin = null;
        String sql = "SELECT * FROM adminCredentials WHERE username = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    admin = new AdminCredential();
                    admin.setId(rs.getLong("id"));  
                    admin.setUsername(rs.getString("username"));
                    admin.setPassword(rs.getString("password"));
                    
                }
            }
        }
        return admin;
    }

    /**
     * Creates a new AdminCredential record in the database.
     * @param admin the AdminCredential to be created.
     * @throws Exception if an error occurs during creation.
     */
    public void create(AdminCredential admin) throws Exception {
        String sql = "INSERT INTO adminCredentials (username, password) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Creating admin failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if (generatedKeys.next()){
                    admin.setId(generatedKeys.getLong(1));
                } else {
                    throw new Exception("Creating admin failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            throw new Exception("Error while creating AdminCredential", e);
        }
    }
}

