package org.secondchancetech.dao;

import org.secondchancetech.model.Product;
import org.secondchancetech.model.Gadget;
import org.secondchancetech.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // ---------- SEARCH & FILTER (FIXED) ----------
    public List<Product> searchProducts(String query, Double minPrice, Double maxPrice, String specs) {
        List<Product> products = new ArrayList<>();

        // 1. Base Query: FIXED to join 'spec' table instead of 'product_specs'
        StringBuilder sql = new StringBuilder("""
            SELECT DISTINCT p.*, g.name AS gadget_name 
            FROM product p
            JOIN gadget g ON p.gadget_id = g.gadget_id
            LEFT JOIN spec s ON p.product_id = s.product_id
            WHERE 1=1
        """);

        List<Object> params = new ArrayList<>();

        // 2. Search Logic (Matches Category OR Product Name)
        if (query != null && !query.trim().isEmpty()) {
            sql.append(" AND (LOWER(g.name) LIKE LOWER(?) OR LOWER(p.name) LIKE LOWER(?))");
            String searchPattern = "%" + query.trim() + "%";
            params.add(searchPattern);
            params.add(searchPattern);
        }

        // 3. Price Filter
        if (minPrice != null && minPrice >= 0) {
            sql.append(" AND p.price >= ?");
            params.add(minPrice);
        }
        if (maxPrice != null && maxPrice > 0) {
            sql.append(" AND p.price <= ?");
            params.add(maxPrice);
        }

        // 4. Specs Filter (Matches 's.value')
        // Uses TRIM and LOWER to ensure "8GB" matches " 8GB " in database
        if (specs != null && !specs.trim().isEmpty()) {
            String[] specArray = specs.split(",");
            if (specArray.length > 0) {
                sql.append(" AND LOWER(TRIM(s.value)) IN (");
                for (int i = 0; i < specArray.length; i++) {
                    sql.append("LOWER(TRIM(?))");
                    params.add(specArray[i].trim());
                    if (i < specArray.length - 1) sql.append(",");
                }
                sql.append(")");
            }
        }

        sql.append(" ORDER BY p.price ASC");

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    products.add(mapRowWithGadget(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // ---------- CREATE ----------
    public Product createProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product (gadget_id, name, image_url, delivery_day, guaranteed_period, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, product.getGadgetId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getImagePath());
            ps.setInt(4, product.getDeliveryDay());
            ps.setInt(5, product.getGuaranteedPeriod());
            ps.setDouble(6, product.getPrice());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) product.setProductId(rs.getInt(1));
            }
        }
        return product;
    }

    // ---------- READ ----------
    public Product getProductById(int productId) {
        String sql = "SELECT p.*, g.name as gadget_name FROM product p JOIN gadget g ON p.gadget_id = g.gadget_id WHERE p.product_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRowWithGadget(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Product> getProductsByGadget(int gadgetId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE gadget_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, gadgetId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // ---------- UPDATE & DELETE ----------
    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET gadget_id=?, name=?, image_url=?, delivery_day=?, guaranteed_period=?, price=? WHERE product_id=?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, product.getGadgetId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getImagePath());
            ps.setInt(4, product.getDeliveryDay());
            ps.setInt(5, product.getGuaranteedPeriod());
            ps.setDouble(6, product.getPrice());
            ps.setInt(7, product.getProductId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE product_id=?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // ---------- MAPPERS ----------
    private Product mapRow(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setProductId(rs.getInt("product_id"));
        p.setGadgetId(rs.getInt("gadget_id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getDouble("price"));
        p.setDeliveryDay(rs.getInt("delivery_day"));
        p.setGuaranteedPeriod(rs.getInt("guaranteed_period"));
        try { p.setImagePath(rs.getString("image_url")); }
        catch (SQLException e) { try { p.setImagePath(rs.getString("image_path")); } catch (Exception ex) {} }
        return p;
    }

    private Product mapRowWithGadget(ResultSet rs) throws SQLException {
        Product p = mapRow(rs);
        Gadget gadget = new Gadget();
        gadget.setGadgetId(rs.getInt("gadget_id"));
        try { gadget.setName(rs.getString("gadget_name")); } catch (SQLException e) {}
        p.setGadget(gadget);
        return p;
    }
}