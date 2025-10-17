package com.lxp.lecture.service;

import com.lxp.course.Course;
import com.lxp.lecture.dao.LectureDAO;
import com.lxp.lecture.dto.LecturesByCourse;

import java.util.List;

public class LectureService {
    private final LectureDAO lectureDAO;

    public LectureService(LectureDAO lectureDAO) {
        this.lectureDAO = lectureDAO;
    }

}
