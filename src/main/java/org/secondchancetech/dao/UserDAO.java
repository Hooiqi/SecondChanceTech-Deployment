package org.secondchancetech.dao;

import org.secondchancetech.model.User;
import org.secondchancetech.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // ---------- CREATE ----------
    public int createUser(User user) throws SQLException {
        String sql = """
        INSERT INTO user
        (first_name, last_name, password, email, gender, address, city, state, zipcode, is_verified)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword()); // hash it before saving!
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getGender());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getState());
            ps.setString(9, user.getZipcode());
            ps.setBoolean(10, user.isVerified());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // return the generated user ID
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }


    // ---------- READ ----------
    public User getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        User user = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = mapRow(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        User user = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = mapRow(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ---------- UPDATE ----------
    public boolean updateUser(User user) {
        String sql = """
            UPDATE user SET
                first_name = ?, last_name = ?, password = ?, gender = ?,
                address = ?, city = ?, state = ?, zipcode = ?, is_verified = ?
            WHERE user_id = ?
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword()); // hash before update
            ps.setString(4, user.getGender());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getCity());
            ps.setString(7, user.getState());
            ps.setString(8, user.getZipcode());
            ps.setBoolean(9, user.isVerified());
            ps.setInt(10, user.getUserId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------- DELETE ----------
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------- MAPPER ----------
    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserId(rs.getInt("user_id"));
        u.setFirstName(rs.getString("first_name"));
        u.setLastName(rs.getString("last_name"));
        u.setPassword(rs.getString("password"));
        u.setEmail(rs.getString("email"));
        u.setGender(rs.getString("gender"));
        u.setAddress(rs.getString("address"));
        u.setCity(rs.getString("city"));
        u.setState(rs.getString("state"));
        u.setZipcode(rs.getString("zipcode"));
        u.setVerified(rs.getBoolean("is_verified"));
        return u;
    }
}
