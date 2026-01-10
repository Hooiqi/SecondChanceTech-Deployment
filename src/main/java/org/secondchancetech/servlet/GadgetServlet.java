package org.secondchancetech.servlet;

import org.secondchancetech.dao.ProductDAO;
import org.secondchancetech.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/gadgets")
public class GadgetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ProductDAO dao = new ProductDAO();

        String search = req.getParameter("search"); // also used for category
        Double minPrice = parseDouble(req.getParameter("minPrice"));
        Double maxPrice = parseDouble(req.getParameter("maxPrice"));

        List<Product> products = dao.searchProducts(
                search, minPrice, maxPrice
        );

        req.setAttribute("products", products);

        String title = "ALL PRODUCTS";
        if (search != null && !search.isEmpty()) {
            title = search.toUpperCase();
        }

        req.setAttribute("categoryTitle", title);

        req.getRequestDispatcher("/WEB-INF/views/category.jsp")
           .forward(req, resp);
    }

    private Double parseDouble(String value) {
        try {
            return value != null ? Double.parseDouble(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

