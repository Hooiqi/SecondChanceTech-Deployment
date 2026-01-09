package org.secondchancetech.dao;

import org.secondchancetech.model.Gadget;
import org.secondchancetech.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GadgetDAO {

    public List<Gadget> getAllGadgets() {
        List<Gadget> list = new ArrayList<>();
        String sql = "SELECT g.gadget_id, g.name, p.product_id, p.price, p.category, p.image_path " +
                "FROM gadget g JOIN product p ON g.gadget_id = p.gadget_id " +
                "ORDER BY g.name";

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

    public List<Gadget> searchAndFilter(String query, String category, Double minPrice, Double maxPrice) {
        List<Gadget> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT g.gadget_id, g.name, p.product_id, p.price, p.category, p.image_path " +
                        "FROM gadget g JOIN product p ON g.gadget_id = p.gadget_id WHERE 1=1 "
        );

        // Dynamic Query Building
        if (query != null && !query.isEmpty()) sql.append(" AND g.name LIKE ?");
        if (category != null && !category.isEmpty() && !category.equalsIgnoreCase("all")) sql.append(" AND p.category = ?");
        if (minPrice != null) sql.append(" AND p.price >= ?");
        if (maxPrice != null) sql.append(" AND p.price <= ?");

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int paramIdx = 1;
            if (query != null && !query.isEmpty()) ps.setString(paramIdx++, "%" + query + "%");
            if (category != null && !category.isEmpty() && !category.equalsIgnoreCase("all")) ps.setString(paramIdx++, category);
            if (minPrice != null) ps.setDouble(paramIdx++, minPrice);
            if (maxPrice != null) ps.setDouble(paramIdx++, maxPrice);

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

    public Gadget getGadgetById(int gadgetId) {
        String sql = "SELECT g.gadget_id, g.name, p.product_id, p.price, p.category, p.image_path " +
                "FROM gadget g JOIN product p ON g.gadget_id = p.gadget_id " +
                "WHERE g.gadget_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, gadgetId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Gadget mapRow(ResultSet rs) throws SQLException {
        Gadget g = new Gadget();
        g.setGadgetId(rs.getInt("gadget_id"));
        g.setName(rs.getString("name"));
        g.setProductId(rs.getInt("product_id"));
        g.setPrice(rs.getDouble("price"));
        g.setImagePath(rs.getString("image_url"));
        return g;
    }
}