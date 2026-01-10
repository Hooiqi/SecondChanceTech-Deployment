package org.secondchancetech.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.secondchancetech.model.Product;
import org.secondchancetech.model.Spec;
import org.secondchancetech.model.ProductSpec;
import org.secondchancetech.model.Gadget;

@WebServlet("/smart-scoring")
public class SmartScoringServlet extends HttpServlet {

    // Helper class to pass display data to JSP
    public static class ProductSpecDisplay {
        private String specKey;
        private String specValue;

        public ProductSpecDisplay(String specKey, String specValue) {
            this.specKey = specKey;
            this.specValue = specValue;
        }

        public String getSpecKey() { return specKey; }
        public String getSpecValue() { return specValue; }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // ======================
        // MOCK GADGET
        // ======================
        Gadget gadget = new Gadget();
        gadget.setGadgetId(1);
        gadget.setName("iPhone 13 Pro Max");

        // ======================
        // MOCK SPECS (SPEC TEMPLATE)
        // ======================
        List<Spec> specs = new ArrayList<>();
        specs.add(new Spec(1, gadget.getGadgetId(), "Screen Size"));
        specs.add(new Spec(2, gadget.getGadgetId(), "Battery"));
        specs.add(new Spec(3, gadget.getGadgetId(), "Processor"));
        specs.add(new Spec(4, gadget.getGadgetId(), "RAM"));
        specs.add(new Spec(5, gadget.getGadgetId(), "Storage"));

        // ======================
        // MOCK PRODUCT
        // ======================
        Product product = new Product();
        product.setProductId(1);
        product.setGadgetId(gadget.getGadgetId());
        product.setName(gadget.getName());
        product.setDeliveryDay(3);
        product.setGuaranteedPeriod(6);
        product.setPrice(3500.00);
        product.setImagePath("assets/images/iphone13.jpg");

        // ======================
        // MOCK PRODUCT SPEC (ACTUAL VALUES)
        // ======================
        List<ProductSpec> productSpecs = new ArrayList<>();
        productSpecs.add(new ProductSpec(1, product.getProductId(), specs.get(0).getSpecId(), "6.7 inches"));
        productSpecs.add(new ProductSpec(2, product.getProductId(), specs.get(1).getSpecId(), "4352 mAh"));
        productSpecs.add(new ProductSpec(3, product.getProductId(), specs.get(2).getSpecId(), "A15 Bionic"));
        productSpecs.add(new ProductSpec(4, product.getProductId(), specs.get(3).getSpecId(), "6 GB"));
        productSpecs.add(new ProductSpec(5, product.getProductId(), specs.get(4).getSpecId(), "256 GB"));

        // ======================
        // Map ProductSpec + Spec -> ProductSpecDisplay for JSP
        // ======================
        List<ProductSpecDisplay> productSpecDisplays = new ArrayList<>();
        for (ProductSpec ps : productSpecs) {
            Spec matchingSpec = specs.stream()
                                     .filter(s -> s.getSpecId() == ps.getGadgetSpecId())
                                     .findFirst()
                                     .orElse(null);
            if (matchingSpec != null) {
                productSpecDisplays.add(new ProductSpecDisplay(
                        matchingSpec.getSpecKey(),
                        ps.getSpecValue()
                ));
            }
        }

        // ======================
        // MOCK SCORES
        // ======================
        int screenScore = 10;
        int bodyScore = 7;
        int buttonScore = 7;
        int physicalScore = screenScore + bodyScore + buttonScore;

        int batteryScore = 22; // out of 25
        int functionalityScore = 23; // out of 25
        int ageAndSpecScore = 20; // out of 25
        int totalScore = physicalScore + batteryScore + functionalityScore + ageAndSpecScore;

        // Ensure totalScore <= 100
        if (totalScore > 100) totalScore = 100;

        // ======================
        // Battery percentage (dynamic)
        // ======================
        int batteryPercentage = 88; // example

        // ======================
        // FAIR PRICE
        // ======================
        double fairPrice = product.getPrice() * (totalScore / 100.0) * 0.2;

        // ======================
        // PASS TO JSP
        // ======================
        req.setAttribute("product", product);
        req.setAttribute("productSpecs", productSpecDisplays);

        req.setAttribute("screenScore", screenScore);
        req.setAttribute("bodyScore", bodyScore);
        req.setAttribute("buttonScore", buttonScore);
        req.setAttribute("physicalScore", physicalScore);
        req.setAttribute("batteryScore", batteryScore);
        req.setAttribute("functionalityScore", functionalityScore);
        req.setAttribute("ageAndSpecScore", ageAndSpecScore);
        req.setAttribute("totalScore", totalScore);
        req.setAttribute("batteryPercentage", batteryPercentage);
        req.setAttribute("fairPrice", String.format("%.2f", fairPrice));

        req.getRequestDispatcher("/WEB-INF/views/smart-scoring/index.jsp").forward(req, resp);
    }
}
