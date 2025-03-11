package servlet;

import dao.CustomerDAO;
import model.CustomerCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        String customerIdParam = request.getParameter("customerId");
        if (customerIdParam == null || customerIdParam.trim().isEmpty()) {
            response.sendRedirect("ViewCustomersServlet?error=NoCustomerIdProvided");
            return;
        }

        try {
            long customerId = Long.parseLong(customerIdParam);
            CustomerDAO customerDAO = new CustomerDAO();

            // Fetch the customer from DB
            CustomerCredential customerToDelete = customerDAO.findById(customerId);
            if (customerToDelete != null) {
                customerDAO.delete(customerToDelete);
            }

            // Redirect with success
            response.sendRedirect("ViewCustomersServlet?success=CustomerDeleted");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ViewCustomersServlet?error=" + e.getMessage());
        }
    }
}
