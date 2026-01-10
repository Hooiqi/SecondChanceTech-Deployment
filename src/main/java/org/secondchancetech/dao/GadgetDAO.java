package org.secondchancetech.dao;

import org.secondchancetech.model.Gadget;
import org.secondchancetech.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GadgetDAO {

    public List<Gadget> getAllGadgets() {
        List<Gadget> list = new ArrayList<>();
        String sql = "SELECT gadget_id, name FROM gadget " +
                "ORDER BY name";

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

    public Gadget getGadgetById(int gadgetId) {
        // FIX: Changed 'image_path' to 'image_url'
        String sql = "SELECT g.gadget_id, g.name, p.product_id, p.price, p.image_url " +
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

    public List<Gadget> searchGadgets(
            String search,
            String category,
            Double minPrice,
            Double maxPrice
    ) {
        List<Gadget> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT g.gadget_id, g.name, p.product_id, p.price, p.image_url " +
                        "FROM gadget g " +
                        "JOIN product p ON g.gadget_id = p.gadget_id " +
                        "WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();

        if (search != null && !search.isEmpty()) {
            sql.append("AND g.name LIKE ? ");
            params.add("%" + search + "%");
        }

        if (minPrice != null) {
            sql.append("AND p.price >= ? ");
            params.add(minPrice);
        }

        if (maxPrice != null) {
            sql.append("AND p.price <= ? ");
            params.add(maxPrice);
        }

        sql.append("ORDER BY g.name");

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

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

    private Gadget mapRow(ResultSet rs) throws SQLException {
        Gadget g = new Gadget();
        g.setGadgetId(rs.getInt("gadget_id"));
        g.setName(rs.getString("name"));
        return g;
    }

    public List<String> getUniqueSpecValues(String category, String specKey) {
        List<String> values = new ArrayList<>();
        // Join product and spec tables to find values for a specific category and key
        String sql = "SELECT DISTINCT s.value FROM spec s " +
                "JOIN product p ON s.gadget_id = p.gadget_id " +
                "WHERE AND s.key = ? ORDER BY s.value";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            ps.setString(2, specKey);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    values.add(rs.getString("value"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }
}