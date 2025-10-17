package com.lxp.lecture.dao;

import com.lxp.lecture.dto.LecturesByCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LectureDAO {
    private Connection conn;

    public LectureDAO(Connection conn) {
        this.conn = conn;
    }

}
