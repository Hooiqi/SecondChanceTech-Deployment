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
        List<Gadget> allProducts = dao.getAllGadgets();
        List<Gadget> arrivals = allProducts.stream().limit(4).toList();

        req.setAttribute("newArrivals", arrivals);

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}