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

    public List<LecturesByCourse> getLecturesByCourse(int courseId) {

        List<LecturesByCourse> response = lectureDAO.findByCourseId(courseId);
        return response;
    }
}
