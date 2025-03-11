package servlet;

import service.AdminService;
import service.CustomerService;
import service.DriverService;
import model.CustomerCredential;
import model.DriverCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("DEBUG: Login request => role=" + role + ", username=" + username);
        boolean authenticated = false;
        HttpSession session = request.getSession();
        try {
            if ("admin".equalsIgnoreCase(role)) {
                AdminService adminService = new AdminService();
                authenticated = adminService.authenticate(username, password);
                System.out.println("DEBUG: adminService.authenticate => " + authenticated);
                if (authenticated) {
                    System.out.println("DEBUG: Admin login success => username=" + username);
                    session.setAttribute("role", "admin");
                    session.setAttribute("username", username);
                    response.sendRedirect("adminDashboard.jsp");
                    return;
                }
            } else if ("customer".equalsIgnoreCase(role)) {
                CustomerService customerService = new CustomerService();
                authenticated = customerService.authenticate(username, password);
                System.out.println("DEBUG: customerService.authenticate => " + authenticated);
                if (authenticated) {
                    CustomerCredential customer = customerService.getCustomerByUsername(username);
                    if (customer == null) {
                        System.out.println("DEBUG: getCustomerByUsername returned null for " + username);
                    } else {
                        System.out.println("DEBUG: Customer login success => username=" + customer.getUsername() + ", ID=" + customer.getId());
                    }
                    session.setAttribute("role", "customer");
                    session.setAttribute("customer", customer);
                    response.sendRedirect("customerDashboard.jsp");
                    return;
                }
            } else if ("driver".equalsIgnoreCase(role)) {
                DriverService driverService = new DriverService();
                authenticated = driverService.authenticate(username, password);
                System.out.println("DEBUG: driverService.authenticate => " + authenticated);
                if (authenticated) {
                    DriverCredential driver = driverService.getDriverByUsername(username);
                    if (driver == null) {
                        System.out.println("DEBUG: getDriverByUsername returned null for " + username);
                    } else {
                        System.out.println("DEBUG: Driver login success => name=" + driver.getName() + ", username=" + driver.getUsername() + ", ID=" + driver.getId());
                    }
                    session.setAttribute("role", "driver");
                    session.setAttribute("driver", driver);
                    response.sendRedirect("DriverVehiclesServlet");
                    return;
                }
            }
            System.out.println("DEBUG: Authentication failed => role=" + role + ", username=" + username);
            request.setAttribute("error", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Login failed: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
