package com.lxp;

import com.lxp.common.RoleController;
import com.lxp.config.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try {
            // Connection 생성
            Connection conn = DatabaseManager.getConnection();
            // Test User 생성
            DatabaseManager.initTestUsers(conn);
            System.out.println("✅ 데이터베이스 연결 성공!");
            System.out.println("\n\n== WELCOME! 🥳 ==");

            RoleController roleController = new RoleController(conn);
            roleController.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
