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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        GadgetDAO dao = new GadgetDAO();
        // Fetch products for the homepage
        List<Gadget> products = dao.getAllGadgets();

        // Pass the list to home.jsp. Use the name "products" to match your JSP loop
        req.setAttribute("products", products);

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}