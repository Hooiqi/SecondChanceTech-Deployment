package org.secondchancetech.dao;

import org.secondchancetech.model.Cart;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.secondchancetech.util.DBUtil;


public class CartDAO {

    public List<Cart> getAllCarts() {
        List<Cart> list = new ArrayList<>();
        String sql = "SELECT * FROM cart";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cart c = new Cart();
                c.setCartId(rs.getInt("cart_id"));
                c.setUserId(rs.getInt("user_id"));
                c.setProductId(rs.getInt("product_id"));
                c.setQuantity(rs.getInt("quantity"));
                c.setStatus(rs.getString("status"));
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
