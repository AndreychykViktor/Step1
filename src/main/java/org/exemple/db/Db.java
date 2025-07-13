package org.exemple.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static final String URL = "jdbc:postgresql://localhost:5434/postgres";
    private static final String USER = "postgres777";
    private static final String PASS = "pg123456";

    public static Connection conn() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}