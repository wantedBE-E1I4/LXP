package com.lxp.course.dao;

import com.lxp.course.Enrollment;
import com.lxp.course.EnrollmentStatus;
import com.lxp.course.dto.EnrollmentData;
import com.lxp.course.dto.MyEnrollmentCourseInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    private Connection conn;

    public EnrollmentDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * 특정 사용자가 '수강중'인 강좌 목록을 DB에서 조회하여 반환합니다.
     *
     * @param userId 조회할 사용자의 ID
     * @return 해당 사용자의 '수강중' 상태인 Enrollment 객체 리스트
     */
    public List<Enrollment> findMyCourse(int userId) {

        String sql = "SELECT " +
                "e.enrollment_id, " +
                "e.user_id, " +
                "e.course_id, " +
                "e.progress, " +
                "e.status, " +
                "c.title " +
                "FROM enrollments e JOIN courses c ON e.course_id = c.course_id " +
                "WHERE e.user_id = ?"; // '수강중' 과 '수강완료' 상태값 확인 필요

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

    public int deleteEnrollment(int courseId, int userId) {
        String sql = "DELETE FROM enrollments WHERE course_id = ? AND user_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, userId);

            // 삭제된 행(row) 수를 반환함
            int affectedRows = pstmt.executeUpdate();
            return affectedRows;


        } catch (SQLException e) {
            throw new RuntimeException("Enrollment 삭제 중 오류 발생", e);
        }
    }

    public boolean createEnrollment(int userId, int courseId) {

            String sql = "INSERT INTO enrollments (user_id, course_id, progress, status) VALUES (?, ?, 0, 'IN_PROGRESS')";

            // 2. try-with-resources 로 PreparedStatement 관리
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // 변수명 pstmt로 통일 (선택사항)
                // 3. 첫 번째 ?에 userId 설정
                pstmt.setInt(1, userId);
                // 4. 두 번째 ?에 courseId 설정
                pstmt.setInt(2, courseId);

                // 5. INSERT 쿼리 실행 및 영향받은 행 수 확인
                int affectedRows = pstmt.executeUpdate();

                // 6. 1개 이상의 행이 추가되었으면 true 반환, 아니면 false 반환
                return affectedRows > 0;

            } catch (SQLException e) {
                // 7. 오류 발생 시 메시지 출력 및 false 반환
                System.out.println("Enrollment 생성 중 오류가 발생했습니다."); // 오류 메시지 수정
                e.printStackTrace();
                return false;
            }
    }



    public List<MyEnrollmentCourseInfo> findMyCourseForLearner(int userId) {
        String sql = "SELECT \n" +
                "    e.enrollment_id AS enrollment_id,\n" +
                "    c.course_id AS course_id,\n" +
                "    c.title AS course_title,\n" +
                "    tutor.user_name AS tutor_name,\n" +
                "    e.progress AS progress\n" +
                "FROM enrollments e\n" +
                "JOIN users learner ON e.user_id = learner.user_id\n" +
                "JOIN courses c ON e.course_id = c.course_id\n" +
                "JOIN users tutor ON c.tutor_id = tutor.user_id\n" +
                "WHERE learner.user_id = ?;\n";

        List<MyEnrollmentCourseInfo> myCourses = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    MyEnrollmentCourseInfo enrollment = new MyEnrollmentCourseInfo(
                            rs.getInt("enrollment_id"),
                            rs.getInt("course_id"),
                            rs.getString("course_title"),
                            rs.getString("tutor_name"),
                            rs.getDouble("progress")
                    );
                    myCourses.add(enrollment);
                }
            }
        } catch (SQLException e) {
            System.out.println("내 수강 목록 조회 중 오류가 발생했습니다.");
            e.printStackTrace(); // 오류 내용 출력
        }
        return myCourses;
    }

    public boolean existsByUserAndCourse(int userId, int courseId) {
        String sql = "SELECT COUNT(*) FROM enrollments WHERE user_id = ? AND course_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("중복 수강 확인 중 오류 발생", e);
        }
        return false;
    }

}

