package org.secondchancetech.servlet;

import org.secondchancetech.dao.ProductDAO;
import org.secondchancetech.model.Product;
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
        ProductDAO dao = new ProductDAO(); // Fetch products, not categories
        List<Product> allProducts = dao.getAllProducts();

        if (allProducts != null && !allProducts.isEmpty()) {
            req.setAttribute("newArrivals", allProducts.stream().limit(4).toList());
            req.setAttribute("recommended", allProducts.stream().skip(4).limit(4).toList());
            req.setAttribute("products", allProducts);
        }

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}