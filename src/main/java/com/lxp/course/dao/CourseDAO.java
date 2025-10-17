package com.lxp.course.dao;


import com.lxp.config.DatabaseManager;
import com.lxp.course.Course; // Course 클래스 import
import com.lxp.user.User;   // User 클래스 import (강사 이름 때문에 필요)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 강좌 데이터 접근 객체 (courses 테이블 담당)
 */
public class CourseDAO {

    public CourseDAO(Connection conn) {

        this.conn = conn;
    }
    /**
     * 모든 강좌 목록과 해당 강좌의 강사 이름을 DB에서 조회하여 반환합니다.
     * @return 각 요소가 [Course 객체, 강사 이름] 형태인 List<Object[]>
     * @throws SQLException DB 조회 오류 발생 시
     */
    public List<Object[]> findAllCoursesWithTutorName() throws SQLException {
        // L-01 기능을 위해 JOIN하는 SQL 쿼리
        String sql = "SELECT c.course_id, c.title, c.tutor_id, u.user_name " + // 필요한 컬럼 추가
                "FROM courses c JOIN users u ON c.tutor_id = u.user_id"; // tutorId로 JOIN (DB 컬럼명 확인!)

        List<Object[]> resultList = new ArrayList<>();

        // try-with-resources (Connection은 App.java에서 관리하므로 여기서 닫지 않음)
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Course 객체 생성 (DB 설계에 맞는 생성자나 팩토리 메서드 필요)
                Course course = Course.createCourse(
                        rs.getInt("tutor_id"), // DB 설계 확인
                        rs.getString("title")
                );

                String tutorName = rs.getString("user_name");

                // Course 객체와 강사 이름을 배열로 묶어서 리스트에 추가
                resultList.add(new Object[]{course, tutorName});
            }
        }
        // catch 블록은 Service 계층에서 처리하도록 SQLException을 던집니다.
        return resultList;
    }
}
