package servlet;

import dao.VehicleDAO;
import model.Vehicle;
import model.DriverCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import org.json.JSONObject;

@WebServlet("/DeleteVehicleServlet")
public class DeleteVehicleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();

        try {
            // Get vehicleId from request
            String vehicleIdParam = request.getParameter("vehicleId");
            if (vehicleIdParam == null || vehicleIdParam.trim().isEmpty()) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Vehicle ID is missing.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            Long vehicleId = Long.parseLong(vehicleIdParam);

            // Retrieve logged-in driver from session
            HttpSession session = request.getSession();
            DriverCredential driver = (DriverCredential) session.getAttribute("driver");

            if (driver == null) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Unauthorized access. Please log in.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            VehicleDAO vehicleDAO = new VehicleDAO();
            Vehicle vehicle = vehicleDAO.find(vehicleId);

            if (vehicle == null) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Vehicle not found.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            // Check if the vehicle belongs to the logged-in driver
            if (!vehicle.getDriver().getId().equals(driver.getId())) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Unauthorized: You can only delete your own vehicles.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            // Delete vehicle
            vehicleDAO.delete(vehicle);
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Vehicle deleted successfully.");

        } catch (NumberFormatException e) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Invalid vehicle ID format.");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Failed to delete vehicle: " + e.getMessage());
        }

        response.getWriter().write(jsonResponse.toString());
    }
}

