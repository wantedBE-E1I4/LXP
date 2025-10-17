package com.lxp.course.service;

import com.lxp.course.Course;
import com.lxp.course.EnrollmentDAO;
import com.lxp.course.dao.CourseDAO;
import com.lxp.course.dao.CourseDAOtutor;
import com.lxp.course.service.dto.CreateCourseDto;
import com.lxp.lecture.dao.LectureDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private final CourseDAO courseDAO;
    private final CourseDAOtutor courseDAOtutor;
    private final LectureDAO lectureDAO;
    private final EnrollmentDAO enrollmentDAO;

    /**
     * [수정] 생성자에서 CourseDAOtutor를 주입받도록 변경합니다.
     */
    public CourseService(CourseDAO courseDAO, CourseDAOtutor courseDAOtutor, LectureDAO lectureDAO, EnrollmentDAO enrollmentDAO) {
        this.courseDAO = courseDAO;
        this.courseDAOtutor = courseDAOtutor;
        this.lectureDAO = lectureDAO;
        this.enrollmentDAO = enrollmentDAO;
    }

    /**
     * [수정] ID 타입을 int로 변경합니다.
     * @param courseId 삭제할 강좌의 ID
     * @return 삭제 성공 여부
     */
    public boolean deleteCourseById(int courseId) {
        int affectedRows = courseDAOtutor.deleteById(courseId);
        return affectedRows > 0;
    }

    public int openNewCourse(CreateCourseDto dto) {
        Course course = new Course(dto.getTutorId(), dto.getTitle(), dto.getDescription(), dto.getCategory());
        return courseDAO.save(course);
    }

    public List<Object[]> getAllCoursesWithTutorName() {
        try {
            return courseDAO.findAllCoursesWithTutorName();
        } catch (SQLException e) {
            System.out.println("강좌 목록 조회 중 오류가 발생했습니다.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}