package com.lxp.core;

import java.util.List;
import java.util.Scanner;

// NOTE: 삭제 예정
/*
public class MainView extends BaseView {

    @Override
    public void show() {
        // Header
        printHeader("LXP 서비스");

        // Body
        List<String> users = List.of("학습자", "강사");
        printBody(users);

        // Close Comments
        closeComment();

        // IO
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        switch (input) {
            case "1" -> {
                // TODO : 학습자 페이지로 이동
            }
            case "2" -> {
                // TODO : 강사 페이지로 이동
            }
            case "q", "Q" -> System.out.println("프로그램을 종료합니다.");
            default -> {
                System.out.println("잘못된 입력입니다.");
                pauseAndReturn();
            }
        }
    }

    private void pauseAndReturn() {
        System.out.println("계속하려면 Enter를 누르세요...");
        new Scanner(System.in).nextLine();
        show(); // 다시 메인메뉴
    }
}
*/