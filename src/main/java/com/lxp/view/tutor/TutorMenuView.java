// src/main/java/com/lxp/view/tutor/TutorMenuView.java
package com.lxp.view.tutor;

import com.lxp.course.Course;
import com.lxp.course.CourseService; // Service 계층 import
import java.util.List;
import java.util.Scanner;

public class TutorMenuView {

    private final Scanner scanner = new Scanner(System.in);
    // 비즈니스 로직을 처리할 Service 객체를 멤버 변수로 가집니다.
    private final CourseService courseService = new CourseService();

    public void show(int teacherId) { // 로그인한 강사의 ID를 받아옵니다.
        while (true) {
            System.out.println("\n== 강사 메인 메뉴 ==");

            // 1. Service를 통해 해당 강사의 강좌 목록을 DB에서 가져옵니다.
            List<Course> myCourses = courseService.getCoursesForTeacher(teacherId);

            // 2. 가져온 강좌 목록을 화면에 출력합니다.
            if (myCourses.isEmpty()) {
                System.out.println("개설한 강좌가 없습니다.");
            } else {
                System.out.println("== 내 강좌 목록 ==");
                for (int i = 0; i < myCourses.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, myCourses.get(i).getTitle());
                }
            }

            System.out.println("\n== 원하시는 작업의 번호를 입력해주세요! ==");
            System.out.println("1. 강좌-강의 관리");
            System.out.println("2. 새 강좌 개설");
            System.out.println("9. 로그아웃");
            System.out.print(">> ");

            String choice = scanner.nextLine();

            if ("1".equals(choice)) {
                // TODO: 강좌-강의 관리 메뉴로 진입하는 로직
                // 1. 사용자에게 관리할 강좌 번호를 입력받습니다.
                // 2. 입력받은 번호에 해당하는 courseId를 찾습니다.
                // 3. CourseManagementView 객체를 생성하고, courseId를 넘겨주며 show() 메서드를 호출합니다.
                System.out.println("강좌-강의 관리 메뉴로 진입합니다. (구현 필요)");

            } else if ("9".equals(choice)) {
                System.out.println("로그아웃합니다.");
                return; // 현재 메뉴를 종료하고 이전 화면으로 돌아감
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}