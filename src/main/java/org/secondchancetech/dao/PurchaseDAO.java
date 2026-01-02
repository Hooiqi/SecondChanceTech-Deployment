package org.secondchancetech.dao;

import org.secondchancetech.model.Purchase;
import org.secondchancetech.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {

    // ---------- CREATE ----------
    public void createPurchase(Purchase purchase) throws SQLException {
        String sql = """
            INSERT INTO purchase
            (cart_id, purchased_date, total_amount)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, purchase.getCartId());
            ps.setString(2, purchase.getPurchasedDate()); // ISO string recommended
            ps.setDouble(3, purchase.getTotalAmount());

            ps.executeUpdate();
        }
    }

    // ---------- READ ----------
    public Purchase getPurchaseById(int purchaseId) {
        String sql = "SELECT * FROM purchase WHERE purchase_id = ?";
        Purchase purchase = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, purchaseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    purchase = mapRow(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchase;
    }

    public Purchase getPurchaseByCart(int cartId) {
        String sql = "SELECT * FROM purchase WHERE cart_id = ?";
        Purchase purchase = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    purchase = mapRow(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchase;
    }

    public List<Purchase> getAllPurchases() {
        List<Purchase> list = new ArrayList<>();
        String sql = "SELECT * FROM purchase ORDER BY purchased_date DESC";

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
    public boolean updateTotalAmount(int purchaseId, double totalAmount) {
        String sql = "UPDATE purchase SET total_amount = ? WHERE purchase_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, totalAmount);
            ps.setInt(2, purchaseId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------- DELETE ----------
    public boolean deletePurchase(int purchaseId) {
        String sql = "DELETE FROM purchase WHERE purchase_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, purchaseId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------- MAPPER ----------
    private Purchase mapRow(ResultSet rs) throws SQLException {
        Purchase p = new Purchase();
        p.setPurchaseId(rs.getInt("purchase_id"));
        p.setCartId(rs.getInt("cart_id"));
        p.setPurchasedDate(rs.getString("purchased_date"));
        p.setTotalAmount(rs.getDouble("total_amount"));
        return p;
    }
}
