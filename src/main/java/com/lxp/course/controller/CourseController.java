package com.lxp.course.controller;

import com.lxp.course.Course;
import com.lxp.course.service.CourseService;
import com.lxp.course.EnrollmentService;
import com.lxp.lecture.service.LectureService;

import java.util.List;
import java.util.Scanner;

public class CourseController {
   // Scanner scanner = new Scanner(System.in);
    private CourseService courseService;
    private LectureService lectureService;
    private EnrollmentService enrollmentService;

    public CourseController(CourseService courseService, LectureService lectureService, EnrollmentService enrollmentService) {
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.enrollmentService = enrollmentService;
    }

    public void manageCourses(Scanner scanner) {
        while (true) {
            System.out.println("\n--- [강좌 관리 메뉴] ---");
            System.out.println("1. 신규 강좌 개설");
            System.out.println("2. 강좌 삭제");
            System.out.println("0. 이전 메뉴로 돌아가기");
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
        System.out.println("\n--- 📖 전체 강좌 목록 ---");
        // 1. Service에게 모든 강좌 데이터를 요청합니다.
        List<Course> courses = courseService.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("개설된 강좌가 없습니다.");
        } else {
            // 2. 받아온 데이터를 사용자가 보기 좋게 출력합니다.
            courses.forEach(course ->
                    System.out.printf("ID: %d, 제목: %s\n", course.getId(), course.getTitle())
            );
        }
        System.out.println("--------------------");
    }
    //강좌 수강신청 (강좌단위)
    public void enrollCourse(Scanner scanner) {
    }
    //강사 - 강좌 개설
    public void createCourse(Scanner scanner) {
    }
    //강사 - 강좌 삭제
    public void deleteCourse(Scanner scanner) {
        // 1. 먼저 사용자에게 삭제 가능한 강좌 목록을 보여줍니다.
        showAllCourses();

        try {
            // 2. 사용자로부터 삭제할 강좌의 ID를 입력받습니다.
            System.out.print("삭제할 강좌의 ID를 입력하세요: ");
            String input = scanner.nextLine();
            Long courseIdToDelete = Long.parseLong(input);

            // 3. Service에게 실제 삭제 처리를 위임합니다.
            boolean isDeleted = courseService.deleteCourseById(courseIdToDelete);

            // 4. 처리 결과를 사용자에게 알려줍니다.
            if (isDeleted) {
                System.out.println("✅ 강좌가 성공적으로 삭제되었습니다.");
            } else {
                // Service에서 해당 ID의 강좌를 찾지 못했거나 삭제에 실패한 경우
                System.out.println("❌ 해당 ID의 강좌를 찾을 수 없거나 삭제에 실패했습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❗ 잘못된 입력입니다. 숫자를 입력해주세요.");
        }
    }


    }


