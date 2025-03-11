package dao;

import model.Car;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    // Create a new Car record
    public void create(Car car) throws SQLException {
        String sql = "INSERT INTO cars (model, registrationNumber) VALUES (?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            ps.setString(1, car.getModel());
            ps.setString(2, car.getRegistrationNumber());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating car failed, no rows affected.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    car.setId(generatedKeys.getLong(1)); // Make sure your Car class has setId(long)
                } else {
                    throw new SQLException("Creating car failed, no ID obtained.");
                }
            }
        }
    }

    // Find a car by its ID
    public Car find(Long id) throws SQLException {
        Car car = null;
        String sql = "SELECT * FROM cars WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    car = new Car();
                    car.setId(rs.getLong("id"));
                    car.setModel(rs.getString("model"));
                    car.setRegistrationNumber(rs.getString("registrationNumber"));
                }
            }
        }
        return car;
    }

    // Retrieve all cars
    public List<Car> findAll() throws SQLException {
        List<Car> list = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getLong("id"));
                car.setModel(rs.getString("model"));
                car.setRegistrationNumber(rs.getString("registrationNumber"));
                list.add(car);
            }
        }
        return list;
    }

    // Update an existing car record
    public void update(Car car) throws SQLException {
        String sql = "UPDATE cars SET model = ?, registrationNumber = ? WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, car.getModel());
            ps.setString(2, car.getRegistrationNumber());
            ps.setLong(3, car.getId());
            ps.executeUpdate();
        }
    }

    // Delete a car record
    public void delete(Car car) throws SQLException {
        String sql = "DELETE FROM cars WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, car.getId());
            ps.executeUpdate();
        }
    }
}


