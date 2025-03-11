package servlet;

import service.AdminDashboardService;
import service.SystemSettingService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        try {
            // Instantiate the AdminDashboardService to fetch counts
            AdminDashboardService dashboardService = new AdminDashboardService();
            int totalBookings = dashboardService.getAllBookings().size();
            int activeDrivers = dashboardService.getActiveDriversCount();
            int registeredCustomers = dashboardService.getRegisteredCustomersCount();
            
            // Optionally fetch current rate (if used on the dashboard)
            SystemSettingService settingService = new SystemSettingService();
            double currentRate = settingService.getCurrentRatePerKm();
            
            // Set the attributes to be displayed in adminDashboard.jsp
            request.setAttribute("totalBookings", totalBookings);
            request.setAttribute("activeDrivers", activeDrivers);
            request.setAttribute("registeredCustomers", registeredCustomers);
            request.setAttribute("currentRate", currentRate);
            
            // Forward to the JSP (not redirect) so these attributes are available
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to load dashboard: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
