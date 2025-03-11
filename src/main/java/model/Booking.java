package model;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class Booking {
    @Expose private Long orderNumber;
    @Expose private CustomerCredential customer;
    @Expose private DriverCredential driver;
    @Expose private Vehicle vehicle;
    @Expose private Date bookingDate = new Date();
    @Expose private String destination;
    @Expose private Double distance;
    @Expose private Double price;
    @Expose private Double discount = 0.0;
    @Expose private String status = "Pending";

    public Booking() {}

    public Booking(CustomerCredential customer, String destination, Double distance, Double price, Double discount) {
        this.customer = customer;
        this.destination = destination;
        this.distance = distance;
        this.price = price;
        this.discount = discount;
        this.status = "Pending";
    }

    // Getters and setters
    public Long getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }
    public CustomerCredential getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerCredential customer) {
        this.customer = customer;
    }
    public DriverCredential getDriver() {
        return driver;
    }
    public void setDriver(DriverCredential driver) {
        this.driver = driver;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public Date getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Double getDistance() {
        return distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}

