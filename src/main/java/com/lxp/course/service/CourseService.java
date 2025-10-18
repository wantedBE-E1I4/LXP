package com.lxp.course.service;

import com.lxp.course.Course;
import com.lxp.course.Enrollment;
import com.lxp.course.EnrollmentDAO; // 필요한 DAO import
import com.lxp.course.dao.CourseDAO;
import com.lxp.course.dto.CourseWithStatusDTO;
import com.lxp.lecture.dao.LectureDAO; // 필요한 DAO import
import com.lxp.course.EnrollmentStatus;
import com.lxp.course.dao.EnrollmentDAO;
import com.lxp.course.dao.CourseDAO;
import com.lxp.course.dao.CourseDAOtutor;
import com.lxp.course.service.dto.CreateCourseDto;
import com.lxp.lecture.dao.LectureDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        /*
         * 사용자의 수강 상태를 포함한 모든 강좌 목록을 조회합니다.
         * @param userId 현재 로그인한 사용자의 ID
         * @return 각 요소가 CourseWithStatusDTO 형태인 리스트
         */
         public List<CourseWithStatusDTO> getAllCoursesWithStatus (int userId) {

            List<CourseWithStatusDTO> resultList = new ArrayList<>();
            Set<Integer> enrolledCourseIds = new HashSet<>();

            try {
                // 1. 현재 학생이 수강 중인 강좌 목록 가져오기
                List<Enrollment> myCourses = enrollmentDAO.findMyCourse(userId);

                // 2. 수강 중인 강좌 ID만 Set에 저장
                for (Enrollment enrollment : myCourses) {
                    enrolledCourseIds.add(enrollment.getCourseId());
                }

                // 3. 모든 강좌 목록과 강사 이름 가져오기
                List<Object[]> allCoursesData = courseDAO.findAllCoursesWithTutorName();

                // 4. 모든 강좌 목록을 순회하며 DTO 생성
                for (Object[] data : allCoursesData) {
                    Course course = (Course) data[0];
                    String tutorName = (String) data[1];
                    int currentCourseId = course.getCourseId();

                    // 5. Set을 사용하여 현재 학생의 수강 여부 확인
                    boolean isEnrolled = enrolledCourseIds.contains(currentCourseId);

                    // 6. 최종 결과를 담을 DTO 객체 생성 및 리스트에 추가
                    resultList.add(new CourseWithStatusDTO(currentCourseId, course.getTitle(), tutorName, isEnrolled)); // getTitle() Getter 필요
                }

            } catch (SQLException e) {
                System.out.println("수강 상태 포함 강좌 목록 조회 중 오류 발생");
                e.printStackTrace();
                // 오류 발생 시 빈 리스트 반환 (또는 예외를 다시 던질 수도 있음)
            }

            return resultList;
        // 최종 결과 리스트 반환
        }
    }








