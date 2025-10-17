package com.lxp.course.controller;

import com.lxp.course.Course;
import com.lxp.course.service.CourseService;
import com.lxp.course.EnrollmentService;
import com.lxp.lecture.service.LectureService;
import com.lxp.user.User;
import com.lxp.user.service.UserService;

import java.util.Scanner;

public class CourseController {
    Scanner scanner = new Scanner(System.in);

    private CourseService courseService;
    private LectureService lectureService;
    private EnrollmentService enrollmentService;
    private UserService userService;

    public CourseController(
            CourseService courseService,
            LectureService lectureService,
            EnrollmentService enrollmentService,
            UserService userService
    ) {
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.enrollmentService = enrollmentService;
        this.userService = userService;
    }

    public void manageCourses(Scanner scanner) {
        while (true) {
            System.out.println("\n--- [강좌 관리 메뉴] ---");
            System.out.println("0. 이전 메뉴로 돌아가기");
            System.out.println("1. 강좌 개설");
            System.out.println("2. 강좌 폐강");
            System.out.print(">> ");
            String subMenuChoice = scanner.nextLine(); // [핵심] Controller가 직접 입력을 받음

            if ("1".equals(subMenuChoice)) {
                this.createCourse(scanner); // 자신의 다른 메서드를 호출
            } else if ("2".equals(subMenuChoice)) {
                this.deleteCourse(scanner); // 자신의 다른 메서드를 호출
            } else if ("0".equals(subMenuChoice)) {
                return;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    //강좌들 조회
    public void showAllCourses() {
    }

    //강좌 수강신청 (강좌단위)
    public void enrollCourse(Scanner scanner) {
    }

    //강사 - 강좌 개설
    public Course createCourse(Scanner scanner) {
        // 사용자 입력값 받기
        System.out.println("\n--- 개설할 강좌의 정보를 입력해주세요. ---\n");
        System.out.println("제목 : ");
        String title = scanner.nextLine();
        System.out.println("설명 : ");
        String description = scanner.nextLine();
        System.out.println("카테고리 : ");
        String category = scanner.nextLine();

        // 강사 조회
        User currentTutor = this.userService.getCurrentTutor();

        // 강사의 강좌 개설
        Course createdCourse = courseService.openNewCourse(currentTutor.getId(), );

        // 새로운 강좌 반환
        return createdCourse;
    }

    //강사 - 강좌 삭제
    public void deleteCourse(Scanner scanner) {
    }
}