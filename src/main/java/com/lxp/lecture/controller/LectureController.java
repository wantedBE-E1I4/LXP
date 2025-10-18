package com.lxp.lecture.controller;

import com.lxp.course.service.EnrollmentService;
import com.lxp.course.service.CourseService;
import com.lxp.lecture.Lecture;
import com.lxp.lecture.service.LectureService;

import java.util.List;
import java.util.Scanner;

public class LectureController {
    private LectureService lectureService;
    private CourseService courseService;
    private EnrollmentService enrollmentService;
    public LectureController(LectureService lectureService, EnrollmentService enrollmentService, CourseService courseService) {
        this.lectureService = lectureService;
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
    }

    //강좌별 강의조회
    public void showLecturesByCourse(int courseId) {
        String title = courseService.getCourseTitle(courseId);
        System.out.println("== " + title + " ==");
        List<Lecture> lectures = lectureService.getLectureList(courseId);
        if (lectures.isEmpty()) {
            System.out.println("강의가 없습니다.");
        } else {
            for (Lecture lecture :  lectures ) {
                System.out.printf("%d. %s%n", lecture.getOrderNo(), lecture.getTitle());
            }
        }
    }

    //강좌별 강의 추가
    public void addLectureToCourse(String lectureTitle, int courseId) {
        lectureService.createLecture(lectureTitle, courseId);
    }
    //강좌별 강의 삭제
    public void deleteLectureFromCourse(Scanner scanner) {

    }

    public void getCourseList() {
    }

    public void getLectureList(Long courseId) {
    }
}
