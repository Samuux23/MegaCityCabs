package model;

import java.util.Date;

public class SystemSetting {
    private Integer id;
    private Double ratePerKm;
    private Date updatedAt = new Date();

    public SystemSetting() {}

    public SystemSetting(Double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Double getRatePerKm() {
        return ratePerKm;
    }
    public void setRatePerKm(Double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

