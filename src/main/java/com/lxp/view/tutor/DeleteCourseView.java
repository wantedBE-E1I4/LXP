// src/main/java/com/lxp/view/tutor/DeleteCourseView.java
package com.lxp.view.tutor;

import com.lxp.course.Course;
import com.lxp.view.ViewTemplate;
import java.util.List;
import java.util.Scanner;

public class DeleteCourseView {
    private final Scanner sc = new Scanner(System.in);

    /**
     * 강좌 목록을 보여주고, 사용자가 삭제할 강좌를 선택하도록 합니다.
     * @param courses 보여줄 강좌 목록
     * @return 사용자가 선택한 강좌의 실제 course_id, 취소 시 -1
     */
    public int getInput(List<Course> courses) {
        // 1. 강좌 목록 출력
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < courses.size(); i++) {
            body.append(String.format("%d. %s\n", i + 1, courses.get(i).getTitle()));
        }
        ViewTemplate template = new ViewTemplate("내 강좌 목록", body.toString(), "삭제할 강좌 번호를 입력하세요 (취소: Q)");
        System.out.println(template);

        // 2. 사용자 입력 받기
        System.out.print(">> ");
        String input = sc.nextLine();

        if (input.equalsIgnoreCase("Q")) {
            return -1; // 사용자가 취소를 선택
        }

        try {
            int choice = Integer.parseInt(input);
            // 사용자가 입력한 번호(1부터 시작)를 리스트의 인덱스(0부터 시작)로 변환
            if (choice > 0 && choice <= courses.size()) {
                return courses.get(choice - 1).getCourseId(); // 선택된 강좌의 실제 ID 반환
            }
        } catch (NumberFormatException e) {
            // 숫자가 아닌 값을 입력한 경우
        }

        // 잘못된 번호를 입력한 경우
        return -1;
    }
}