package com.lxp.course;

import com.lxp.config.DatabaseManager;
import java.sql.*;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

// 강좌 데이터 접근
public class CourseDAO {

    public List<Course> findCoursesByTeacherId(int teacherId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE teacher_id = ? AND del_flag = false";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, teacherId);
            ResultSet rs = pstmt.executeQuery();

            // while 루프 안에서 ResultSet(rs)의 각 줄을 Course 객체로 변환합니다.
            while (rs.next()) {
                // ✅ 이 부분이 추가/수정된 핵심 로직입니다.
                int courseId = rs.getInt("course_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String category = rs.getString("category");
                // teacher_id는 이미 매개변수로 알고 있으므로 굳이 가져올 필요는 없습니다.

                // DB에서 가져온 데이터로 Course 객체를 생성합니다.
                Course course = new Course(courseId, title, description, category, teacherId);

                // 생성된 객체를 리스트에 추가합니다.
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    /**
     * 특정 강좌를 폐강 처리합니다 (del_flag를 true로 업데이트).
     * @param courseId 폐강할 강좌의 ID
     */
    public void deleteCourseById(int courseId) {
        String sql = "UPDATE courses SET del_flag = true WHERE course_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, courseId);
            pstmt.executeUpdate(); // INSERT, UPDATE, DELETE 쿼리는 executeUpdate() 사용

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // courseId를 받아서 DB에서 해당 강의 정보를 찾아 Course 객체로 반환하는 메서드
    public Optional<Course> findById(Long courseId) {
        String sql = "SELECT * FROM course WHERE id = ?"; // 실행할 SQL 쿼리
        Connection conn = null;      // DB 연결 객체
        PreparedStatement pstmt = null; // SQL 실행 객체
        ResultSet rs = null;         // 결과 담는 객체
        Course course = null;

        try {
            // 1. DB 연결 (이 부분은 별도의 DB 연결 클래스로 분리하는 것이 좋습니다)
            DatabaseManager DatabaseUtil = new DatabaseManager();
            conn = DatabaseUtil.getConnection();

            // 2. SQL 쿼리 실행 준비
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, courseId); // SQL의 첫 번째 '?'에 courseId 값을 설정

            // 3. 쿼리 실행 및 결과 받기
            rs = pstmt.executeQuery();

            // 4. 결과가 있다면 Course 객체로 변환
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getLong("id"));
                course.setTitle(rs.getString("title"));
                course.setProfessor(rs.getString("professor"));
                // ... 나머지 필드 설정
            }

        } catch (SQLException e) {
            e.printStackTrace(); // 실제로는 로깅 처리를 해야 합니다.
        } finally {
            // 5. 사용한 자원 반납 (매우 중요!)
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 결과가 없을 수도 있으므로 Optional로 감싸서 반환
        return Optional.ofNullable(course);
    }
}