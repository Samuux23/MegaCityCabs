package servlet;

import dao.DriverDAO;
import model.DriverCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteDriverServlet")
public class DeleteDriverServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        String driverIdParam = request.getParameter("driverId");
        if (driverIdParam == null || driverIdParam.trim().isEmpty()) {
            response.sendRedirect("ViewDriversServlet?error=NoDriverIdProvided");
            return;
        }

        try {
            long driverId = Long.parseLong(driverIdParam);
            DriverDAO driverDAO = new DriverDAO();

            // Fetch the driver from DB
            DriverCredential driverToDelete = driverDAO.findById(driverId);
            if (driverToDelete != null) {
                driverDAO.delete(driverToDelete);
            }

            // Redirect with success
            response.sendRedirect("ViewDriversServlet?success=DriverDeleted");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ViewDriversServlet?error=" + e.getMessage());
        }
    }
}
