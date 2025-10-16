package com.lxp.user.service;

import com.lxp.user.Role;
import com.lxp.user.User;
import com.lxp.user.dao.UserDAO;

import java.sql.SQLException;

// 사용자 관련 비즈니스 로직
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
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
