package com.lxp.course.dao;

import com.lxp.course.Course;

import java.sql.*;

// 강좌 데이터 접근
public class CourseDAO {
    private Connection conn;

    public CourseDAO(Connection conn) {
        this.conn = conn;
    }

    public int save(Course course) {
        String sql = "INSERT INTO courses(tutor_id, title, description, category) " +
                     "VALUES (?, ?, ?, ?)";

        // NOTE: Statement.RETURN_GENERATED_KEYS -> PK 반환 옵션
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // 학습자
            pstmt.setInt(1, course.getTutorId());
            pstmt.setString(2, course.getTitle());
            pstmt.setString(3, course.getDescription());
            pstmt.setString(4, course.getCategory());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // 생성된 ID만 반환
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
