package org.exemple.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    static String url = "jdbc:postgresql://localhost:5433/Tinder";
    static String username = "postgres";
    static String password = "pg123456";

    public static Connection conn() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}