package com.lxp.course.controller;

import com.lxp.user.User;

// 학습자 메뉴 및 기능 흐름 제어
public class LearnerController {
    private final User learner;
    //private final CourseService courseService;
    //private final LearnerView learnerView;

    public LearnerController(User learner) {
        this.learner = learner;
    }

    // NOTE: 의존성이 추가되면 주석 해제해주세요.
    //public LearnerController(User learner, CourseService courseService, LearnerView learnerView) {
    //    this.learner = learner;
    //    this.courseService = courseService;
    //    this.learnerView = learnerView;
    //}

    /**
     * 학습자 메뉴의 전체 흐름 제어
     * - LearnerView를 통해 메뉴 출력
     * - 입력값에 따라 Service 호출
     */
    public void process() {
        // TODO: LearnerView.showMenu();
        // TODO: 사용자 입력값에 따라 수강 목록 조회 / 수강 신청 등 분기 처리
        System.out.println("학습자 메뉴 출력");
    }
}
