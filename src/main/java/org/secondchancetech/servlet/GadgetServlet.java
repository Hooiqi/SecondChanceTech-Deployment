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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();

        String search = req.getParameter("search");
        String type = req.getParameter("type");
        String minPriceStr = req.getParameter("minPrice");
        String maxPriceStr = req.getParameter("maxPrice");

        // FIX: Combine multiple 'specs' checkboxes into one comma-separated string
        String[] specsArray = req.getParameterValues("specs");
        String specs = null;
        if (specsArray != null && specsArray.length > 0) {
            specs = String.join(",", specsArray);
        }

        String queryTerm = (search != null && !search.trim().isEmpty()) ? search : type;
        Double minPrice = parseDouble(minPriceStr);
        Double maxPrice = parseDouble(maxPriceStr);

        List<Product> products = dao.searchProducts(queryTerm, minPrice, maxPrice, specs);

        req.setAttribute("products", products);

        String pageTitle = "ALL PRODUCTS";
        if (queryTerm != null && !queryTerm.isEmpty()) {
            pageTitle = queryTerm.toUpperCase();
        }
        req.setAttribute("categoryTitle", pageTitle);

        req.getRequestDispatcher("/WEB-INF/views/category.jsp").forward(req, resp);
    }

    private Double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        try {
            double d = Double.parseDouble(value);
            return (d == 0) ? null : d;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}