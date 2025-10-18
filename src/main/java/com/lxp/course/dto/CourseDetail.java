package com.lxp.course.dto;

import java.util.Date;

public record CourseDetail(String title, String description, String category, String tutorName, Date createdAt, Date updatedAt) {
}
