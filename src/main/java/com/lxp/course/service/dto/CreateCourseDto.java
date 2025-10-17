package com.lxp.course.service.dto;

public class CreateCourseDto {
    private int tutorId;
    private String title;
    private String description;
    private String category;

    public CreateCourseDto(int tutorId, String title, String description, String category) {
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
}
