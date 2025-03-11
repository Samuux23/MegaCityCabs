package servlet;

import dao.VehicleDAO;
import model.Vehicle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UpdateVehicleStatusServlet")
public class UpdateVehicleStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        try {
            Long vehicleId = Long.parseLong(request.getParameter("vehicleId"));
            String statusParam = request.getParameter("status");
            boolean newStatus = Boolean.parseBoolean(statusParam);
            VehicleDAO vehicleDAO = new VehicleDAO();
            Vehicle vehicle = vehicleDAO.find(vehicleId);
            if (vehicle != null) {
                vehicle.setStatus(newStatus);
                vehicleDAO.update(vehicle);
            }
            response.sendRedirect("DriverVehiclesServlet");
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to update vehicle status: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

