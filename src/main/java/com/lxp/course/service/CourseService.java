package com.lxp.course.service;

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


}
