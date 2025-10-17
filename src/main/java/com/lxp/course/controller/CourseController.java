package com.lxp.course.controller;

import com.lxp.course.Course;
import com.lxp.course.dto.CourseWithStatusDTO;
import com.lxp.course.service.CourseService;
import com.lxp.course.EnrollmentService;
import com.lxp.lecture.service.LectureService;

import java.util.List;
import java.util.Scanner;

public class CourseController {
    private CourseService courseService;
    private LectureService lectureService;
    private EnrollmentService enrollmentService;

    public CourseController(CourseService courseService, LectureService lectureService, EnrollmentService enrollmentService) {
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.enrollmentService = enrollmentService;
    }

    //모든 강좌 출력
    public void showAllCourses() {
        ;
        // 1. Service에게 데이터 요청
        List<Object[]> courseDataList = courseService.getAllCoursesWithTutorName();

        System.out.println("\n== 전체 강좌 목록 ==");
        if (courseDataList.isEmpty()) {
            System.out.println("개설된 강좌가 없습니다.");
            return;
        }

        // 2. 받은 데이터를 for문으로 출력
        int index = 1;
        for (Object[] data : courseDataList) {
            Course course = (Course) data[0]; // Course 객체 추출
            String tutorName = (String) data[1]; // 강사 이름 추출

            // Course 객체에 getTitle() 메서드가 있어야 합니다.
            System.out.printf("%d. %s - %s\n", index, course.getTitle(), tutorName);
            index++;
        }

    }

    //내 강좌 목록 출력
    public void showMyCourses() {
        List<Object[]> courseDataList = courseService.getAllCoursesWithTutorName();

        System.out.println("\n== 전체 강좌 목록 ==");
        if (courseDataList.isEmpty()) {
            System.out.println("개설된 강좌가 없습니다.");
            return;
        }

        // 2. 받은 데이터를 for문으로 출력
        int index = 1;
        for (Object[] data : courseDataList) {
            Course course = (Course) data[0]; // Course 객체 추출
            String tutorName = (String) data[1]; // 강사 이름 추출

            // Course 객체에 getTitle() 메서드가 있어야 합니다.
            System.out.printf("%d. %s - %s\n", index, course.getTitle(), tutorName);
            index++;
        }

    }

    public void enrollCourse(Scanner scanner, int userId) {
        // Service 호출 시 Scanner 없이 userId만 전달
        List<CourseWithStatusDTO> courses = courseService.getAllCoursesWithStatus(userId);

        // 1. 가져온 courses 목록을 화면에 출력 (for문 사용)
        System.out.println("\n== 전체 강좌 목록 (신청 상태) ==");
        if (courses.isEmpty()) {
            System.out.println("개설된 강좌가 없습니다.");
            return; // 수강 신청할 강좌가 없으므로 메서드 종료
        }
        int index = 1;
        for (CourseWithStatusDTO courseDTO : courses) {
            String status = courseDTO.isEnrolled() ? " - 수강중" : " - 수강하지 않음";

            System.out.printf("%d. %sby %s%s\n",
                    index,
                    courseDTO.getTitle(),     // getTitle() Getter 사용
                    courseDTO.getTutorName(), // getTutorName() Getter 사용
                    status);
            index++;
        }

        // 2. 사용자에게 수강할 강좌 번호 입력받기
        System.out.println("\n== 수강할 강좌의 번호를 입력해주세요 ==");
        System.out.print(">> ");
        String input = scanner.nextLine(); // 여기서 scanner 사용


        }

        public void createCourse (Scanner scanner){
        }
    }

