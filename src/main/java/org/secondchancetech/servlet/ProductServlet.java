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

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ProductDAO dao = new ProductDAO();
        List<Product> products = dao.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/jsp/products.jsp").forward(req, resp);
    }
}
