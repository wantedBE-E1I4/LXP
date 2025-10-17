package com.lxp.course.dao;

import com.lxp.course.Course;
import com.lxp.course.dto.CourseData;
import com.lxp.course.dto.CourseDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// 강좌 데이터 접근
public class CourseDAO {
    private Connection conn;

    public CourseDAO(Connection conn) {
        this.conn = conn;
    }

    public CourseDetail findById(int courseId) {
        String sql = "SELECT c.title, c.description, c.category, u.user_name, c.created_at, c.updated_at FROM courses as c, users as u WHERE c.tutor_id = u.user_id AND c.course_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            CourseDetail response = new CourseDetail(
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("category"),
                    rs.getString("user_name"),
                    rs.getDate("created_at"),
                    rs.getDate("updated_at")
            );

            return response;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CourseData> findByUserId(int userId) {
        String sql = "SELECT * FROM courses";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            List<CourseData> response = new ArrayList<>();
            while (rs.next()) {
                CourseData data = new CourseData(
                        rs.getInt("course_id"),
                        rs.getString("title")
                );
                response.add(data);
            }

            return response;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
