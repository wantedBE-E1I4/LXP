package com.lxp.course.service;

import com.lxp.course.Enrollment;
import com.lxp.course.dao.LectureProcessDAO;
import com.lxp.course.dto.EnrollmentLecture;
import com.lxp.course.dao.EnrollmentDAO;
import com.lxp.course.dto.MyEnrollmentCourseInfo;

import java.util.List;

// 수강 신청/취소 로직
public class EnrollmentService {
    private EnrollmentDAO enrollmentDAO;
    private LectureProcessDAO lectureProcessDAO;

    public EnrollmentService(EnrollmentDAO enrollmentDAO, LectureProcessDAO lectureProcessDAO) {
        this.enrollmentDAO = enrollmentDAO;
        this.lectureProcessDAO = lectureProcessDAO;
    }

    public List<Enrollment> getEnrollmentsByUser(int userId) {
        return enrollmentDAO.findMyCourse(userId);
    }

    public int deleteEnrollment(int courseId, int userId) {
        return enrollmentDAO.deleteEnrollment(courseId, userId);
    }
    public List<MyEnrollmentCourseInfo> getMyEnrollments(int userId) {
        return enrollmentDAO.findMyCourseForLearner(userId);
    }

    /**
     *  수강중인 강좌의 강의 출력 메서드
    * */
    public List<EnrollmentLecture> getEnrollmentLectures(int enrollmentCourseId) {
        return lectureProcessDAO.findByEnrollmentId(enrollmentCourseId);
    }

    /**
     * 수강 중인 강좌의 강의 상태값 변경 메서드
    * */
    public int takeEnrollmentLecture(int courseId, int lectureId) {
        return lectureProcessDAO.setEnrollmentLectureCompleted(courseId, lectureId);
    }
}
