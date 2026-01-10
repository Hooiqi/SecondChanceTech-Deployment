package org.secondchancetech.dao;

import org.secondchancetech.model.Product;
import org.secondchancetech.model.Gadget;
import org.secondchancetech.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // ---------- CREATE ----------
    public Product createProduct(Product product) throws SQLException {
        String sql = """
            INSERT INTO product
            (gadget_id, name, image_path, delivery_day, guaranteed_period, price)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, product.getGadgetId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getImagePath());
            ps.setInt(4, product.getDeliveryDay());
            ps.setInt(5, product.getGuaranteedPeriod());
            ps.setDouble(6, product.getPrice());

            ps.executeUpdate();

            // Get generated ID
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    product.setProductId(rs.getInt(1));
                }
            }
        }

        return product;
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

    public List<Product> searchProducts(
            String search,
            Double minPrice,
            Double maxPrice
    ) {
        List<Product> products = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
            SELECT
                p.product_id,
                p.name,
                p.price,
                p.image_path,
                p.delivery_day,
                p.guaranteed_period,
                g.gadget_id,
                g.name AS gadget_name
            FROM product p
            JOIN gadget g ON p.gadget_id = g.gadget_id
            WHERE 1=1
        """);

        List<Object> params = new ArrayList<>();

        // Search / Category BOTH map to gadget name
        if (search != null && !search.isEmpty()) {
            sql.append(" AND g.name LIKE ?");
            params.add("%" + search + "%");
        }

        if (minPrice != null) {
            sql.append(" AND p.price >= ?");
            params.add(minPrice);
        }

        if (maxPrice != null) {
            sql.append(" AND p.price <= ?");
            params.add(maxPrice);
        }

        sql.append(" ORDER BY g.name");

        try (Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Gadget gadget = new Gadget();
                    gadget.setGadgetId(rs.getInt("gadget_id"));
                    gadget.setName(rs.getString("gadget_name"));

                    Product product = new Product();
                    product.setProductId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setImagePath(rs.getString("image_path"));
                    product.setDeliveryDay(rs.getInt("delivery_day"));
                    product.setGuaranteedPeriod(rs.getInt("guaranteed_period"));
                    product.setGadget(gadget);

                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
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
                name = ?,
                image_path = ?,
                delivery_day = ?,
                guaranteed_period = ?,
                price = ?
            WHERE product_id = ?
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, product.getGadgetId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getImagePath());
            ps.setInt(4, product.getDeliveryDay());
            ps.setInt(5, product.getGuaranteedPeriod());
            ps.setDouble(6, product.getPrice());
            ps.setInt(7, product.getProductId());

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
        p.setName(rs.getString("name"));
        p.setImagePath(rs.getString("image_path"));
        p.setDeliveryDay(rs.getInt("delivery_day"));
        p.setGuaranteedPeriod(rs.getInt("guaranteed_period"));
        p.setPrice(rs.getDouble("price"));
        return p;
    }
}
