package com.lxp.course.dto;

public record EnrollmentLecture(
        int enrollmentLectureId,
        int enrollmentId,
        int lectureId,
        String lectureName,
        int lectureOrderNo,
        boolean completed
) {

}