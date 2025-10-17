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
    //강좌들 조회
    public void showAllCourses() {
    }
    //강좌 수강신청 (강좌단위)
    public void enrollCourse(Scanner scanner) {
    }
    //강사 - 강좌 개설
    public void createCourse(Scanner scanner) {
    }
    //강사 - 강좌 삭제
    public void deleteCourse(Scanner scanner) {
    }
}
