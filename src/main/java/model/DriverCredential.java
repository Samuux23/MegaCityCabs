package model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class DriverCredential {
    @Expose private Long id;
    @Expose private String name;
    @Expose private String contactNumber;
    @Expose private String nic;
    @Expose private String username;
    @Expose private String password;
    // In plain JDBC, you can keep the list if needed—but you must manage loading manually.
    @Expose private List<Vehicle> vehicles;

    public DriverCredential() {}

    public DriverCredential(String name, String contactNumber, String nic, String username, String password) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.nic = nic;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getNic() {
        return nic;
    }
    public void setNic(String nic) {
        this.nic = nic;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
