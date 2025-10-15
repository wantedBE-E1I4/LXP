package com.lxp.common;

import com.lxp.course.controller.LearnerController;
import com.lxp.course.controller.TutorController;
import com.lxp.user.User;
import com.lxp.view.common.RoleSelectView;

public class RoleController {
    private RoleSelectView roleSelectView = new RoleSelectView();

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
                    User learner = User.createLearner(1, "이학습");
                    new LearnerController(learner).process();
                    break;
                // 강사
                case "2":
                    User tutor = User.createTutor(1, "김강사");
                    new TutorController(tutor).process();
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
