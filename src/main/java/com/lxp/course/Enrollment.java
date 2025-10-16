package com.lxp.course;

import java.util.Date;

// 수강 내역 (학습자-강좌 관계)
public class Enrollment {
    private int enrollmentId;
    private int userId;
    private int courseId;
    private double progress;
    private EnrollmentStatus status;

    private Enrollment(int userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    // Factory Method
    public static Enrollment createEnrollment(int userId, int courseId) {
        return new Enrollment(userId, courseId);
    }
}
