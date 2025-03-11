package servlet;

import dao.CustomerDAO;
import model.CustomerCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewCustomersServlet")
public class ViewCustomersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch all customers from DB
            CustomerDAO customerDAO = new CustomerDAO();
            List<CustomerCredential> customers = customerDAO.findAll();

            // Store them in request scope
            request.setAttribute("customers", customers);

            // Forward to the JSP page that displays the table
            request.getRequestDispatcher("viewCustomers.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to retrieve customers: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

