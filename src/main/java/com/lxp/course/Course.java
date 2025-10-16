package com.lxp.course;

// 강좌 엔티티
public class Course {

    // 필드(데이터)는 보통 private으로 선언됩니다.
    private int courseId;
    private String title;
    private String description;
    private String category;
    private int teacherId;

    // 생성자: 객체를 만들 때 데이터를 초기화합니다.
    public Course(int courseId, String title, String description, String category, int teacherId) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.teacherId = teacherId;
    }

    // --- Getter 메서드들 ---

    public int getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return this.title; // 'this.'는 생략 가능
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getTeacherId() {
        return teacherId;
    }


}
