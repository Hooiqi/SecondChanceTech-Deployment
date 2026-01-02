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
}
