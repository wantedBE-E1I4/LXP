// src/main/java/com/lxp/App.java
package com.lxp;

import com.lxp.user.UserDAO;

public class App {
    public static void main(String[] args) {
        System.out.println("LXP 서비스를 시작합니다.");

        // 1. 데이터베이스 작업을 처리할 DAO 객체를 생성합니다.
        UserDAO userDAO = new UserDAO();

        // 2. DAO의 메서드를 호출하여 데이터베이스의 모든 사용자 정보를 출력합니다.
        userDAO.printAllUsers();

        System.out.println("\nLXP 서비스를 종료합니다.");

        // TODO: 향후 이곳에 MainMenu를 호출하는 코드를 추가하게 됩니다.
        // MainMenu mainMenu = new MainMenu();
        // mainMenu.show();
    }
}