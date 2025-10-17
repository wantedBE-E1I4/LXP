package com.lxp.lecture;

import java.util.Date;

public class Lecture {
    private int lectureId;
    private int courseId;
    private String title;
    private String content;
    private int orderNo;
    private int delFlg;

    public int getLectureId() {
        return lectureId;
    }

    public String getTitle() {
        return title;
    }

    private Lecture(int courseId, String title) {
        this.courseId = courseId;
        this.title = title;
    }

    // Factory Method
    public static Lecture createLecture(int courseId, String title) {
        return new Lecture(courseId, title);
    }

    public static Lecture ofIdCourseAndTitle(int lectureId, int courseId, String title) {
        Lecture l = new Lecture(courseId, title);
        l.lectureId = lectureId;
        return l;
    }
}
