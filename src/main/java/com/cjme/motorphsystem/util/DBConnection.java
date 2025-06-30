package com.cjme.motorphsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/payrollsystem_db";
    private static final String USER = "root";
    private static final String PASSWORD = "01Tartaros";
    private static Connection testConnection = null;

    public static void setTestConnection(Connection conn) {
        testConnection = conn;
    }
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}


