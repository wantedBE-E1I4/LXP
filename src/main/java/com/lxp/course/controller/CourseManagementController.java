package com.lxp.course.controller;

import com.lxp.course.Course;
import com.lxp.course.CourseService;
import com.lxp.user.User;
import com.lxp.view.tutor.CourseManagementView;
import com.lxp.view.tutor.DeleteCourseView; // import 추가

import java.util.List;

public class CourseManagementController {
    private final User tutor;
    private final CourseManagementView view = new CourseManagementView();
    private final CourseService courseService = new CourseService();

    public CourseManagementController(User tutor) {
        this.tutor = tutor;
    }

    public void process() {
        while (true) {
            view.show();
            String input = view.getInput();

            switch (input) {
                case "1":
                    System.out.println("강좌 개설 (구현 필요)");
                    break;
                case "2":
                    // ✅ '강좌 폐강' 로직을 처리할 private 메서드 호출
                    deleteCourse();
                    break;
                case "3":
                    System.out.println("강좌-강의 관리 (구현 필요)");
                    break;
                case "Q", "q":
                    System.out.println("이전 메뉴로 돌아갑니다.");
                    return; // while 루프를 종료하고 이전 컨트롤러로 돌아감
                default:
                    System.out.println("⚠️ 잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    /**
     * [추가] 강좌 폐강의 전체 흐름을 담당하는 메서드
     */
    private void deleteCourse() {
        // 1. 현재 강사가 개설한 강좌 목록을 서비스에서 가져옴
        List<Course> myCourses = courseService.getCoursesForTeacher(tutor.getUserId());

        if (myCourses.isEmpty()) {
            System.out.println("폐강할 강좌가 없습니다.");
            return;
        }

        // 2. DeleteCourseView를 통해 사용자에게 목록을 보여주고 입력을 받음
        DeleteCourseView deleteView = new DeleteCourseView();
        int courseIdToDelete = deleteView.getInput(myCourses);

        // 3. 사용자가 유효한 강좌를 선택했는지 확인
        if (courseIdToDelete != -1) {
            // 4. 서비스를 통해 강좌 삭제 요청 (소유권 검증 포함)
            boolean isSuccess = courseService.deleteCourse(courseIdToDelete, tutor.getUserId());

            if (isSuccess) {
                System.out.println("✅ 삭제되었습니다!");
            } else {
                // 이 메시지는 소유권이 없거나, 그 사이 다른 사람이 삭제한 경우에만 나타남
                System.out.println("⚠️ 삭제에 실패했습니다. 강좌 정보를 다시 확인해주세요.");
            }
        } else {
            System.out.println("강좌 폐강을 취소했습니다.");
        }
    }
}