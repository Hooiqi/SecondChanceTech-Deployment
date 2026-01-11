package org.secondchancetech.servlet;

import org.secondchancetech.dao.CartDAO;
import org.secondchancetech.model.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shoppingcart")
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        CartDAO dao = new CartDAO();
        List<Cart> carts = dao.getAllCarts();
        req.setAttribute("carts", carts);
        req.getRequestDispatcher("/WEB-INF/views/shoppingcart.jsp").forward(req, resp);
    }
}
