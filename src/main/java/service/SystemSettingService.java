package service;

import dao.SystemSettingDAO;
import model.SystemSetting;
import java.sql.SQLException;

public class SystemSettingService {
    private SystemSettingDAO settingDAO = new SystemSettingDAO();
    
    public double getCurrentRatePerKm() throws Exception {
        try {
            SystemSetting setting = settingDAO.find(1);
            return (setting != null) ? setting.getRatePerKm() : 0.0;
        } catch (SQLException e) {
            throw new Exception("Error fetching current rate per km", e);
        }
    }
    
    public void updateRatePerKm(double rate) throws Exception {
        try {
            SystemSetting setting = settingDAO.find(1);
            if (setting == null) {
                // Create a new record if not found
                setting = new SystemSetting();
                setting.setRatePerKm(rate);
                // Optionally set the updated time:
                setting.setUpdatedAt(new java.util.Date());
                settingDAO.create(setting);
            } else {
                // Otherwise update the existing record
                setting.setRatePerKm(rate);
                // Optionally set the updated time:
                setting.setUpdatedAt(new java.util.Date());
                settingDAO.update(setting);
            }
        } catch (SQLException e) {
            throw new Exception("Error updating rate per km", e);
        }
    }

}
