package model;

public class Vehicle {
    private Long id;
    private DriverCredential driver;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleImage; // Could be a URL or a path
    private Boolean status = true;

    public Vehicle() {}

    public Vehicle(DriverCredential driver, String vehicleNumber, String vehicleType, String vehicleImage) {
        this.driver = driver;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.vehicleImage = vehicleImage;
        this.status = true;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public DriverCredential getDriver() {
        return driver;
    }
    public void setDriver(DriverCredential driver) {
        this.driver = driver;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getVehicleImage() {
        return vehicleImage;
    }
    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
}

