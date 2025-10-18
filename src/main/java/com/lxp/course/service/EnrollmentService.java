package com.lxp.course.service;

import com.lxp.course.Enrollment;
import com.lxp.course.dao.EnrollmentDAO;
import com.lxp.course.dto.EnrollmentData;
import com.lxp.course.dto.MyEnrollmentCourseInfo;

import java.util.List;

// 수강 신청/취소 로직
public class EnrollmentService {
    private EnrollmentDAO enrollmentDAO;

    public EnrollmentService(EnrollmentDAO enrollmentDAO) {
        this.enrollmentDAO = enrollmentDAO;
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
}
