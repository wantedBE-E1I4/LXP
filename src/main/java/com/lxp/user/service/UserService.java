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

    // NOTE: 사용자 인증 로직이 포함되지 않고, 사용자 조회를 위한 임시 함수입니다.
    // 일반적인 사용자 조회 방식이 아님을 유의해주세요~!
    public User getCurrentTutor() {
        return userDAO.getUserByRole(Role.TUTOR);
    }
}
