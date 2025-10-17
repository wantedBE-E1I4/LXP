package com.lxp.lecture.controller;

import com.lxp.lecture.Lecture;
import com.lxp.lecture.service.LectureService;

import java.util.List;
import java.util.Scanner;

public class LectureController {
    private LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    //강좌별 강의조회
    public void showLecturesByCourse(int courseId) {
        String title = courseService.getCourseTitle();
        System.out.println("== " + title + " ==");
        List<Lecture> lectures = lectureService.getLectureList(courseId);
        if (lectures.isEmpty()) {
            System.out.println("강의가 없습니다.");
        } else {
            for (Lecture lecture :  lectures ) {
                System.out.printf("%d. %s%n", lecture.getLectureId(), lecture.getTitle());
            }
        }
    }

    //강좌별 강의 추가
    public void addLectureToCourse(Scanner scanner) {

    }
    //강좌별 강의 삭제
    public void deleteLectureFromCourse(Scanner scanner) {

    }
}
