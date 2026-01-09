package org.secondchancetech.servlet;

import org.secondchancetech.dao.GadgetDAO;
import org.secondchancetech.model.Gadget;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/gadgets")
public class GadgetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        GadgetDAO dao = new GadgetDAO();

        // This calls your updated method that joins gadget and product tables
        List<Gadget> gadgets = dao.getAllGadgets();

        // Pass the list to the JSP. The name "gadgets" must match ${gadgets} in your category.jsp
        req.setAttribute("gadgets", gadgets);
        req.setAttribute("categoryTitle", "ALL PRODUCTS");

        // Forward to your view
        req.getRequestDispatcher("/WEB-INF/views/category.jsp").forward(req, resp);
    }
}