package org.secondchancetech.service;

import org.secondchancetech.dao.UserDAO;
import org.secondchancetech.model.User;
import org.secondchancetech.util.PasswordUtil;

import java.sql.SQLException;

public class AuthService {

    private final UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    // ---------- REGISTER ----------
    public boolean registerUser(User user) {
        // hash password before saving
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        user.setVerified(false); // default not verified

        try {
            userDAO.createUser(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---------- LOGIN ----------
    public User login(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user == null) return null;

        // compare password
        if (PasswordUtil.checkPassword(password, user.getPassword())) {
            return user; // login success
        } else {
            return null; // invalid password
        }
    }

    // ---------- VERIFY EMAIL ----------
    public boolean verifyUser(int userId) {
        User user = userDAO.getUserById(userId);
        if (user == null) return false;

        user.setVerified(true);
        return userDAO.updateUser(user);
    }

    // ---------- OPTIONAL: JWT ----------
    // You can add methods to generate JWT token if needed for your servlet sessions
}
