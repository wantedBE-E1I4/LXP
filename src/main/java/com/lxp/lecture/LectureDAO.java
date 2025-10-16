package com.lxp.lecture;

import java.sql.Connection;

public class LectureDAO {
    private Connection conn;

    public LectureDAO(Connection conn) {
        this.conn = conn;
    }
}
