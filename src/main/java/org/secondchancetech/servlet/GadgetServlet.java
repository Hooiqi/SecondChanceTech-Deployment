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

        // 1. Capture inputs from Homepage search or Category filters
        String searchQuery = req.getParameter("search");
        String category = req.getParameter("type");
        String minP = req.getParameter("minPrice");
        String maxP = req.getParameter("maxPrice");

        // 2. Convert price strings to Doubles
        Double minPrice = (minP != null && !minP.isEmpty()) ? Double.parseDouble(minP) : null;
        Double maxPrice = (maxP != null && !maxP.isEmpty()) ? Double.parseDouble(maxP) : null;

        // 3. Fetch filtered results
        List<Gadget> gadgets = dao.searchAndFilter(searchQuery, category, minPrice, maxPrice);

        // 4. Send data back to category.jsp
        req.setAttribute("gadgets", gadgets);
        req.setAttribute("categoryTitle", (category != null) ? category.toUpperCase() : "SEARCH RESULTS");

        req.getRequestDispatcher("/WEB-INF/views/category.jsp").forward(req, resp);
    }
}