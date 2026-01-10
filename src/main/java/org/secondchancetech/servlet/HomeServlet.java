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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GadgetDAO dao = new GadgetDAO();
        List<Gadget> allProducts = null;

        req.setAttribute("newArrivals", allProducts.stream().limit(4).toList());
        req.setAttribute("recommended", allProducts.stream().skip(4).limit(4).toList());
        req.setAttribute("products", allProducts); // For 'Best Condition'

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}