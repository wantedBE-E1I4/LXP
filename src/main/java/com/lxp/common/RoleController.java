package com.lxp.common;

import com.lxp.course.controller.LearnerController;
import com.lxp.course.controller.TutorController;
import com.lxp.user.Role;
import com.lxp.user.User;
import com.lxp.user.UserService;
import com.lxp.view.common.RoleSelectView;

import java.sql.Connection;

public class RoleController {
    private RoleSelectView roleSelectView = new RoleSelectView();
    private UserService userService;

    public RoleController(Connection conn) {
        this.userService = new UserService(conn);
    }

    public void start() {
        boolean running = true;

        while (running) {
            // 역할 선택 화면 출력
            roleSelectView.show();
            // 입력받기
            String input = roleSelectView.getInput();

            switch (input) {
                // 학습자
                case "1":
                    User learner = this.userService.findUserByRole(Role.LEARNER);
                    if (learner != null) {
                        new LearnerController(learner).process();
                    } else {
                        System.out.println("사용자를 찾을 수 없습니다.");
                    }
                    break;
                // 강사
                case "2":
                    User tutor = this.userService.findUserByRole(Role.TUTOR);
                    if (tutor != null) {
                        new TutorController(tutor).process();
                    } else {
                        System.out.println("사용자를 찾을 수 없습니다.");
                    }
                    break;
                // 프로그램 종료
                case "Q", "q":
                    System.out.println("프로그램을 종료합니다.");
                    running = false;
                    break;
                // 잘못된 입력
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }
}
