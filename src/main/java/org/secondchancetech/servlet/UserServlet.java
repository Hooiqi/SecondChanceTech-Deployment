package org.secondchancetech.servlet;

import org.secondchancetech.dao.UserDAO;
import org.secondchancetech.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        UserDAO dao = new UserDAO();
        List<User> users = dao.getAllUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
    }
}
