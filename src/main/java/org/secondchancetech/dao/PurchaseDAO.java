package org.secondchancetech.dao;

import org.secondchancetech.model.Purchase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.secondchancetech.util.DBUtil;


public class PurchaseDAO {

    public List<Purchase> getAllPurchases() {
        List<Purchase> list = new ArrayList<>();
        String sql = "SELECT * FROM purchases";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Purchase p = new Purchase();
                p.setPurchaseId(rs.getInt("purchase_id"));
                p.setCartId(rs.getInt("cart_id"));
                p.setPurchasedDate(rs.getString("purchased_date"));
                p.setTotalAmount(rs.getDouble("total_amount"));

                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
