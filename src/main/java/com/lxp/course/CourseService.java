package com.lxp.course;

import com.lxp.config.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// 강좌 관련 로직
public class CourseService {
    private final CourseDAO courseDAO = new CourseDAO();

    public List<Course> getCoursesForTeacher(int teacherId) {
        // 지금은 단순히 DAO를 호출하지만, 나중에 여기에 비즈니스 규칙이 추가될 수 있습니다.
        return courseDAO.findCoursesByTeacherId(teacherId);
    }

    /**
     * ID로 특정 강좌 하나의 정보를 조회합니다.
     * @param courseId 조회할 강좌의 ID
     * @return 조회된 Course 객체, 없으면 null
     */
    public Course findById(int courseId) {
        String sql = "SELECT * FROM courses WHERE course_id = ? AND del_flag = false";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Course(
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getInt("teacher_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 강좌 폐강을 처리합니다. 강좌의 소유권을 확인하는 비즈니스 로직을 포함합니다.
     * @param courseId 폐강할 강좌의 ID
     * @param teacherId 현재 로그인한 강사의 ID
     * @return 성공 시 true, 실패(강좌 없거나 권한 없음) 시 false
     */
    public boolean deleteCourse(int courseId, int teacherId) {
        // 1. 먼저 폐강할 강좌 정보를 가져옵니다.
        Course course = courseDAO.findById(courseId);

        // 2. 강좌가 존재하지 않거나, 강좌의 주인이 현재 로그인한 강사가 아니면 실패 처리
        if (course == null || course.getTeacherId() != teacherId) {
            return false;
        }

        // 3. 소유권이 확인되면 DAO를 통해 삭제(업데이트) 작업을 수행합니다.
        courseDAO.deleteCourseById(courseId);
        return true;
    }


}
