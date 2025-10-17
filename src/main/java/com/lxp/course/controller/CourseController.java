package com.lxp.course.controller;

import com.lxp.course.Course;
import com.lxp.course.service.CourseService;
import com.lxp.course.EnrollmentService;
import com.lxp.course.service.dto.CreateCourseDto;
import com.lxp.lecture.service.LectureService;
import com.lxp.user.User;
import com.lxp.user.service.UserService;

import java.util.List;
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
            System.out.println("1. 강좌 개설");
            System.out.println("2. 강좌 삭제");
            System.out.println("0. 이전 메뉴로 돌아가기");
            System.out.print(">> ");
            String subMenuChoice = scanner.nextLine(); // [핵심] Controller가 직접 입력을 받음

            if ("1".equals(subMenuChoice)) {
                this.createCourse(scanner);
            } else if ("2".equals(subMenuChoice)) {
                this.deleteCourse(scanner);
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

    //강좌 수강신청 (강좌단위)
    public void enrollCourse(Scanner scanner) {
    }

    //강사 - 강좌 개설
    public void createCourse(Scanner scanner) {
        // 사용자 입력값 받기
        System.out.println("\n--- 개설할 강좌의 정보를 입력해주세요. ---\n");
        System.out.print("제목 : ");
        String title = scanner.nextLine();
        System.out.print("설명 : ");
        String description = scanner.nextLine();
        System.out.print("카테고리 : ");
        String category = scanner.nextLine();

        // 강사 조회
        User currentTutor = this.userService.getCurrentTutor();

        // DTO 생성
        CreateCourseDto dto = new CreateCourseDto(currentTutor.getId(), title, description, category);

        // 강사의 강좌 개설
        int createdCourseId = courseService.openNewCourse(dto);

        if (createdCourseId == 0) {
            System.out.println("--- 강좌 개설 중 오류가 발생했습니다! 관리자에게 문의해주세요. ---");
        } else {
            System.out.println("--- 강좌가 개설되었습니다! ---");
        }
    }
}