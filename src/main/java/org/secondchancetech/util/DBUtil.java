package org.secondchancetech.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String DB_URL;

    static {
        try {
            Class.forName("org.sqlite.JDBC");

            // dynamic cross-platform path
            String userHome = System.getProperty("user.home");
            File folder = new File(userHome + "/secondchancetech/data");
            if (!folder.exists()) folder.mkdirs();

            DB_URL = "jdbc:sqlite:" + folder.getAbsolutePath() + "/app.db";
            System.out.println("SQLite DB path: " + DB_URL); // debug

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
