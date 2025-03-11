package dao;

import model.SystemSetting;
import util.DBUtil;
import java.sql.*;

public class SystemSettingDAO {

    // Create a new system setting record
    public void create(SystemSetting setting) throws SQLException {
        // Using rate_per_km and updated_at to match DB column names
        String sql = "INSERT INTO system_settings (rate_per_km, updated_at) VALUES (?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, setting.getRatePerKm());
            ps.setTimestamp(2, new Timestamp(setting.getUpdatedAt().getTime()));
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating system setting failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    setting.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating system setting failed, no ID obtained.");
                }
            }
        }
    }

    // Find a system setting by its ID
    public SystemSetting find(Integer id) throws SQLException {
        SystemSetting setting = null;
        String sql = "SELECT id, rate_per_km, updated_at FROM system_settings WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    setting = new SystemSetting();
                    setting.setId(rs.getInt("id"));
                    // Use the underscored column names
                    setting.setRatePerKm(rs.getDouble("rate_per_km"));
                    setting.setUpdatedAt(rs.getTimestamp("updated_at"));
                }
            }
        }
        return setting;
    }

    // Update an existing system setting
    public void update(SystemSetting setting) throws SQLException {
        // Again, use rate_per_km and updated_at
        String sql = "UPDATE system_settings SET rate_per_km = ?, updated_at = ? WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, setting.getRatePerKm());
            ps.setTimestamp(2, new Timestamp(setting.getUpdatedAt().getTime()));
            ps.setInt(3, setting.getId());
            ps.executeUpdate();
        }
    }
}
