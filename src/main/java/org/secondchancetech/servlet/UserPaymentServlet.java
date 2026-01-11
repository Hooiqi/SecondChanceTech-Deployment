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

@WebServlet({"/step1", "/step2", "/step3", "/step4"})
public class UserPaymentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        UserPaymentDAO dao = new UserPaymentDAO();
        List<UserPayment> payments = dao.getAllUserPayments();
        req.setAttribute("payments", payments);

        String path = req.getServletPath();

        req.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp").forward(req, resp);
    }
}
