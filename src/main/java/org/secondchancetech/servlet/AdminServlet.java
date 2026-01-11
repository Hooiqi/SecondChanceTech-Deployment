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

@WebServlet("/admin/dashboard")
public class AdminServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();
    private final GadgetDAO gadgetDAO = new GadgetDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // --- 1. SECURITY CHECK ---
        HttpSession session = req.getSession();
        // Match the attribute name "currentUser" used in your LoginServlet
        User user = (User) session.getAttribute("currentUser");

        if (user == null || !"ADMIN".equals(user.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // --- 2. DATA FETCHING ---
        List<Product> products = productDAO.getAllProducts();
        List<Gadget> gadgets = gadgetDAO.getAllGadgets();

        // --- 3. DASHBOARD STATISTICS CALCULATION ---
        int totalProducts = products.size();
        int totalCategories = gadgets.size();

        // Map category (Gadget) names to the count of products in each
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

        // --- 4. SEND DATA TO JSP ---
        // These names must match the ${name} used in your admin.jsp file
        req.setAttribute("totalProducts", totalProducts);
        req.setAttribute("totalCategories", totalCategories);
        req.setAttribute("categoryStats", categoryStats);
        req.setAttribute("products", products);
        req.setAttribute("gadgets", gadgets);

        // Forward to the JSP inside the private WEB-INF folder
        req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");

        if (user == null || !"ADMIN".equals(user.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            try {
                int productId = Integer.parseInt(req.getParameter("id"));
                productDAO.deleteProduct(productId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // After an action, redirect back to the dashboard to refresh data
        resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
    }
}