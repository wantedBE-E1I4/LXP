package com.lxp.user.dao;

import com.lxp.config.DatabaseManager;
import com.lxp.user.Role;
import com.lxp.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 데이터에 접근하는 객체 (DAO)
 * [REFACTOR] 더 안전한 Connection 관리를 위해 생성자 주입 방식을 제거했습니다.
 */
public class UserDAO {

    /**
     * 모든 사용자 정보를 DB에서 조회하여 반환합니다.
     * [REFACTOR] DAO는 데이터를 출력(print)하지 않고, 조회하여 객체로 반환하는 역할만 수행합니다.
     * @return 모든 User 객체를 담은 리스트
     */
    public List<User> findAllUsers() {
        String sql = "SELECT user_id, user_name, role, email FROM users";
        List<User> userList = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = User.createFromDB(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        Role.valueOf(rs.getString("role"))
                );
                user.setEmail(rs.getString("email"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 조회 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * 특정 역할을 가진 사용자를 조회합니다.
     * @param role 조회할 역할 (LEARNER or TUTOR)
     * @return 조회된 User 객체 (없으면 null)
     */
    public User getUserByRole(Role role) {
        String sql = "SELECT user_id AS id, user_name AS name, role FROM users WHERE role = ?";

        try (Connection conn = DatabaseManager.getConnection(); // 메서드 내에서 Connection 관리
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, role.name());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // 이제 User.createFromDB() 호출이 정상적으로 작동합니다.
                    return User.createFromDB(rs.getInt("id"), rs.getString("name"), Role.valueOf(rs.getString("role")));
                }
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 조회 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return null; // 조회된 사용자가 없을 경우 null 반환
    }
}