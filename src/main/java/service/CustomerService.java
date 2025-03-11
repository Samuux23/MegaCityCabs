package service;

import dao.CustomerDAO;
import model.CustomerCredential;
import java.sql.SQLException;

public class CustomerService {
    
    private CustomerDAO customerDAO = new CustomerDAO();
    
    public boolean authenticate(String username, String password) throws Exception {
        try {
            CustomerCredential customer = customerDAO.findByUsername(username);
            return customer != null && customer.getPassword().equals(password);
        } catch (SQLException e) {
            throw new Exception("Error during customer authentication", e);
        }
    }
    
    public void signUpCustomer(String name, String address, String nic, String contact, String username, String password)
            throws Exception {
        try {
            CustomerCredential customer = new CustomerCredential(name, address, nic, contact, username, password);
            customerDAO.create(customer);
        } catch (SQLException e) {
            throw new Exception("Error signing up customer", e);
        }
    }
    
    public CustomerCredential getCustomerByUsername(String username) throws Exception {
        try {
            return customerDAO.findByUsername(username);
        } catch (SQLException e) {
            throw new Exception("Error fetching customer by username", e);
        }
    }
}


