// src/main/java/com/lxp/config/DatabaseManager.java
package com.lxp.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.net.URL;

public class DatabaseManager {
    private static final Properties properties = new Properties();

    static {
        URL resourceUrl = DatabaseManager.class.getClassLoader().getResource("db.properties");
        if (resourceUrl == null) {
            System.err.println("CRITICAL ERROR: 'db.properties' 파일을 클래스패스에서 찾을 수 없습니다!");
        } else {
            System.out.println("INFO: 'db.properties' 파일의 실제 위치: " + resourceUrl.getPath());
        }
        try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(input);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load database properties.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );
    }
}