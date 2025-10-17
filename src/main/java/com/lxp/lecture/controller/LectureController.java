package com.lxp.lecture.controller;

import com.lxp.lecture.service.LectureService;

import java.util.Scanner;

public class LectureController {
    private LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    //강좌별 강의조회
    public void showLecturesByCourse(Scanner scanner) {
    }

    //강좌별 강의 추가
    public void addLectureToCourse(Scanner scanner) {

    }
    //강좌별 강의 삭제
    public void deleteLectureFromCourse(Scanner scanner) {

    }
}
