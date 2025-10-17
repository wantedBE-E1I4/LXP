package com.lxp.course;

''

// 수강 내역 (학습자-강좌 관계)
public class Enrollment {

    private int enrollmentId;
    private int userId;
    private int courseId;
    private double progress;
    private EnrollmentStatus status;
    private String title;

    private Enrollment(int userId, int courseId, double progress, int enrollmentId,String title, EnrollmentStatus status) {
        this.userId = userId;
        this.courseId = courseId;
        this.progress = progress;
        this.enrollmentId = enrollmentId;
        this.title = title;
        this.status = status;
    }

    // Factory Method
    public static Enrollment createEnrollment(int enrollmentId, int userId, int courseId,
                                              double progress, EnrollmentStatus status, String title){
        return new Enrollment(userId, courseId, progress, enrollmentId, title, status);

    }
    public int getEnrollmentId() {
        return enrollmentId;
    }
    public int getUserId() {
        return userId;
    }
    public int getCourseId() {
        return courseId;
    }
    public double getProgress() {
        return progress;
    }
    public EnrollmentStatus getStatus() {
        return status;
    }
    public String getTitle() {
        return title;
    }
}


