package com.cjme.motorphsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/motorphdatabase";
    private static final String USER = "root";
    private static final String PASSWORD = "01Tartaros";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}


