package org.secondchancetech.servlet;

import org.secondchancetech.dao.CartDAO;
import org.secondchancetech.dao.ProductDAO;
import org.secondchancetech.model.Cart;
import org.secondchancetech.model.Product;
import org.secondchancetech.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/cart")
public class CartAPIServlet extends HttpServlet {

    private CartDAO cartDAO = new CartDAO();
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Prevent caching so cart updates immediately when switching users
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);

        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");

        HttpSession session = req.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;

        if (currentUser == null) {
            // If not logged in, return empty list
            out.print("[]");
            return;
        }

        int userId = currentUser.getUserId();

        try {
            if ("list".equals(action)) {
                List<Cart> carts = cartDAO.getCartsByUser(userId);
                StringBuilder json = new StringBuilder("[");

                boolean first = true;
                for (Cart cart : carts) {
                    if ("DRAFT".equals(cart.getStatus())) {
                        Product product = productDAO.getProductById(cart.getProductId());
                        if (product != null) {
                            if (!first) json.append(",");
                            json.append("{")
                                    .append("\"id\":").append(cart.getCartId()).append(",")
                                    .append("\"productId\":").append(product.getProductId()).append(",")
                                    .append("\"name\":\"").append(escapeJson(product.getName())).append("\",")
                                    .append("\"sku\":\"#").append(product.getProductId()).append("\",")
                                    .append("\"price\":").append(product.getPrice()).append(",")
                                    .append("\"quantity\":").append(cart.getQuantity()).append(",")
                                    // Image path fix
                                    .append("\"image\":\"").append(req.getContextPath()).append("/assets").append(escapeJson(product.getImagePath())).append("\"")
                                    .append("}");
                            first = false;
                        }
                    }
                }
                json.append("]");
                out.print(json.toString());
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"" + escapeJson(e.getMessage()) + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");

        HttpSession session = req.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;

        if (currentUser == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print("{\"error\":\"User not logged in\"}");
            return;
        }

        int userId = currentUser.getUserId();
        // --------------------------------

        try {
            if ("add".equals(action)) {
                int productId = Integer.parseInt(req.getParameter("productId"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));

                // Check if product already exists for THIS user
                List<Cart> userCarts = cartDAO.getCartsByUser(userId);
                Cart existingCart = null;

                for (Cart c : userCarts) {
                    if (c.getProductId() == productId && "DRAFT".equals(c.getStatus())) {
                        existingCart = c;
                        break;
                    }
                }

                if (existingCart != null) {
                    cartDAO.updateQuantity(existingCart.getCartId(), existingCart.getQuantity() + quantity);
                } else {
                    Cart cart = new Cart();
                    cart.setUserId(userId);
                    cart.setProductId(productId);
                    cart.setQuantity(quantity);
                    cart.setStatus("DRAFT");
                    cartDAO.createCart(cart);
                }
                out.print("{\"success\":true}");

            } else if ("update".equals(action)) {
                int cartId = Integer.parseInt(req.getParameter("cartId"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                cartDAO.updateQuantity(cartId, quantity);
                out.print("{\"success\":true}");

            } else if ("remove".equals(action)) {
                int cartId = Integer.parseInt(req.getParameter("cartId"));
                cartDAO.deleteCart(cartId);
                out.print("{\"success\":true}");

            } else if ("checkout".equals(action)) {
                List<Cart> carts = cartDAO.getCartsByUser(userId);
                for (Cart cart : carts) {
                    if ("DRAFT".equals(cart.getStatus())) {
                        cartDAO.updateStatus(cart.getCartId(), "PAID");
                    }
                }
                out.print("{\"success\":true}");
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"" + escapeJson(e.getMessage()) + "\"}");
        }
    }

    private String escapeJson(String str) {
        if (str == null) return "";
        return str.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}