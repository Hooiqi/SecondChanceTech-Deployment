package org.secondchancetech.servlet;

import org.secondchancetech.dao.ProductDAO;
import org.secondchancetech.dao.ProductSpecDAO;
import org.secondchancetech.dao.GadgetDAO;
import org.secondchancetech.model.Product;
import org.secondchancetech.model.ProductSpec;
import org.secondchancetech.model.Gadget;
import org.secondchancetech.model.Spec;
import org.secondchancetech.dao.SpecDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/products/add-product")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 5 * 1024 * 1024,   // 5MB
        maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class AddProductServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();
    private final ProductSpecDAO productSpecDAO = new ProductSpecDAO();
    private final GadgetDAO gadgetDAO = new GadgetDAO();
    private final SpecDAO specDAO = new SpecDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Fetch data for the dropdowns
        List<Gadget> gadgets = gadgetDAO.getAllGadgets();
        req.setAttribute("gadgets", gadgets);

        Map<Integer, List<Spec>> gadgetSpecsMap = new HashMap<>();
        for (Gadget g : gadgets) {
            gadgetSpecsMap.put(g.getGadgetId(), specDAO.getSpecsByGadgetId(g.getGadgetId()));
        }
        req.setAttribute("gadgetSpecsMap", gadgetSpecsMap);

        req.getRequestDispatcher("/WEB-INF/views/products/add-product.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int gadgetId = Integer.parseInt(req.getParameter("gadgetId"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            int deliveryDay = Integer.parseInt(req.getParameter("deliveryDay"));
            int guaranteedPeriod = Integer.parseInt(req.getParameter("guaranteedPeriod"));

            // ---------- IMAGE UPLOAD ----------
            Part imagePart = req.getPart("imageFile");
            String fileName = Path.of(imagePart.getSubmittedFileName())
                    .getFileName().toString();

            String uploadPath = getServletContext().getRealPath("/assets/images/");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            imagePart.write(uploadPath + File.separator + fileName);
            String imagePath = "/assets/images/" + fileName;

            // ---------- INSERT PRODUCT ----------
            Product product = new Product();
            product.setGadgetId(gadgetId);
            product.setName(name);
            product.setPrice(price);
            product.setDeliveryDay(deliveryDay);
            product.setGuaranteedPeriod(guaranteedPeriod);
            product.setImagePath(imagePath);

            product = productDAO.createProduct(product);
            int productId = product.getProductId(); // MUST be set by DAO

            // ---------- INSERT PRODUCT SPECS ----------
            req.getParameterMap().forEach((paramName, values) -> {

                if (paramName.startsWith("spec_")) {
                    int specId = Integer.parseInt(paramName.substring(5));
                    String specValue = values[0];

                    if (specValue != null && !specValue.trim().isEmpty()) {
                        try {
                            ProductSpec ps = new ProductSpec();
                            ps.setProductId(productId);
                            ps.setGadgetSpecId(specId);
                            ps.setSpecValue(specValue.trim());

                            productSpecDAO.createProductSpec(ps);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });

            resp.sendRedirect(req.getContextPath() + "/products");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Failed to add product");
            doGet(req, resp);
        }
    }

}
