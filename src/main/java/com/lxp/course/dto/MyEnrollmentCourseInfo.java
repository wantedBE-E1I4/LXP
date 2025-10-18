package com.lxp.course.dto;

public record MyEnrollmentCourseInfo(
        int enrollmentId,
        int courseId,
        String courseTitle,
        String tutorName,
        double progress

) {

}
