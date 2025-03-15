package dao; // Or any appropriate package

import util.DBUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestDBConnection {
    private static final Logger logger = Logger.getLogger(TestDBConnection.class.getName());

    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection()) {
            logger.info("Database connection successful!");
            
            try (PreparedStatement stmt = conn.prepareStatement("SELECT 1")) {
                stmt.executeQuery();
                logger.info("Simple query executed successfully.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection failed: " + e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
