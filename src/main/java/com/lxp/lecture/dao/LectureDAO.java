package com.lxp.lecture.dao;

import java.sql.Connection;

public class LectureDAO {
    private Connection conn;

    public LectureDAO(Connection conn) {
        this.conn = conn;
    }
}
