package org.secondchancetech.servlet;

import org.secondchancetech.model.User;
import org.secondchancetech.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Passwords do not match.");
            req.getRequestDispatcher("/WEB-INF/views/auth/signup.jsp").forward(req, resp);
            return; // stop execution here
        }

        User user = new User();
        user.setFirstName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(password);

        User createdUser = authService.registerUser(user);

        if (createdUser != null) {
            req.getSession().setAttribute("currentUser", createdUser);
            req.setAttribute("success", "Account created! Please login.");
            resp.sendRedirect("register");
        } else {
            req.setAttribute("error", "Failed to create account. Try again.");
            req.getRequestDispatcher("/WEB-INF/views/auth/signup.jsp").forward(req, resp);
        }
    }
}
