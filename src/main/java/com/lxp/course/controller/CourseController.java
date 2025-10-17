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

    // TODO : 강의 듣기 페이지
    public void taskCourse(Scanner scanner) {
        // NOTE : 내 수강 목록 띄우기

        // NOTE : 강좌 선택 IO

        /*-------구분선--------*/

        // NOTE : 선택한 강좌의 강의 띄우기

        // NOTE : 수강 여부 [y/n]

        /*--------구분선-------*/

        // NOTE : "<강좌이름 - 강의 이름> 수강 처리 되었습니다" 메시지 출력
    }
}
