package com.lxp.course.controller;

import com.lxp.course.CourseService;
import com.lxp.user.User;
import com.lxp.view.tutor.CourseManagementView;

public class CourseManagementController {
    private final User tutor;
    private final CourseManagementView view = new CourseManagementView();
    private final CourseService courseService = new CourseService();

    public CourseManagementController(User tutor) {
        this.tutor = tutor;
    }

    public void process() {
        boolean running = true;

        while (running) {
            view.show();

            String input = view.getInput();

            switch (input) {
                // 강좌 개설
                case "1":
                    System.out.println("강좌 개설");
                    break;
                // 강좌 폐강
                case "2":
                    System.out.println("강좌 폐강");
                    // TODO: 세부 구현 필요
                    //deleteCourse();
                    break;
                // 강좌-강의 관리
                case "3":
                    System.out.println("강좌-강의 관리");
                    // TODO: 강의 컨트롤러로 흐름 이동
                    //new LectureController(tutor);
                    break;
                case "Q", "q":
                    System.out.println("종료합니다");
                    System.exit(0);
                    break;
                default: // 잘못된 입력
                    System.out.println("다시 입력해주세요");
                    //showInvalidInputMessage(); // 세부 구현 필요
            }
            running = false;
        }
    }
}
