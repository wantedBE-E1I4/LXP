package com.lxp.course;

import com.lxp.course.dto.CourseData;
import com.lxp.course.dto.EnrollmentData;

import java.util.List;

// 수강 신청/취소 로직
public class EnrollmentService {
    private EnrollmentDAO enrollmentDAO;

    public EnrollmentService(EnrollmentDAO enrollmentDAO) {
        this.enrollmentDAO = enrollmentDAO;
    }

    public List<EnrollmentData> getEnrollmentsByUser(int userId) {
        List<EnrollmentData> response = enrollmentDAO.findByUserId(userId);
        return response;
    }
}
