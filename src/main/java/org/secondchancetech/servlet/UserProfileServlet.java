package org.secondchancetech.servlet;

import org.secondchancetech.dao.UserDAO;
import org.secondchancetech.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/profile")
public class UserProfileServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            resp.sendRedirect("login");
            return;
        }

        User currentUser = (User) session.getAttribute("currentUser");

        req.setAttribute("user", currentUser);
        req.getRequestDispatcher("/WEB-INF/views/auth/profile.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            resp.sendRedirect("login");
            return;
        }

        User currentUser = (User) session.getAttribute("currentUser");

        // Update profile fields
        currentUser.setFirstName(req.getParameter("first_name"));
        currentUser.setLastName(req.getParameter("last_name"));
        currentUser.setGender(req.getParameter("gender"));
        currentUser.setPhone(req.getParameter("phone"));
        currentUser.setAddress(req.getParameter("address"));
        currentUser.setCity(req.getParameter("city"));
        currentUser.setState(req.getParameter("state"));
        currentUser.setZipcode(req.getParameter("zipcode"));

        boolean updated = userDAO.updateUser(currentUser);

        if (updated) {
            // keep session in sync
            session.setAttribute("currentUser", currentUser);
            req.setAttribute("success", "Profile updated successfully.");
        } else {
            req.setAttribute("error", "Failed to update profile.");
        }

        req.setAttribute("user", currentUser);
        req.getRequestDispatcher("/WEB-INF/views/auth/profile.jsp")
                .forward(req, resp);
    }
}
