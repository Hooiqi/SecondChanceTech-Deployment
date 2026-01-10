package org.secondchancetech.servlet;

import org.secondchancetech.dao.ProductDAO;
import org.secondchancetech.dao.ProductSpecDAO;
import org.secondchancetech.dao.SpecDAO;
import org.secondchancetech.model.Product;
import org.secondchancetech.model.ProductSpec;
import org.secondchancetech.model.Spec;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pathInfo = req.getPathInfo(); // e.g., "/123"
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendRedirect(req.getContextPath() + "/gadgets");
            return;
        }

        try {
            int productId = Integer.parseInt(pathInfo.substring(1));

            ProductDAO productDAO = new ProductDAO();
            SpecDAO specDAO = new SpecDAO();
            ProductSpecDAO pspecDAO = new ProductSpecDAO();

            Product product = productDAO.getProductById(productId);
            if (product == null) {
                resp.sendRedirect(req.getContextPath() + "/gadgets");
                return;
            }

            List<ProductSpec> productSpecs = pspecDAO.getProductSpecsByProductId(productId);

            Map<String, String> specsMap = new HashMap<>();
            for (ProductSpec ps : productSpecs) {
                Spec spec = specDAO.getSpecById(ps.getGadgetSpecId());
                if (spec != null) {
                    specsMap.put(spec.getSpecKey(), ps.getSpecValue());
                }
            }

            req.setAttribute("product", product);
            req.setAttribute("specs", specsMap);
            req.getRequestDispatcher("/WEB-INF/views/products/product.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/gadgets");
        }
    }
}

