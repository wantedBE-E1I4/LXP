package com.lxp.course;

import java.time.LocalDateTime; // Java 8 이상에서 날짜/시간을 다루는 표준 클래스

/**
 * 강좌 엔티티 (courses 테이블과 매핑)
 */
public class Course {
    private Long courseId;
    private String title;
    private String description;
    private String category;
    private Long teacherId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean delFlag; // DB의 del_flag (BOOLEAN)와 매핑

    // --- 생성자 ---

    /**
     * 기본 생성자
     */
    public Course() {}

    /**
     * DB에서 조회한 데이터를 객체로 매핑하기 위한 전체 필드 생성자
     */
    public Course(Long courseId, String title, String description, String category, Long teacherId, LocalDateTime createdAt, LocalDateTime updatedAt, boolean delFlag) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.teacherId = teacherId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.delFlag = delFlag;
    }


    // --- Getter and Setter ---

    public Long getId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }
}