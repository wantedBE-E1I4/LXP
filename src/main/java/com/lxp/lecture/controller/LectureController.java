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

    /**
     * [수정] Service의 강의 삭제 기능을 호출합니다.
     * @param lectureTitle 삭제할 강의의 제목
     * @param courseId 해당 강의가 속한 강좌의 ID
     */
    public void deleteLectureFromCourse(String lectureTitle, int courseId) {
        boolean isDeleted = lectureService.deleteLectureByTitle(lectureTitle, courseId);
        // App.java의 출력 흐름을 존중하기 위해, Controller에서는 별도의 성공/실패 메시지를 출력하지 않습니다.
        if (!isDeleted) {
            System.out.println(">> 삭제할 강의를 찾지 못했거나, 이미 삭제되었습니다.");
        }
    }

    public void getCourseList() {
    }

    public void getLectureList(Long courseId) {
    }
}
