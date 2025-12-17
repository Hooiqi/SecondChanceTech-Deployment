package org.secondchancetech.dao;

import org.secondchancetech.model.Wishlist;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.secondchancetech.util.DBUtil;


public class WishlistDAO {

    public List<Wishlist> getAllWishlist() {
        List<Wishlist> list = new ArrayList<>();
        String sql = "SELECT * FROM wishlist";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Wishlist w = new Wishlist();
                w.setWishlistId(rs.getInt("wishlist_id"));
                w.setUserId(rs.getInt("user_id"));
                w.setProductId(rs.getInt("product_id"));
                list.add(w);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
