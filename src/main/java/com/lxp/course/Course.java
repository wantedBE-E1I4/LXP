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

    // --- Factory Method ---

    /**
     * 새로운 강좌를 '생성'할 때 사용하는 팩토리 메서드
     * @param title 강좌 제목
     * @param description 강좌 설명
     * @param category 카테고리
     * @param teacherId 담당 강사 ID
     * @return 초기값이 설정된 새로운 Course 객체
     */
    public static Course createNewCourse(String title, String description, String category, Long teacherId) {
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setCategory(category);
        course.setTeacherId(teacherId);
        // ID, 생성/수정일, 삭제 플래그는 DB에서 자동으로 처리되므로 설정하지 않습니다.
        return course;
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