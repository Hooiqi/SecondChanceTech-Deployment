package org.secondchancetech.dao;

import org.secondchancetech.model.Gadget;
import org.secondchancetech.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GadgetDAO {

    public List<Gadget> getAllGadgets() {
        List<Gadget> list = new ArrayList<>();
        // Updated to JOIN both tables and use 'gadgets' if that is your table name
        String sql = "SELECT g.gadget_id, g.name, p.product_id, p.price " +
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

    private Gadget mapRow(ResultSet rs) throws SQLException {
        Gadget g = new Gadget();
        g.setGadgetId(rs.getInt("gadget_id"));
        g.setName(rs.getString("name"));
        g.setProductId(rs.getInt("product_id")); // Required for home.jsp [cite: 61]
        g.setPrice(rs.getDouble("price"));       // Required for product.jsp
        return g;
    }
}