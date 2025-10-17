package com.lxp.course.controller;

import com.lxp.course.Course;
import com.lxp.course.EnrollmentService;
import com.lxp.course.service.CourseService;
import com.lxp.lecture.service.LectureService;

import java.util.List;
import java.util.Scanner;

public class CourseController {
    private final CourseService courseService;
    private final LectureService lectureService;
    private final EnrollmentService enrollmentService;

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
               // this.createCourse(scanner); // 자신의 다른 메서드를 호출
            } else if ("2".equals(subMenuChoice)) {
                this.deleteCourse(scanner); // 자신의 다른 메서드를 호출
            } else if ("0".equals(subMenuChoice)) {
                return;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }


    public void showAllCourses() {;
        // 1. Service에게 데이터 요청
        List<Object[]> courseDataList = courseService.getAllCoursesWithTutorName();

        System.out.println("\n== 전체 강좌 목록 ==");
        if (courseDataList.isEmpty()) {
            System.out.println("개설된 강좌가 없습니다.");
            return;
        }

        // 2. 받은 데이터를 for문으로 출력
        int index = 1;
        for (Object[] data : courseDataList) {
            Course course = (Course) data[0]; // Course 객체 추출
            String tutorName = (String) data[1]; // 강사 이름 추출

            // Course 객체에 getTitle() 메서드가 있어야 합니다.
            System.out.printf("%d. %s - %s\n", index, course.getTitle(), tutorName);
            index++;
        }

    }

    /**
     * [복원 및 수정] 강좌 삭제 기능을 복원합니다.
     */
    public void deleteCourse(Scanner scanner) {
        // 1. 먼저 사용자에게 삭제 가능한 강좌 목록을 보여줍니다.
        showAllCourses();

        try {
            // 2. 사용자로부터 삭제할 강좌의 ID를 입력받습니다.
            System.out.print("삭제할 강좌의 ID를 입력하세요: ");
            String input = scanner.nextLine();
            int courseIdToDelete = Integer.parseInt(input); // [수정] Long -> int

            // 3. Service에게 실제 삭제 처리를 위임합니다.
            boolean isDeleted = courseService.deleteCourseById(courseIdToDelete);

            // 4. 처리 결과를 사용자에게 알려줍니다.
            if (isDeleted) {
                System.out.println("✅ 강좌가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("❌ 해당 ID의 강좌를 찾을 수 없거나 이미 삭제된 강좌입니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❗ 잘못된 입력입니다. 숫자를 입력해주세요.");
        }
    }

    public void enrollCourse(Scanner scanner) {
    }

    // ... (기타 메서드는 기존과 동일)
}