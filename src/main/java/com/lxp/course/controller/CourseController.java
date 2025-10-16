package com.lxp.course.controller;

import com.lxp.course.service.CourseService;
import com.lxp.course.EnrollmentService;
import com.lxp.lecture.service.LectureService;

import java.util.Scanner;

public class CourseController {
    private CourseService courseService;
    private LectureService lectureService;
    private EnrollmentService enrollmentService;

    public CourseController(CourseService courseService, LectureService lectureService, EnrollmentService enrollmentService) {
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.enrollmentService = enrollmentService;
    }
    public void showAllCourses() {
    }

    public void enrollCourse(Scanner scanner) {
    }

    public void createCourse(Scanner scanner) {
    }
}
