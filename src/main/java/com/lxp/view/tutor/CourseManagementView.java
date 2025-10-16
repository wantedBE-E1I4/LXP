package com.lxp.view.tutor;

import com.lxp.common.Displayable;
import com.lxp.view.ViewTemplate;

import java.util.Scanner;
//1. 강사는 강사 메인 메뉴 화면에서 ‘강좌 관리’ 항목을 선택합니다.
//2. 시스템은 입력된 값이 유효한 번호(1, Q) 인지 검증합니다.
//3. 입력이 ‘1(강좌 관리)’일 경우, 강좌 관리 메뉴 화면을 표시합니다.
//강좌 관리 메뉴 화면에는 다음 항목이 표시됩니다.
//• 1. 강좌 개설
//• 2. 강좌 폐강
//• 3. 강좌-강의 관리
//• Q. 종료
//4. 시스템은 강사가 다음 기능을 선택할 때까지 입력 대기 상태로 전환합니다.
//        5. 만약 잘못된 번호나 문자를 입력하면, 시스템은 "⚠️ 유효하지 않은 입력입니다. 다시 입력해주세요." 라는 오류 메시지를 출력하고 강좌 관리 메뉴 화면을 다시 표시합니다.
// 강좌 관리 메뉴
public class CourseManagementView implements Displayable {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void show() {
        String header = "강좌 관리";
        String body = "1. 강좌 개설\n2. 강좌 폐강\n3. 강좌-강의 관리";
        String footer = "원하는 번호를 입력하세요.";

        ViewTemplate template = new ViewTemplate(header, body, footer);
        System.out.println(template);
    }

    public String getInput() {
        System.out.print(">> ");
        return sc.nextLine();
    }
}
