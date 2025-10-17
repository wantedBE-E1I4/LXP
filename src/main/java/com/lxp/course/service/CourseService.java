package com.lxp.course.service;

import com.lxp.course.Course;
import com.lxp.course.EnrollmentDAO; // 필요한 DAO import
import com.lxp.course.dao.CourseDAO;
import com.lxp.course.dao.CourseDAOtutor;
import com.lxp.lecture.dao.LectureDAO; // 필요한 DAO import

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

// 강좌 관련 로직
public class CourseService {
    private CourseDAO courseDAO;
    private CourseDAOtutor courseDAOtutor;
    private LectureDAO lectureDAO;
    private EnrollmentDAO enrollmentDAO;

    public CourseService(CourseDAO courseDAO, LectureDAO lectureDAO, EnrollmentDAO enrollmentDAO) {
        this.courseDAO = courseDAO;
        this.lectureDAO = lectureDAO;
        this.enrollmentDAO = enrollmentDAO;
    }


    public boolean deleteCourseById(Long courseId) {

        // DAO에게 실제 삭제 작업을 위임합니다.
        // DAO의 deleteById 메서드가 삭제된 row의 개수(int)를 반환한다고 가정합니다.
        int affectedRows = courseDAOtutor.deleteById(courseId);
        // 1개 이상의 row가 영향을 받았다면 성공적으로 삭제된 것입니다.
        return affectedRows > 0;
    }
    /**
     * 모든 강좌 목록과 강사 이름을 조회합니다.
     * @return 각 요소가 [Course 객체, 강사 이름] 형태인 List<Object[]>
     */
    public List<Object[]> getAllCoursesWithTutorName() {
        try {
            // DAO의 메서드를 호출하여 결과를 그대로 반환
            return courseDAO.findAllCoursesWithTutorName();
        } catch (SQLException e) {
            System.out.println("강좌 목록 조회 중 오류가 발생했습니다.");
            e.printStackTrace();
            return new ArrayList<>(); // 오류 발생 시 빈 리스트 반환
        }
    }
}
