package com.lxp.course;

import java.sql.Connection;

public class EnrollmentDAO {
    private Connection conn;

    public EnrollmentDAO(Connection conn) {
        this.conn = conn;
    }
}
