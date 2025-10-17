package com.lxp.lecture.dao;

import com.lxp.lecture.dto.LecturesByCourse;
import com.lxp.lecture.Lecture;

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

    public List<Lecture> findByCourseId(int courseId) {
        String sql = "select order_no, lecture_id, title " +
                "from lectures " +
                "where course_id = ? and del_flag = 0 " +
                "Order by order_no asc";

        List<Lecture> result = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Lecture lec = Lecture.ofIdCourseAndTitle(
                            rs.getInt("lecture_id"),
                            courseId,
                            rs.getString("title")
                    );
                    result.add(lec);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch lectures by courseId=" + courseId, e);
        }
        return result;
    }
}
