// src/main/java/com/lxp/config/DatabaseManager.java
package com.lxp.config;

import com.lxp.user.Role;

import java.io.InputStream;
import java.sql.*;
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

    // 테스트 유저 2명 추가
    public static void initTestUsers(Connection conn) {
        // user_id가 이미 존재하면 아무 것도 바꾸지 않고 무시
        // 없으면 새로 추가
        String sql = "INSERT INTO users(user_id, user_name, role, email) " +
                "VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE user_name = user_name";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 학습자
            pstmt.setInt(1, 1);
            pstmt.setString(2, "이학습");
            pstmt.setString(3, Role.LEARNER.name());
            pstmt.setString(4, "learner@email.com");
            pstmt.executeUpdate();

            // 강사
            pstmt.setInt(1, 2);
            pstmt.setString(2, "김강사");
            pstmt.setString(3, Role.TUTOR.name());
            pstmt.setString(4, "tutor@email.com");
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}