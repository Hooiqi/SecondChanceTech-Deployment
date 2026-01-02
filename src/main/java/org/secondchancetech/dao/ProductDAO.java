package org.secondchancetech.dao;

import org.secondchancetech.model.Product;
import org.secondchancetech.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // ---------- CREATE ----------
    public void createProduct(Product product) throws SQLException {
        String sql = """
            INSERT INTO product
            (gadget_id, delivery_day, stock_day, guaranteed_period, price)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, product.getGadgetId());
            ps.setInt(2, product.getDeliveryDay());
            ps.setInt(3, product.getStockDay());
            ps.setInt(4, product.getGuaranteedPeriod());
            ps.setDouble(5, product.getPrice());

            ps.executeUpdate();
        }
    }

    // ---------- READ ----------
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        Product product = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = mapRow(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getProductsByGadget(int gadgetId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE gadget_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, gadgetId);
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

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";

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
    public boolean updateProduct(Product product) {
        String sql = """
            UPDATE product SET
                gadget_id = ?,
                delivery_day = ?,
                stock_day = ?,
                guaranteed_period = ?,
                price = ?
            WHERE product_id = ?
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, product.getGadgetId());
            ps.setInt(2, product.getDeliveryDay());
            ps.setInt(3, product.getStockDay());
            ps.setInt(4, product.getGuaranteedPeriod());
            ps.setDouble(5, product.getPrice());
            ps.setInt(6, product.getProductId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------- DELETE ----------
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE product_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------- MAPPER ----------
    private Product mapRow(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setProductId(rs.getInt("product_id"));
        p.setGadgetId(rs.getInt("gadget_id"));
        p.setDeliveryDay(rs.getInt("delivery_day"));
        p.setStockDay(rs.getInt("stock_day"));
        p.setGuaranteedPeriod(rs.getInt("guaranteed_period"));
        p.setPrice(rs.getDouble("price"));
        return p;
    }
}
