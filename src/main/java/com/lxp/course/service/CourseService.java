package com.lxp.course.service;

import com.lxp.course.Course;
import com.lxp.course.EnrollmentDAO;
import com.lxp.course.dao.CourseDAO;
import com.lxp.lecture.dao.LectureDAO;

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

    public List<Course> getAllCourses() {
        // DAO를 통해 모든 강좌를 가져옵니다.
        return courseDAO.findAll();
    }

    public boolean deleteCourseById(Long courseId) {

        // DAO에게 실제 삭제 작업을 위임합니다.
        // DAO의 deleteById 메서드가 삭제된 row의 개수(int)를 반환한다고 가정합니다.
        int affectedRows = courseDAO.deleteById(courseId);
        // 1개 이상의 row가 영향을 받았다면 성공적으로 삭제된 것입니다.
        return affectedRows > 0;
    }
}
