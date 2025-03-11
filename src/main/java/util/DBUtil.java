package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/mega_city_cab?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "samundasql";

    private static final Logger logger = Logger.getLogger(DBUtil.class.getName());

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("MySQL driver loaded successfully.");
        } catch(ClassNotFoundException e) {
            logger.log(Level.SEVERE, "MySQL driver class not found: " + e.getMessage(), e);
            throw new ExceptionInInitializerError(e); // Fatal error
        }
    }
    
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Database connection established successfully.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection failed: " + e.getMessage(), e);
            throw e; // Re-throw the exception to be handled by the caller
        }
        return conn;
    }
}
