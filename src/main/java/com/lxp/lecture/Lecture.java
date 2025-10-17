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

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    private Lecture(int courseId, String title, int orderNo) {
        this.courseId = courseId;
        this.title = title;
        this.orderNo = orderNo;
    }

    // Factory Method
    public static Lecture createLecture(int courseId, String title,  int orderNo) {
        return new Lecture(courseId, title,  orderNo);
    }

    public static Lecture ofIdCourseAndTitle(int lectureId, int courseId, String title,int orderNo) {
        Lecture l = new Lecture(courseId, title, orderNo);
        l.lectureId = lectureId;
        return l;
    }
}
