package com.lxp.course.service;

import com.lxp.course.Course;
import com.lxp.course.EnrollmentDAO;
import com.lxp.course.dao.CourseDAO;
import com.lxp.course.dto.CourseData;
import com.lxp.course.dto.CourseDetail;
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

    public CourseDetail getCourseDetail(int courseId) {
        CourseDetail findedCourse =  courseDAO.findById(courseId);

        return findedCourse;
    }

    public List<CourseData> getCoursesByUser(int userId) {
        List<CourseData> findedCourses = courseDAO.findByUserId(userId);
        return findedCourses;
    }
}
