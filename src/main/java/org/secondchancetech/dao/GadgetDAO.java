package org.secondchancetech.dao;

import org.secondchancetech.model.Gadget;
import org.secondchancetech.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GadgetDAO {

    // ---------- CREATE ----------
    public void createGadget(Gadget gadget) throws SQLException {
        String sql = "INSERT INTO gadget (name) VALUES (?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, gadget.getName());
            ps.executeUpdate();
        }
    }

    // ---------- READ ----------
    public Gadget getGadgetById(int gadgetId) {
        String sql = "SELECT * FROM gadget WHERE gadget_id = ?";
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

    public List<Gadget> getAllGadgets() {
        List<Gadget> list = new ArrayList<>();
        String sql = "SELECT * FROM gadget ORDER BY name";

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
    public boolean updateGadget(Gadget gadget) {
        String sql = "UPDATE gadget SET name = ? WHERE gadget_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, gadget.getName());
            ps.setInt(2, gadget.getGadgetId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------- DELETE ----------
    public boolean deleteGadget(int gadgetId) {
        String sql = "DELETE FROM gadget WHERE gadget_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, gadgetId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------- MAPPER ----------
    private Gadget mapRow(ResultSet rs) throws SQLException {
        Gadget g = new Gadget();
        g.setGadgetId(rs.getInt("gadget_id"));
        g.setName(rs.getString("name"));
        return g;
    }
}
