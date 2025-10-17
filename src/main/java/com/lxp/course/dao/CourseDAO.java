package com.lxp.course.dao;

import com.lxp.config.DatabaseManager;
import com.lxp.course.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 강좌 데이터 접근 객체 (courses 테이블 담당)
 */
public class CourseDAO {

    /**
     * 삭제되지 않은 모든 강좌 목록을 데이터베이스에서 조회합니다.
     * @return 모든 Course 객체를 담은 리스트
     */
    public List<Course> findAll() {
        // [수정] SQL 스키마에 정의된 정확한 컬럼명을 사용합니다.
        String sql = "SELECT course_id, title, description, category, teacher_id, created_at, updated_at, del_flag FROM courses WHERE del_flag = false";
        List<Course> courses = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // [수정] Course.java의 생성자에 맞게 모든 필드를 매핑합니다.
                Course course = new Course(
                        rs.getLong("course_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getLong("teacher_id"),
                        rs.getTimestamp("created_at").toLocalDateTime(), // Timestamp -> LocalDateTime 변환
                        rs.getTimestamp("updated_at").toLocalDateTime(), // Timestamp -> LocalDateTime 변환
                        rs.getBoolean("del_flag")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("강좌 목록 조회 중 오류 발생");
            e.printStackTrace();
        }
        return courses;
    }

    /**
     * 특정 ID의 강좌를 논리적으로 삭제 처리합니다. (del_flag를 true로 업데이트)
     * @param courseId 삭제할 강좌의 ID
     * @return 업데이트에 영향을 받은 row의 개수 (성공 시 1, 실패 시 0)
     */
    public int deleteById(Long courseId) {
        // [수정] is_deleted -> del_flag로 컬럼명 수정
        String sql = "UPDATE courses SET del_flag = true WHERE course_id = ?";
        int affectedRows = 0;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, courseId);
            affectedRows = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("강좌 삭제 중 오류 발생");
            e.printStackTrace();
        }
        return affectedRows;
    }

    /**
     * 새로운 강좌를 데이터베이스에 저장합니다.
     * @param course 저장할 Course 객체
     * @return 저장된 Course 객체 (생성된 ID 포함)
     */
    public Course save(Course course) {
        String sql = "INSERT INTO courses (title, description, category, teacher_id) VALUES (?, ?, ?, ?)";
        ResultSet generatedKeys = null;

        try (Connection conn = DatabaseManager.getConnection();
             // [수정] INSERT 후 자동 생성된 키(ID)를 반환받기 위한 옵션 추가
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, course.getTitle());
            pstmt.setString(2, course.getDescription());
            pstmt.setString(3, course.getCategory());
            pstmt.setLong(4, course.getTeacherId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // 저장 성공 시, DB가 생성한 course_id를 가져와서 course 객체에 설정
                generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    course.setCourseId(generatedKeys.getLong(1));
                }
                System.out.println("새로운 강좌가 성공적으로 저장되었습니다. (ID: " + course.getId() + ")");
            }
        } catch (SQLException e) {
            System.out.println("강좌 저장 중 오류 발생");
            e.printStackTrace();
        }
        return course;
    }
}