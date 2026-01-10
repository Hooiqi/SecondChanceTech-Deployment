package org.secondchancetech.dao;

import org.secondchancetech.model.Spec;
import org.secondchancetech.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecDAO {

    // ---------- CREATE ----------
    public void createSpec(Spec spec) throws SQLException {
        String sql = "INSERT INTO spec (gadget_id, spec_key) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, spec.getGadgetId());
            ps.setString(2, spec.getSpecKey());
            ps.executeUpdate();
        }
    }

    // ---------- READ ----------
    public Spec getSpecById(int specId) {
        String sql = "SELECT * FROM spec WHERE gadget_spec_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, specId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Spec> getSpecsByGadgetId(int gadgetId) {
        List<Spec> list = new ArrayList<>();
        String sql = "SELECT * FROM spec WHERE gadget_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, gadgetId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Spec> getAllSpecs() {
        List<Spec> list = new ArrayList<>();
        String sql = "SELECT * FROM spec ORDER BY gadget_id, spec_id";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ---------- UPDATE ----------
    public boolean updateSpec(Spec spec) {
        String sql = "UPDATE spec SET spec_key = ? WHERE spec_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, spec.getSpecKey());
            ps.setInt(2, spec.getSpecId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------- DELETE ----------
    public boolean deleteSpec(int specId) {
        String sql = "DELETE FROM spec WHERE spec_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, specId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------- MAPPER ----------
    private Spec mapRow(ResultSet rs) throws SQLException {
        Spec s = new Spec();
        s.setSpecId(rs.getInt("gadget_spec_id"));
        s.setGadgetId(rs.getInt("gadget_id"));
        s.setSpecKey(rs.getString("spec_key"));
        return s;
    }
}
