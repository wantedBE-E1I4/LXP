package com.lxp.course;

// 수강 신청/취소 로직
public class EnrollmentService {
    private EnrollmentDAO enrollmentDAO;

    public EnrollmentService(EnrollmentDAO enrollmentDAO) {
        this.enrollmentDAO = enrollmentDAO;
    }
}
