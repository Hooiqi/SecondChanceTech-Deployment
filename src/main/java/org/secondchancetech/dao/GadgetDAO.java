package org.secondchancetech.dao;

import org.secondchancetech.model.Gadget;
import org.secondchancetech.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GadgetDAO {

    // 1. For Homepage & Category Page: Joins gadget and product tables to get the price
    public List<Gadget> getAllGadgets() {
        List<Gadget> list = new ArrayList<>();
        // JOIN allows us to get the name from 'gadget' and the price from 'product'
        String sql = "SELECT g.gadget_id, g.name, p.price FROM gadget g " +
                "JOIN product p ON g.gadget_id = p.gadget_id ORDER BY g.name";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
            System.out.println("DEBUG: Found " + list.size() + " products."); // Helps verify connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. For Product Details: Gets specific info and specs
    public Gadget getGadgetById(int gadgetId) {
        // This query gets the basic info and price
        String sql = "SELECT g.gadget_id, g.name, p.price FROM gadget g " +
                "JOIN product p ON g.gadget_id = p.gadget_id " +
                "WHERE g.gadget_id = ?";
        Gadget gadget = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, gadgetId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    gadget = mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gadget;
    }

    // 3. Helper to get specifications for the Product Details page
    public List<String> getSpecsForGadget(int gadgetId) {
        List<String> specs = new ArrayList<>();
        String sql = "SELECT key, value FROM spec WHERE gadget_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, gadgetId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    specs.add(rs.getString("key") + ": " + rs.getString("value"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specs;
    }

    // UPDATED MAPPER: Now handles the price from the product table
    private Gadget mapRow(ResultSet rs) throws SQLException {
        Gadget g = new Gadget();
        g.setGadgetId(rs.getInt("gadget_id"));
        g.setName(rs.getString("name"));
        // Ensure your Gadget model has a setPrice(double) method
        g.setPrice(rs.getDouble("price"));
        return g;
    }
}