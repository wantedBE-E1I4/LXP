package com.lxp.lecture.dao;

import com.lxp.lecture.dto.LecturesByCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LectureDAO {
    private Connection conn;

    public LectureDAO(Connection conn) {
        this.conn = conn;
    }

    public List<LecturesByCourse> findByCourseId(int courseId) {

        String sql = "SELECT order_no, title FROM lectures WHERE course_id = ?";

        List<LecturesByCourse> response = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LecturesByCourse data = new LecturesByCourse(
                        rs.getInt("order_no"),
                        rs.getString("title")
                );
                response.add(data);
            }

            return response;
        } catch (SQLException e) {
            System.out.println("오류가 발생했습니다.");
            throw new RuntimeException(e);
        }
    }
}
