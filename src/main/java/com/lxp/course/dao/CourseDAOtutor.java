package com.lxp.course.dao;

import com.lxp.course.Course;
import com.lxp.config.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseDAOtutor {
    private final Connection conn;

    /**
     * [수정] App.java에서 Connection 객체를 주입받도록 생성자를 수정합니다.
     * @param conn 데이터베이스 연결 객체
     */
    public CourseDAOtutor(Connection conn) {
        this.conn = conn;
    }

    /**
     * [복원 및 수정] 특정 ID의 강좌를 논리적으로 삭제 처리합니다.
     * @param courseId 삭제할 강좌의 ID (int 타입)
     * @return 업데이트에 영향을 받은 row의 개수
     */
    public int deleteById(int courseId) {
        String sql = "UPDATE courses SET del_flag = true WHERE course_id = ?";
        int affectedRows = 0;

        // try-with-resources 구문은 PreparedStatement에만 적용합니다. (Connection은 App에서 관리)
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("강좌 삭제 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return affectedRows;
    }
}