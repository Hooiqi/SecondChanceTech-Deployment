package org.secondchancetech.servlet;

import org.secondchancetech.dao.GadgetDAO;
import org.secondchancetech.dao.ProductDAO;
import org.secondchancetech.model.Gadget;
import org.secondchancetech.model.Product;
import org.secondchancetech.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();
    private GadgetDAO gadgetDAO = new GadgetDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // --- SECURITY CHECK START ---
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
            // User is not logged in OR is not an admin -> Kick them to login
            resp.sendRedirect("login");
            return;
        }
        // --- SECURITY CHECK END ---

        // 1. Fetch all data
        List<Product> products = productDAO.getAllProducts();
        List<Gadget> gadgets = gadgetDAO.getAllGadgets();

        // 2. Calculate Total Products
        int totalProducts = products.size();

        // 3. Calculate Products per Category (Gadget)
        Map<String, Integer> categoryStats = new HashMap<>();
        Map<Integer, String> gadgetNames = new HashMap<>();

        for (Gadget g : gadgets) {
            categoryStats.put(g.getName(), 0);
            gadgetNames.put(g.getGadgetId(), g.getName());
        }

        for (Product p : products) {
            String categoryName = gadgetNames.get(p.getGadgetId());
            if (categoryName != null) {
                categoryStats.put(categoryName, categoryStats.get(categoryName) + 1);
            }
        }

        // 4. Send data to JSP
        req.setAttribute("totalProducts", totalProducts);
        req.setAttribute("categoryStats", categoryStats);
        req.setAttribute("products", products);

        req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // --- SECURITY CHECK START (Also protect POST actions) ---
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
            resp.sendRedirect("login");
            return;
        }
        // --- SECURITY CHECK END ---

        String action = req.getParameter("action");

        // Handle DELETE Product
        if ("delete".equals(action)) {
            try {
                int productId = Integer.parseInt(req.getParameter("id"));
                productDAO.deleteProduct(productId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Refresh the page
        resp.sendRedirect("admin");
    }
}