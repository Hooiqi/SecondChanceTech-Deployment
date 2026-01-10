package org.secondchancetech.dao;

import org.secondchancetech.model.ProductSpec;
import org.secondchancetech.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecDAO {

    // Insert a ProductSpec into the database
    public void createProductSpec(ProductSpec ps) throws SQLException {
        String sql = """
            INSERT INTO product_spec
            (product_id, gadget_spec_id, spec_value)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setInt(1, ps.getProductId());
            psmt.setInt(2, ps.getGadgetSpecId());
            psmt.setString(3, ps.getSpecValue());

            psmt.executeUpdate();
        }
    }

    // Fetch all ProductSpecs for a product
    public List<ProductSpec> getProductSpecsByProductId(int productId) {
        List<ProductSpec> productSpecs = new ArrayList<>();
        String sql = "SELECT * FROM product_spec WHERE product_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProductSpec ps = new ProductSpec();
                ps.setProductSpecId(rs.getInt("product_spec_id"));
                ps.setProductId(rs.getInt("product_id"));
                ps.setGadgetSpecId(rs.getInt("gadget_spec_id"));
                ps.setSpecValue(rs.getString("spec_value"));
                productSpecs.add(ps);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productSpecs;
    }
}
