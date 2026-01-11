package org.secondchancetech.util;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String DB_URL;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            URL resource = DBUtil.class.getClassLoader().getResource("app.db");

            String dbPath = "";

            if (resource != null) {
                dbPath = new File(resource.toURI()).getAbsolutePath();
            } else {
                String userDir = System.getProperty("user.dir");
                dbPath = userDir + "/src/main/resources/app.db";
                System.out.println("⚠️ Warning: DB not found in classpath. Trying source folder: " + dbPath);
            }

            // 2. Connect to that dynamic path
            DB_URL = "jdbc:sqlite:" + dbPath;

            System.out.println("------------------------------------------------");
            System.out.println("✅ Dynamic DB Path: " + dbPath);
            System.out.println("------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing Database Connection", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}