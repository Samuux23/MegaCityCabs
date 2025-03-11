package servlet;

import dao.DriverDAO;
import model.DriverCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewDriversServlet")
public class ViewDriversServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch all drivers (or only active drivers if you prefer)
            DriverDAO driverDAO = new DriverDAO();
            List<DriverCredential> drivers = driverDAO.findAll();

            // Store them in request scope
            request.setAttribute("drivers", drivers);

            // Forward to the JSP page that displays the table
            request.getRequestDispatcher("viewDrivers.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to retrieve drivers: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
