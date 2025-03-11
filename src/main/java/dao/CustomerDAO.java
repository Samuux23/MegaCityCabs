package dao;

import model.CustomerCredential;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public CustomerCredential findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM customerCredentials WHERE username = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return mapRowToCustomer(rs);
                }
            }
        }
        return null;
    }
    
    public CustomerCredential findById(Long id) throws SQLException {
        String sql = "SELECT * FROM customerCredentials WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return mapRowToCustomer(rs);
                }
            }
        }
        return null;
    }
    
    public void create(CustomerCredential customer) throws SQLException {
        String sql = "INSERT INTO customerCredentials (name, address, nic, contactNumber, username, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getNic());
            ps.setString(4, customer.getContactNumber());
            ps.setString(5, customer.getUsername());
            ps.setString(6, customer.getPassword());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    customer.setId(rs.getLong(1));
                }
            }
        }
    }
    
    public void update(CustomerCredential customer) throws SQLException {
        String sql = "UPDATE customerCredentials SET name=?, address=?, nic=?, contactNumber=?, username=?, password=? WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getNic());
            ps.setString(4, customer.getContactNumber());
            ps.setString(5, customer.getUsername());
            ps.setString(6, customer.getPassword());
            ps.setLong(7, customer.getId());
            ps.executeUpdate();
        }
    }
    
    public void delete(CustomerCredential customer) throws SQLException {
        String sql = "DELETE FROM customerCredentials WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, customer.getId());
            ps.executeUpdate();
        }
    }
    
    public List<CustomerCredential> findAll() throws SQLException {
        List<CustomerCredential> list = new ArrayList<>();
        String sql = "SELECT * FROM customerCredentials";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                list.add(mapRowToCustomer(rs));
            }
        }
        return list;
    }
    
    private CustomerCredential mapRowToCustomer(ResultSet rs) throws SQLException {
        CustomerCredential customer = new CustomerCredential();
        customer.setId(rs.getLong("id"));
        customer.setName(rs.getString("name"));
        customer.setAddress(rs.getString("address"));
        customer.setNic(rs.getString("nic"));
        customer.setContactNumber(rs.getString("contactNumber"));
        customer.setUsername(rs.getString("username"));
        customer.setPassword(rs.getString("password"));
        return customer;
    }
}