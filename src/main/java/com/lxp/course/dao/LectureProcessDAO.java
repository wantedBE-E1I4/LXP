package com.lxp.course.dao;

import com.lxp.course.dto.EnrollmentLecture;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LectureProcessDAO {

    private final Connection conn;

    public LectureProcessDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * 특정 enrollmentId의 강의 목록 조회
     */
    public List<EnrollmentLecture> findByEnrollmentId(int enrollmentId) {
        String sql = """
            SELECT lp.progress_id AS enrollment_lecture_id,
                   lp.enrollment_id,
                   l.lecture_id,
                   l.title AS lecture_name,
                   l.order_no AS lecture_order_no,
                   lp.completed
            FROM lecture_progress lp
            JOIN lectures l ON lp.lecture_id = l.lecture_id
            WHERE lp.enrollment_id = ?
            ORDER BY l.order_no
        """;

        List<EnrollmentLecture> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, enrollmentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new EnrollmentLecture(
                        rs.getInt("enrollment_lecture_id"),
                        rs.getInt("enrollment_id"),
                        rs.getInt("lecture_id"),
                        rs.getString("lecture_name"),
                        rs.getInt("lecture_order_no"),
                        rs.getBoolean("completed")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("강의 목록 조회 중 오류 발생", e);
        }

        return list;
    }

    /**
     * 강의 완료 처리
     */
    public int setEnrollmentLectureCompleted(int enrollmentId, int lectureId) {
        String sql = """
            UPDATE lecture_progress
            SET completed = TRUE
            WHERE enrollment_id = ? AND lecture_id = ?
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, enrollmentId);
            pstmt.setInt(2, lectureId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("강의 완료 처리 중 오류 발생", e);
        }
    }
}
