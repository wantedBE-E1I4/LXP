// src/main/java/com/lxp/user/UserDAO.java
package com.lxp.user;

import com.lxp.config.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 사용자 데이터 접근
public class UserDAO {

    // 모든 사용자 정보를 출력하는 메서드
    public void printAllUsers() {
        String sql = "SELECT user_id, user_name, role, email FROM users";

        // try-with-resources: conn, pstmt, rs 객체를 자동으로 닫아주어 매우 안전합니다.
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n--- 📝 사용자 목록 ---");
            while (rs.next()) { // 결과가 없을 때까지 한 줄씩 반복
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String role = rs.getString("role");
                String email = rs.getString("email");

                System.out.printf("ID: %d, 이름: %s, 역할: %s, 이메일: %s\n", id, name, role, email);
            }
            System.out.println("--------------------");

        } catch (SQLException e) {
            System.out.println("데이터베이스 조회 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
}