package com.lxp.course.service;

import com.lxp.course.Course;
import com.lxp.course.EnrollmentDAO; // 필요한 DAO import
import com.lxp.course.dao.CourseDAO;
import com.lxp.lecture.dao.LectureDAO; // 필요한 DAO import

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 강좌 관련 로직
public class CourseService {
    private CourseDAO courseDAO;
    private LectureDAO lectureDAO;
    private EnrollmentDAO enrollmentDAO;

    public CourseService(CourseDAO courseDAO, LectureDAO lectureDAO, EnrollmentDAO enrollmentDAO) {
        this.courseDAO = courseDAO;
        this.lectureDAO = lectureDAO;
        this.enrollmentDAO = enrollmentDAO;
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
