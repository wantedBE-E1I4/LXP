package com.lxp.user;

import java.sql.Connection;
import java.sql.SQLException;

// 사용자 관련 비즈니스 로직
public class UserService {

    private final UserDAO userDAO;

    public UserService(Connection conn) {
        this.userDAO = new UserDAO(conn);
    }

    public User findUserByRole(Role role) {
        try {
            return userDAO.getUserByRole(role);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
