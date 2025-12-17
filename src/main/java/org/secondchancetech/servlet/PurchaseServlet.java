package org.secondchancetech.servlet;

import org.secondchancetech.dao.PurchaseDAO;
import org.secondchancetech.model.Purchase;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/purchases")
public class PurchaseServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PurchaseDAO dao = new PurchaseDAO();
        List<Purchase> purchases = dao.getAllPurchases();
        req.setAttribute("purchases", purchases);
        req.getRequestDispatcher("/jsp/purchases.jsp").forward(req, resp);
    }
}
