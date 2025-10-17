package com.lxp.course;

import com.lxp.course.dto.EnrollmentData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    private Connection conn;

    public EnrollmentDAO(Connection conn) {
        this.conn = conn;
    }

    public List<EnrollmentData> findByUserId(int userId) {
        String sql = "SELECT c.course_id, c.title, u.user_name FROM enrollments as e, courses as c, users as u WHERE e.course_id = c.course_id AND c.tutor_id = u.user_id AND e.user_id = ?";
        List<EnrollmentData> response = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                EnrollmentData data = new EnrollmentData(
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getString("user_name")
                );
                response.add(data);
            }

            return response;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
