package servlet;

import service.VehicleService;
import model.DriverCredential;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/VehicleServlet")
public class VehicleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject jsonResponse = new JSONObject();
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            DriverCredential driver = (DriverCredential) session.getAttribute("driver");
            if (driver == null) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Driver not found in session. Please log in again.");
                out.print(jsonResponse.toString());
                return;
            }
            String vehicleNumber = request.getParameter("vehicleNumber");
            String vehicleType   = request.getParameter("vehicleType");
            if (vehicleNumber == null || vehicleNumber.trim().isEmpty()) {
                throw new Exception("Vehicle number cannot be empty.");
            }
            if (vehicleType == null || vehicleType.trim().isEmpty()) {
                throw new Exception("Vehicle type cannot be empty.");
            }
            System.out.println("DEBUG: Attempting to add vehicle for driver ID: " + driver.getId());
            System.out.println("DEBUG: Vehicle Details -> Number: " + vehicleNumber + ", Type: " + vehicleType);
            // No image field in this version, so use empty string.
            String vehicleImage = "";
            VehicleService vehicleService = new VehicleService();
            vehicleService.addVehicle(driver, vehicleNumber, vehicleType, vehicleImage);
            System.out.println("DEBUG: Vehicle successfully added!");
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Vehicle added successfully!");
            out.print(jsonResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to add vehicle: " + e.getMessage());
            try (PrintWriter out = response.getWriter()) {
                out.print(errorResponse.toString());
            }
        }
    }
}


