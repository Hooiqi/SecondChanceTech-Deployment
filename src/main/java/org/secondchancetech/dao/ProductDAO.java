package org.secondchancetech.dao;

import org.secondchancetech.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.secondchancetech.util.DBUtil;


public class ProductDAO {

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setGadgetId(rs.getInt("gadget_id"));
                p.setDeliveryDay(rs.getInt("delivery_day"));
                p.setStockDay(rs.getInt("stock_day"));
                p.setGuaranteedPeriod(rs.getInt("guaranteed_period"));
                p.setPrice(rs.getDouble("price"));
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
