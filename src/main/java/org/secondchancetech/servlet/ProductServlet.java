package org.secondchancetech.servlet;

import org.secondchancetech.dao.ProductDAO;
import org.secondchancetech.dao.SpecDAO;
import org.secondchancetech.model.Product;
import org.secondchancetech.model.Spec;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Random; // Import for simulating scores

@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/") || pathInfo.length() < 2) {
            resp.sendRedirect(req.getContextPath() + "/gadgets");
            return;
        }

        try {
            int productId = Integer.parseInt(pathInfo.substring(1));
            ProductDAO productDAO = new ProductDAO();
            SpecDAO specDAO = new SpecDAO();

            Product product = productDAO.getProductById(productId);

            if (product == null) {
                resp.sendRedirect(req.getContextPath() + "/gadgets");
                return;
            }

            // 1. Fetch Basic Data
            List<Spec> specs = specDAO.getSpecsByProductId(productId);
            req.setAttribute("product", product);
            req.setAttribute("specs", specs);

            // 2. Generate Smart Scoring Data (Simulated Logic)
            // In a real app, you would fetch this from a 'ScoreDAO' or calculate it based on inputs
            generateSmartScores(req, product);

            req.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/gadgets");
        }
    }

    // Helper method to set scoring attributes
    private void generateSmartScores(HttpServletRequest req, Product product) {
        Random rand = new Random();

        // Individual Component Scores (Out of 10 or 5)
        int screenScore = 9;
        int bodyScore = 8;
        int buttonScore = 10;
        int batteryPercentage = 92;
        int functionalityScore = 25; // Out of 25

        // Category Scores (Out of 25)
        int physicalScore = (int) Math.round(((screenScore + bodyScore + buttonScore) / 30.0) * 25);
        int batteryScore = (int) Math.round((batteryPercentage / 100.0) * 25);
        int ageAndSpecScore = 22; // Example static value

        int totalScore = physicalScore + batteryScore + functionalityScore + ageAndSpecScore;

        // Set attributes for JSP to use
        req.setAttribute("screenScore", screenScore);
        req.setAttribute("bodyScore", bodyScore);
        req.setAttribute("buttonScore", buttonScore);
        req.setAttribute("batteryPercentage", batteryPercentage);
        req.setAttribute("functionalityScore", functionalityScore);

        req.setAttribute("physicalScore", physicalScore);
        req.setAttribute("batteryScore", batteryScore);
        req.setAttribute("ageAndSpecScore", ageAndSpecScore);
        req.setAttribute("totalScore", totalScore);

        // Calculate estimated fair price (e.g., 90-110% of listed price based on score)
        double priceMultiplier = 0.8 + (totalScore / 100.0 * 0.4);
        req.setAttribute("fairPrice", String.format("%.2f", product.getPrice() * priceMultiplier));
    }
}