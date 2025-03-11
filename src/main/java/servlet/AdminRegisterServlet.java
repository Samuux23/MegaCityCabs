package servlet;

import service.CustomerService;
import service.DriverService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/AdminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        String role = request.getParameter("role");
        try {
            if ("customer".equalsIgnoreCase(role)) {
                String name = request.getParameter("customerName");
                String address = request.getParameter("customerAddress");
                String nic = request.getParameter("customerNIC");
                String contact = request.getParameter("customerContact");
                String username = request.getParameter("customerUsername");
                String password = request.getParameter("customerPassword");
                
                CustomerService customerService = new CustomerService();
                customerService.signUpCustomer(name, address, nic, contact, username, password);
            } else if ("driver".equalsIgnoreCase(role)) {
                String name = request.getParameter("driverName");
                String contact = request.getParameter("driverContact");
                String nic = request.getParameter("driverNIC");
                String username = request.getParameter("driverUsername");
                String password = request.getParameter("driverPassword");
                
                DriverService driverService = new DriverService();
                driverService.signUpDriver(name, contact, nic, username, password);
            }
            
            // After registration, redirect back to the admin dashboard.
            response.sendRedirect("AdminDashboardServlet");
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

