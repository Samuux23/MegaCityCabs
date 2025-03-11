package dao;

import model.DriverCredential;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {

    public DriverCredential findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM driverCredentials WHERE username = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return mapRowToDriver(rs);
                }
            }
        }
        return null;
    }
    
    public DriverCredential findById(Long id) throws SQLException {
        String sql = "SELECT * FROM driverCredentials WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return mapRowToDriver(rs);
                }
            }
        }
        return null;
    }
    
    public void create(DriverCredential driver) throws SQLException {
        String sql = "INSERT INTO driverCredentials (name, contactNumber, nic, username, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, driver.getName());
            ps.setString(2, driver.getContactNumber());
            ps.setString(3, driver.getNic());
            ps.setString(4, driver.getUsername());
            ps.setString(5, driver.getPassword());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    driver.setId(rs.getLong(1));
                }
            }
        }
    }
    
    public void update(DriverCredential driver) throws SQLException {
        String sql = "UPDATE driverCredentials SET name=?, contactNumber=?, nic=?, username=?, password=? WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, driver.getName());
            ps.setString(2, driver.getContactNumber());
            ps.setString(3, driver.getNic());
            ps.setString(4, driver.getUsername());
            ps.setString(5, driver.getPassword());
            ps.setLong(6, driver.getId());
            ps.executeUpdate();
        }
    }
    
    public void delete(DriverCredential driver) throws SQLException {
        String sql = "DELETE FROM driverCredentials WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, driver.getId());
            ps.executeUpdate();
        }
    }
    
    public List<DriverCredential> findAll() throws SQLException {
        List<DriverCredential> list = new ArrayList<>();
        String sql = "SELECT * FROM driverCredentials";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                list.add(mapRowToDriver(rs));
            }
        }
        return list;
    }
    
    public int getActiveDriversCount() throws SQLException {
        String sql = "SELECT COUNT(DISTINCT d.id) as count FROM driverCredentials d JOIN vehicles v ON d.id = v.driver_id WHERE v.status = true";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            if (rs.next()){
                return rs.getInt("count");
            }
        }
        return 0;
    }
    
    private DriverCredential mapRowToDriver(ResultSet rs) throws SQLException {
        DriverCredential driver = new DriverCredential();
        driver.setId(rs.getLong("id"));
        driver.setName(rs.getString("name"));
        driver.setContactNumber(rs.getString("contactNumber"));
        driver.setNic(rs.getString("nic"));
        driver.setUsername(rs.getString("username"));
        driver.setPassword(rs.getString("password"));
        return driver;
    }
}