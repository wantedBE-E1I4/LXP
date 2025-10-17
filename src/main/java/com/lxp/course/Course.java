package com.lxp.course;

// 강좌 엔티티
public class Course {
    private int courseId;
    private int tutorId;
    private String title;
    private String description;
    private String category;
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
    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
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



    }

