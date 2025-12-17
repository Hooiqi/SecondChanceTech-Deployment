package org.secondchancetech.dao;

import org.secondchancetech.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.secondchancetech.util.DBUtil;


public class UserDAO {

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
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
                list.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
