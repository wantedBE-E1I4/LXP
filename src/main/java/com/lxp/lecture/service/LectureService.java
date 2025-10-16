package com.lxp.lecture.service;

import com.lxp.lecture.dao.LectureDAO;

public class LectureService {
    private final LectureDAO lectureDAO;

    public LectureService(LectureDAO lectureDAO) {
        this.lectureDAO = lectureDAO;
    }
}
