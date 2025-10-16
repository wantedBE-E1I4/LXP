package com.lxp.course.dao;

import java.sql.Connection;

// 강좌 데이터 접근
public class CourseDAO {
    private Connection conn;

    public CourseDAO(Connection conn) {
        this.conn = conn;
    }
}
