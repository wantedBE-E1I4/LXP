package com.lxp.course;

import com.lxp.course.dto.EnrollmentData;

import com.lxp.config.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    private Connection conn;

    public EnrollmentDAO(Connection conn) {
        this.conn = conn;
    }

    public List<EnrollmentData> findByUserId(int userId) {
        String sql = "SELECT c.course_id, c.title, u.user_name FROM enrollments as e, courses as c, users as u WHERE e.course_id = c.course_id AND c.tutor_id = u.user_id AND e.user_id = ?";
        List<EnrollmentData> response = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                EnrollmentData data = new EnrollmentData(
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getString("user_name")
                );
                response.add(data);
            }

            return response;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
/**
 * 특정 사용자가 '수강중'인 강좌 목록을 DB에서 조회하여 반환합니다.
 * @param userId 조회할 사용자의 ID
 * @return 해당 사용자의 '수강중' 상태인 Enrollment 객체 리스트
 */
        public List<Enrollment> findMyCourse (int userId){

            String sql = "SELECT " +
                    "e.enrollment_id, " +
                    "e.user_id, " +
                    "e.course_id, " +
                    "e.status, " +
                    "c.title " +
                    "FROM enrollments e JOIN courses c ON e.course_id = c.course_id " +
                    "WHERE e.user_id = ? AND e.status = '수강중'"; // '수강중' 상태값 확인 필요

            List<Enrollment> myCourses = new ArrayList<>();

            // try-with-resources (Connection은 App.java에서 관리하므로 여기서 닫지 않음)
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, userId); // 첫 번째 ? 자리에 userId 설정

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        // DB 조회 결과를 Enrollment DTO 객체로 변환
                        // Enrollment 클래스에 title을 저장할 필드와 생성자/Setter가 필요합니다.
                        Enrollment enrollment = Enrollment.createEnrollment( // 생성 방식 확인 필요
                                rs.getInt("enrollment_id"),
                                rs.getInt("user_id"),
                                rs.getInt("course_id"),
                                rs.getDouble("progress"),
                                EnrollmentStatus.valueOf(rs.getString("status")),
                                rs.getString("title")
                        );
                        // enrollment.setEnrollmentId(rs.getInt("enrollment_id")); // ID 설정
                        // enrollment.setStatus(EnrollmentStatus.valueOf(rs.getString("status"))); // 상태 설정 (Enum 사용 시)
                        // enrollment.setCourseTitle(rs.getString("title")); // 강좌 제목 설정 (필드 추가 필요)

                        myCourses.add(enrollment);
                    }
                }
            } catch (SQLException e) {
                System.out.println("내 수강 목록 조회 중 오류가 발생했습니다.");
                e.printStackTrace(); // 오류 내용 출력
            }
            return myCourses;
        }
}

