package com.lxp.lecture.dao;

import com.lxp.course.Course;
import com.lxp.lecture.dto.LecturesByCourse;
import com.lxp.lecture.Lecture;

import java.sql.*;
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
                            rs.getString("title"),
                            rs.getInt("order_no")
                    );
                    result.add(lec);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch lectures by courseId=" + courseId, e);
        }
        return result;
    }

    public int save(Lecture lecture) {
        String selectSql = "SELECT IFNULL(MAX(order_no), 0) + 1 AS next_no " +
                "FROM lectures WHERE course_id = ? FOR UPDATE";
        String insertSql = "INSERT INTO lectures(course_id, title, order_no) VALUES (?, ?, ?)";

        try {
            conn.setAutoCommit(false); // 트랜잭션 시작

            int nextNo;
            try (PreparedStatement ps = conn.prepareStatement(selectSql)) {
                ps.setInt(1, lecture.getCourseId());
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    nextNo = rs.getInt("next_no");
                }
            }

            int generatedId;
            try (PreparedStatement ps = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, lecture.getCourseId());
                ps.setString(2, lecture.getTitle());
                ps.setInt(3, nextNo);
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    generatedId = rs.next() ? rs.getInt(1) : 0;
                }
            }

            conn.commit();
            return generatedId;

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ignore) {}
            throw new RuntimeException("강의 생성에 실패했습니다.", e);
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ignore) {}
        }
    }
}
