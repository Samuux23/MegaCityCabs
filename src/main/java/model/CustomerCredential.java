package model;

import com.google.gson.annotations.Expose;

public class CustomerCredential {
    @Expose private Long id;
    @Expose private String name;
    @Expose private String address;
    @Expose private String nic;
    @Expose private String contactNumber;
    @Expose private String username;
    @Expose private String password;

    public CustomerCredential() {}

    public CustomerCredential(String name, String address, String nic, String contactNumber,
                              String username, String password) {
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.contactNumber = contactNumber;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getNic() {
        return nic;
    }
    public void setNic(String nic) {
        this.nic = nic;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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
}
