package com.lxp.course;

import com.lxp.lecture.Lecture;

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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(int delFlg) {
        this.delFlg = delFlg;
    }

}
