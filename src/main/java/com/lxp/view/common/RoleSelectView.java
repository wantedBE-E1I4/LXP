package com.lxp.view.common;

import com.lxp.common.Displayable;
import com.lxp.view.ViewTemplate;

import java.util.Scanner;

public class RoleSelectView implements Displayable {
    private final Scanner sc = new Scanner(System.in);

    public void show() {
        String header = "LXP 서비스";
        String body = "1. 학습자\n2. 강사\n";
        String footer = "원하는 번호를 입력하세요.";

        ViewTemplate template = new ViewTemplate(header, body, footer);
        System.out.println(template);
    }

    public String getInput() {
        System.out.print(">> ");
        return sc.nextLine();
    }
}
