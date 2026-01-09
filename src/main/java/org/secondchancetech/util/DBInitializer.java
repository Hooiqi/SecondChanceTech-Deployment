package org.secondchancetech.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {

    public static void initialize() {
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            // ---------------- USER ----------------
            String userTable = """
                CREATE TABLE IF NOT EXISTS user (
                    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    first_name TEXT NOT NULL,
                    last_name TEXT NOT NULL,
                    email TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL,
                    gender TEXT,
                    address TEXT,
                    phone TEXT,
                    city TEXT,
                    state TEXT,
                    zipcode TEXT,
                    is_verified INTEGER DEFAULT 0
                )
            """;
            stmt.execute(userTable);

            // ---------------- GADGET ----------------
            String gadgetTable = """
                CREATE TABLE IF NOT EXISTS gadget (
                    gadget_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL
                )
            """;
            stmt.execute(gadgetTable);

            // ---------------- GADGET SPEC (SPEC TEMPLATE) ----------------
            String specTable = """
                CREATE TABLE IF NOT EXISTS spec (
                    gadget_spec_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    gadget_id INTEGER NOT NULL,
                    spec_key TEXT NOT NULL,
                    FOREIGN KEY (gadget_id) REFERENCES gadget(gadget_id)
                )
            """;
            stmt.execute(specTable);

            // ---------------- PRODUCT ----------------
            String productTable = """
                CREATE TABLE IF NOT EXISTS product (
                    product_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    gadget_id INTEGER,
                    delivery_day INTEGER,
                    guaranteed_period INTEGER,
                    price REAL,
                    image_path TEXT,
                    FOREIGN KEY (gadget_id) REFERENCES gadget(gadget_id)
                )
            """;
            stmt.execute(productTable);

            // ---------------- PRODUCT SPEC (ACTUAL VALUES) ----------------
            String productSpecTable = """
                CREATE TABLE IF NOT EXISTS product_spec (
                    product_spec_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    product_id INTEGER NOT NULL,
                    gadget_spec_id INTEGER NOT NULL,
                    spec_value TEXT NOT NULL,
                    FOREIGN KEY (product_id) REFERENCES product(product_id),
                    FOREIGN KEY (gadget_spec_id) REFERENCES gadget_spec(gadget_spec_id)
                )
            """;
            stmt.execute(productSpecTable);

            // ---------------- CART ----------------
            String cartTable = """
                CREATE TABLE IF NOT EXISTS cart (
                    cart_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER,
                    product_id INTEGER,
                    quantity INTEGER,
                    status TEXT,
                    FOREIGN KEY (user_id) REFERENCES user(user_id),
                    FOREIGN KEY (product_id) REFERENCES product(product_id)
                )
            """;
            stmt.execute(cartTable);

            // ---------------- PURCHASE ----------------
            String purchaseTable = """
                CREATE TABLE IF NOT EXISTS purchase (
                    purchase_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    cart_id INTEGER,
                    purchased_date TEXT,
                    total_amount REAL,
                    FOREIGN KEY (cart_id) REFERENCES cart(cart_id)
                )
            """;
            stmt.execute(purchaseTable);

            // ---------------- REVIEW ----------------
            String reviewTable = """
                CREATE TABLE IF NOT EXISTS review (
                    review_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    product_id INTEGER,
                    user_id INTEGER,
                    star_numbers INTEGER,
                    comment TEXT,
                    FOREIGN KEY (product_id) REFERENCES product(product_id),
                    FOREIGN KEY (user_id) REFERENCES user(user_id)
                )
            """;
            stmt.execute(reviewTable);

            // ---------------- USER PAYMENT ----------------
            String userPaymentTable = """
                CREATE TABLE IF NOT EXISTS user_payment (
                    user_payment_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    cardholder_name TEXT,
                    card_number TEXT,
                    exp_date TEXT,
                    cvv TEXT
                )
            """;
            stmt.execute(userPaymentTable);

            // ---------------- WISHLIST ----------------
            String wishlistTable = """
                CREATE TABLE IF NOT EXISTS wishlist (
                    wishlist_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER,
                    product_id INTEGER,
                    FOREIGN KEY (user_id) REFERENCES user(user_id),
                    FOREIGN KEY (product_id) REFERENCES product(product_id)
                )
            """;
            stmt.execute(wishlistTable);

            System.out.println("✅ All tables initialized successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
