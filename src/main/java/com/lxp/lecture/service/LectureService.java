package com.lxp.lecture.service;

import com.lxp.course.Course;
import com.lxp.lecture.Lecture;
import com.lxp.lecture.dao.LectureDAO;
import com.lxp.lecture.dto.LecturesByCourse;

import java.util.List;

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

    /**
     * 강의를 생성합니다.
     * @param lectureTitle
     * @param courseId
     */
    public void createLecture(String lectureTitle, int courseId) {
        Lecture lecture = new Lecture(courseId, lectureTitle);
        lectureDAO.save(lecture);
    }

    /**
     * [추가] 제목을 기반으로 강의를 삭제하는 비즈니스 로직입니다.
     * @param lectureTitle 삭제할 강의의 제목
     * @param courseId 해당 강의가 속한 강좌의 ID
     * @return 삭제 성공 여부
     */
    public boolean deleteLectureByTitle(String lectureTitle, int courseId) {
        int affectedRows = lectureDAO.deleteByTitleAndCourseId(lectureTitle, courseId);
        return affectedRows > 0;
    }



}
