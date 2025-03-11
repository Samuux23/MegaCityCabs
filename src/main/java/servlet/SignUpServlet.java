package servlet;

import service.AdminService;
import service.CustomerService;
import service.DriverService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        String role = request.getParameter("role");
        try {
            if ("admin".equalsIgnoreCase(role)) {
                String username = request.getParameter("adminUsername");
                String password = request.getParameter("adminPassword");
                AdminService adminService = new AdminService();
                adminService.signUpAdmin(username, password);
            } else if ("customer".equalsIgnoreCase(role)) {
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
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Sign up failed: " + e.getMessage());
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}
