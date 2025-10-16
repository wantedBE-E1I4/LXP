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
        // TODO: TutorMenuView.show();
        System.out.println("강사 메뉴 출력"); // header
        System.out.println("1. 강좌 관리");  // body
        System.out.print("원하는 번호를 입력하세요\n>>");  // footer
        Scanner scanner = new Scanner(System.in); // getInput()
        String input = scanner.nextLine();
        // ================================

        boolean running = true;

        while (running) {
            switch (input) {
                // 강좌 관리 메뉴 진입
                case "1":
                    new CourseManagementController(tutor).process();
                    break;
                // 프로그램 종료
                case "Q", "q":
                    System.out.println("종료합니다."); // TODO: TutorMenuView로 이동
                    running = false;
                    break;
                // 잘못된 입력
                default:
                    System.out.println("다시 입력해주세요."); // TODO: TutorMenuView로 이동
            }
        }
    }
}