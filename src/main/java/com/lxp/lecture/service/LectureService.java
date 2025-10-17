package com.lxp.lecture.service;

import com.lxp.lecture.Lecture;
import com.lxp.lecture.dao.LectureDAO;

import java.sql.SQLException;
import java.util.List;

public class LectureService {
    private final LectureDAO lectureDAO;

    public LectureService(LectureDAO lectureDAO) {
        this.lectureDAO = lectureDAO;
    }

    public List<Lecture> getLectureList(int courseId) {
        return lectureDAO.findByCourseId(courseId);
    }
}
