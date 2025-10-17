package com.lxp.course;

import java.time.LocalDateTime;

public class Course {
    private int courseId;
    private int tutorId;
    private String title;
    private String description;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean delFlag;

    public Course() {}

    public Course(int courseId, String title, String description, String category, int tutorId, LocalDateTime createdAt, LocalDateTime updatedAt, boolean delFlag) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.tutorId = tutorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.delFlag = delFlag;
    }

    public Course(int tutorId, String title, String description, String category) {
        this.tutorId = tutorId;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    // Factory Method
    public static Course createCourse(int tutorId, String title) {
        Course course = new Course();
        course.setTutorId(tutorId);
        course.setTitle(title);
        return course;
    }

    // --- Getter and Setter (기존과 동일) ---
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getTutorId() { return tutorId; }
    public void setTutorId(int tutorId) { this.tutorId = tutorId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public boolean isDelFlag() { return delFlag; }
    public void setDelFlag(boolean delFlag) { this.delFlag = delFlag; }
}