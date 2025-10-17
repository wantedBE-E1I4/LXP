package com.lxp.course.service;

import com.lxp.course.Course;
import com.lxp.course.EnrollmentDAO;
import com.lxp.course.dao.CourseDAO;
import com.lxp.lecture.dao.LectureDAO;

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

    public Course openNewCourse(int UserId) {
        // TODO: 엔티티 생성
        Course.createCourse();
        // TODO: DAO 호출
        //
    }
}
