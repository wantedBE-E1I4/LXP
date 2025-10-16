package com.lxp.view.tutor;

import com.lxp.common.Displayable;
import com.lxp.course.Lecture;
import com.lxp.course.LectureDAO;
import com.lxp.course.LectureService;

import java.util.List;
import java.util.Scanner;

// 강의 관리 메뉴
public class LectureManagementView implements Displayable {
    Scanner scanner = new Scanner(System.in);
    LectureDAO lectureDAO = new LectureDAO();
    LectureService lectureService = new LectureService(lectureDAO);

    @Override
    public void show() {
        // 1) 강좌-관리 목록
        lectureService.courseManage();
        int courseId = scanner.nextInt();

        // 2) 강의 관리
        List<Lecture> lectures = lectureService.lectureManage(courseId);
    }
}
