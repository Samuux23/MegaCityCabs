package servlet;

import dao.VehicleDAO;
import model.Vehicle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AvailableVehiclesServlet")
public class AvailableVehiclesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        String vehicleType = request.getParameter("vehicleType");
        if (vehicleType == null || vehicleType.trim().isEmpty()){
            response.sendRedirect("customerDashboard.jsp");
            return;
        }
        
        try {
            VehicleDAO vehicleDAO = new VehicleDAO();
            List<Vehicle> vehicles = vehicleDAO.findAvailableVehicles(vehicleType);
            request.setAttribute("vehicles", vehicles);
            request.getRequestDispatcher("customerDashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            // Wrap the SQLException in a ServletException and throw it
            throw new ServletException("Error fetching available vehicles", e);
        }
    }
}


