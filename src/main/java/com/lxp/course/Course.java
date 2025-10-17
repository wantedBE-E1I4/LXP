package com.lxp.course;

import java.util.Date;

// 강좌 엔티티
public class Course {
    private int courseId;
    private int tutorId;
    private String title;
    private String description;
    private String category;
    private int delFlg;

    private Course(int tutorId, String title) {
        this.tutorId = tutorId;
        this.title = title;
    }

    private Course(int tutorId, String title, String description, String category) {
        this.tutorId = tutorId;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public int getTutorId() {
        return tutorId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    // Factory Method
    public static Course createCourse(int tutorId, String title, String description, String category) {
        return new Course(tutorId, title, description, category);
    }

}
