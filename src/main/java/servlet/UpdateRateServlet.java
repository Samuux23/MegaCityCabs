package servlet;

import service.SystemSettingService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UpdateRateServlet")
public class UpdateRateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        try {
            // 1) Parse the new rate from the form
            String rateStr = request.getParameter("ratePerKm");
            if (rateStr == null || rateStr.trim().isEmpty()) {
                throw new Exception("Rate per KM is required.");
            }
            double rate = Double.parseDouble(rateStr);

            // 2) Update the rate in the database via the service.
            // The SystemSettingService will update the rate and also set the updatedAt timestamp.
            SystemSettingService settingService = new SystemSettingService();
            settingService.updateRatePerKm(rate);

            // 3) Redirect back to Admin Dashboard to see the changes
            response.sendRedirect("AdminDashboardServlet");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Rate update failed: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

