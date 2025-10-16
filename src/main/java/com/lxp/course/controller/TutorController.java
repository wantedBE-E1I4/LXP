package com.lxp.course.controller;

import com.lxp.course.CourseService;
import com.lxp.user.User;

import java.util.Scanner;

// 강사 메뉴 및 기능 흐름 제어
public class TutorController {
    private final User tutor;
    private final CourseService courseService = new CourseService();

    public TutorController(User tutor) {
        this.tutor = tutor;
    }

    public void process() {
        // TODO: LearnerView.showMenu();
        // TODO: 사용자 입력값에 따라 수강 목록 조회 / 수강 신청 등 분기 처리
        System.out.println("강사 메뉴 출력");
        System.out.println("1. 강좌 관리");

        // === TODO: View로 이동 ===
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // =======================

        boolean running = true;

        while (running) {
            switch (input) {
                // 강좌 관리 메뉴 진입
                case "1":
                    enterCourseManagement();
                    break;
                case "Q", "q":
                    System.out.println("종료합니다.");
                    running = false;
                    break;
                default:
                    System.out.println("다시 입력해주세요.");
            }
        }
    }

    private void enterCourseManagement() {
        System.out.println("here");
    }
}
