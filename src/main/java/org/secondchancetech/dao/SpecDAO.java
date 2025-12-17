package org.secondchancetech.dao;

import org.secondchancetech.model.Spec;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.secondchancetech.util.DBUtil;


public class SpecDAO {

    public List<Spec> getAllSpecs() {
        List<Spec> list = new ArrayList<>();
        String sql = "SELECT * FROM spec"; 

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Spec s = new Spec();
                s.setSpecId(rs.getInt("spec_id"));
                s.setGadgetId(rs.getInt("gadget_id"));
                s.setKey(rs.getString("key"));
                s.setValue(rs.getString("value"));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
