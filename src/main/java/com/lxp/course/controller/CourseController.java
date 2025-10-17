package com.lxp.course.controller;

import com.lxp.course.Course;
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
    public void showAllCourses() {;
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
    public void enrollCourse(Scanner scanner) {
    }

    public void createCourse(Scanner scanner) {
    }
}
