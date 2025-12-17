package org.secondchancetech.dao;

import org.secondchancetech.model.Gadget;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.secondchancetech.util.DBUtil;


public class GadgetDAO {

    public List<Gadget> getAllGadgets() {
        List<Gadget> list = new ArrayList<>();
        String sql = "SELECT * FROM gadgets";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Gadget g = new Gadget();
                g.setGadgetId(rs.getInt("gadget_id"));
                g.setName(rs.getString("name"));
                list.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
