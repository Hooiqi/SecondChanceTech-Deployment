package org.secondchancetech.dao;

import org.secondchancetech.model.Spec;
import org.secondchancetech.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecDAO {

    public List<Spec> getSpecsByProductId(int productId) {
        List<Spec> list = new ArrayList<>();
        String sql = "SELECT * FROM spec WHERE product_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Spec> getSpecsByGadgetId(int gadgetId) {
        List<Spec> list = new ArrayList<>();
        // Note: If gadget_id is null in your DB, this may return an empty list
        String sql = "SELECT * FROM spec WHERE gadget_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, gadgetId);
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

    public List<Spec> getAllSpecs() {
        List<Spec> list = new ArrayList<>();
        // Order by product_id and then spec_id for a tidy list
        String sql = "SELECT * FROM spec ORDER BY product_id, spec_id";

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

    public Spec getSpecById(int specId) {
        String sql = "SELECT * FROM spec WHERE spec_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, specId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    private Spec mapRow(ResultSet rs) throws SQLException {
        Spec s = new Spec();
        s.setSpecId(rs.getInt("spec_id"));
        s.setProductId(rs.getInt("product_id"));
        s.setSpecKey(rs.getString("key"));   // DB column 'key'
        s.setSpecValue(rs.getString("value")); // DB column 'value'
        return s;
    }
}