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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GadgetDAO dao = new GadgetDAO();

        // 1. Capture basic inputs from the Header Search or Category Sidebar
        String searchQuery = req.getParameter("search");
        String category = req.getParameter("type");
        String minP = req.getParameter("minPrice");
        String maxP = req.getParameter("maxPrice");

        // 2. Capture the comma-separated hardcoded specs (e.g., "8GB,256GB")
        String specs = req.getParameter("specs");

        // 3. Safely convert price strings to Doubles
        Double minPrice = (minP != null && !minP.isEmpty()) ? Double.parseDouble(minP) : null;
        Double maxPrice = (maxP != null && !maxP.isEmpty()) ? Double.parseDouble(maxP) : null;

        // 4. Fetch the filtered results from the DAO
        // Note: You will need to update your DAO's searchAndFilter to handle the 'specs' string
        List<Gadget> gadgets = dao.searchAndFilter(searchQuery, category, minPrice, maxPrice);

        // 5. Set attributes for the Category Page
        req.setAttribute("gadgets", gadgets);

        // Determine the title to display (e.g., "PHONES" or "Search Results")
        String title = "ALL PRODUCTS";
        if (searchQuery != null && !searchQuery.isEmpty()) {
            title = "SEARCH: " + searchQuery.toUpperCase();
        } else if (category != null && !category.isEmpty()) {
            title = category.toUpperCase();
        }
        req.setAttribute("categoryTitle", title);

        // 6. Forward to the category view
        req.getRequestDispatcher("/WEB-INF/views/category.jsp").forward(req, resp);
    }
}