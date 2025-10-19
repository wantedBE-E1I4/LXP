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

    /**
     * [추가] 특정 강좌에 속한 강의를 제목으로 찾아 논리적으로 삭제합니다.
     * @param title 삭제할 강의의 제목
     * @param courseId 해당 강의가 속한 강좌의 ID
     * @return 업데이트에 영향을 받은 row의 개수 (성공 시 1)
     */
    public int deleteByTitleAndCourseId(String title, int courseId) {
        String sql = "UPDATE lectures SET del_flag = 1 WHERE title = ? AND course_id = ?";
        int affectedRows = 0;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setInt(2, courseId);
            affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("강의 삭제 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return affectedRows;
    }


}
