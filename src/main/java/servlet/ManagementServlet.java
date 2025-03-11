package servlet;

import model.Car;
import service.ManagementService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ManagementServlet")
public class ManagementServlet extends HttpServlet {
    private ManagementService managementService = new ManagementService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        String action = request.getParameter("action");
        String entity = request.getParameter("entity");
        if (action == null) {
            action = "list";
        }
        try {
            switch (action) {
                case "list":
                    if ("car".equals(entity)) {
                        request.setAttribute("cars", managementService.getAllCars());
                        request.getRequestDispatcher("carManagement.jsp").forward(request, response);
                    }
                    break;
                case "edit":
                    if ("car".equals(entity)) {
                        Long id = Long.parseLong(request.getParameter("id"));
                        Car car = managementService.getCarById(id);
                        request.setAttribute("car", car);
                        request.getRequestDispatcher("editCar.jsp").forward(request, response);
                    }
                    break;
                case "delete":
                    if ("car".equals(entity)) {
                        Long id = Long.parseLong(request.getParameter("id"));
                        managementService.deleteCar(id);
                        response.sendRedirect("ManagementServlet?action=list&entity=car");
                    }
                    break;
                default:
                    response.sendRedirect("ManagementServlet?action=list&entity=car");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        String action = request.getParameter("action");
        String entity = request.getParameter("entity");
        try {
            if ("add".equals(action)) {
                if ("car".equals(entity)) {
                    String model = request.getParameter("model");
                    String regNumber = request.getParameter("registrationNumber");
                    Car car = new Car(model, regNumber);
                    managementService.addCar(car);
                    response.sendRedirect("ManagementServlet?action=list&entity=car");
                }
            } else if ("update".equals(action)) {
                if ("car".equals(entity)) {
                    Long id = Long.parseLong(request.getParameter("id"));
                    String model = request.getParameter("model");
                    String regNumber = request.getParameter("registrationNumber");
                    Car car = new Car(model, regNumber);
                    car.setId(id);
                    managementService.updateCar(car);
                    response.sendRedirect("ManagementServlet?action=list&entity=car");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

