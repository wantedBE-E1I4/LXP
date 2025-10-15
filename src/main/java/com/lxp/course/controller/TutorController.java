package com.lxp.course.controller;

import com.lxp.user.User;

// 강사 메뉴 및 기능 흐름 제어
public class TutorController {
    private final User tutor;

    public TutorController(User tutor) {
        this.tutor = tutor;
    }

    public void process() {
        // TODO: LearnerView.showMenu();
        // TODO: 사용자 입력값에 따라 수강 목록 조회 / 수강 신청 등 분기 처리
        System.out.println("강사 메뉴 출력");
    }
}
