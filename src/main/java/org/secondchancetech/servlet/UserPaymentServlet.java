package org.secondchancetech.servlet;

import org.secondchancetech.dao.UserPaymentDAO;
import org.secondchancetech.model.UserPayment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/payments")
public class UserPaymentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        UserPaymentDAO dao = new UserPaymentDAO();
        List<UserPayment> payments = dao.getAllPayments();
        req.setAttribute("payments", payments);
        req.getRequestDispatcher("/jsp/payments.jsp").forward(req, resp);
    }
}
