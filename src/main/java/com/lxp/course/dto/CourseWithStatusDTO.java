package com.lxp.course.dto;



public class CourseWithStatusDTO {

    private int courseId;
    private String title;
    private String tutorName;
    private boolean isEnrolled;


        //모든 필드 값 초기화 : 생성자
    public CourseWithStatusDTO(int courseId, String title,  String tutorName, boolean isEnrolled) {
        this.courseId = courseId;
        this.title = title;
        this.tutorName = tutorName;
        this.isEnrolled = isEnrolled;
    }
    public int getCourseId() {
        return courseId;
    }
    public String getTitle() {
        return title;
    }
    public String getTutorName() {
        return tutorName;
    }
    public boolean isEnrolled() {
        return isEnrolled;
    }
}
    

