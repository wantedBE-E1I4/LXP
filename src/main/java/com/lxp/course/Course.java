package com.lxp.course;

import java.util.Date;

// 강좌 엔티티
public class Course {
    private int courseId;
    private int tutorId; // TODO : DB에서는 teacherId로 정의 되어 있는 듯 합니다.
    private String title;
    private String description;
    private String category;
    private int delFlg;

    private Course(int tutorId, String title) {
        this.tutorId = tutorId;
        this.title = title;
    }

    // Factory Method
    public static Course createCourse(int tutorId, String title) {
        return new Course(tutorId, title);
    }

}
