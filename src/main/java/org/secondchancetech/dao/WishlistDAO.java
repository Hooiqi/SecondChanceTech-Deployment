package org.secondchancetech.dao;

import org.secondchancetech.model.Wishlist;
import org.secondchancetech.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {

    // ---------- CREATE ----------
    public void addToWishlist(Wishlist wishlist) throws SQLException {
        String sql = """
            INSERT INTO wishlist (user_id, product_id)
            VALUES (?, ?)
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, wishlist.getUserId());
            ps.setInt(2, wishlist.getProductId());

            ps.executeUpdate();
        }
    }

    // ---------- READ ----------
    public Wishlist getWishlistById(int wishlistId) {
        String sql = "SELECT * FROM wishlist WHERE wishlist_id = ?";
        Wishlist wishlist = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, wishlistId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    wishlist = mapRow(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wishlist;
    }

    public List<Wishlist> getWishlistByUser(int userId) {
        List<Wishlist> list = new ArrayList<>();
        String sql = "SELECT * FROM wishlist WHERE user_id = ? ORDER BY wishlist_id";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Wishlist> getAllWishlistItems() {
        List<Wishlist> list = new ArrayList<>();
        String sql = "SELECT * FROM wishlist";

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

    // ---------- DELETE ----------
    public boolean removeFromWishlist(int wishlistId) {
        String sql = "DELETE FROM wishlist WHERE wishlist_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, wishlistId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ---------- MAPPER ----------
    private Wishlist mapRow(ResultSet rs) throws SQLException {
        Wishlist w = new Wishlist();
        w.setWishlistId(rs.getInt("wishlist_id"));
        w.setUserId(rs.getInt("user_id"));
        w.setProductId(rs.getInt("product_id"));
        return w;
    }
}
