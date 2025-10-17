package com.lxp.lecture.controller;

import com.lxp.course.Course;
import com.lxp.course.EnrollmentService;
import com.lxp.course.dto.CourseData;
import com.lxp.course.dto.CourseDetail;
import com.lxp.course.dto.EnrollmentData;
import com.lxp.course.service.CourseService;
import com.lxp.lecture.dto.LecturesByCourse;
import com.lxp.lecture.service.LectureService;
import com.lxp.user.User;

import java.util.List;
import java.util.Scanner;

public class LectureController {
    private LectureService lectureService;
    private CourseService courseService;
    private EnrollmentService enrollmentService;
    public LectureController(LectureService lectureService, EnrollmentService enrollmentService, CourseService courseService) {
        this.lectureService = lectureService;
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
    }
    public void showLecturesByCourse(Scanner scanner, int userId) {
        System.out.println("\n--- [내 강좌 리스트] ---");
        // 강좌 리스트 출력 TODO : 재웅님이 작업하고 있는 부분
        List<EnrollmentData> enrollments = enrollmentService.getEnrollmentsByUser(userId);
        for (EnrollmentData enrollment : enrollments) {
            System.out.println(enrollment.courseId() + ". " + enrollment.courseTitle() + " - " + enrollment.tutorName());
        }
        System.out.println();
        System.out.println("조회할 강좌의 번호를 입력해주세요.");
        System.out.println("0. 종료"); // TODO : 종료를 해야할지 App main으로 돌아가야 할지 고민 !
        System.out.print(">> ");
        String courseChoice = scanner.nextLine();
        switch (courseChoice) {
            case "0" :
                System.out.println("프로그램을 종료합니다.");
                scanner.close();
                return;
            default:
                lectureCourse(scanner, courseChoice);
                break;
        }
    }

    private void lectureCourse(Scanner scanner, String courseId) {
        int convertIntCourseId = Integer.parseInt(courseId);

        // 강좌 제목
        CourseDetail course = courseService.getCourseDetail(convertIntCourseId);
        System.out.println("--- " + course.title() + " ---");

        // 강의 리스트
        // NOTE : Service layer에 값을 넘겨줄 때는 단순 데이터를 넘겨줘야 합니다.
        // TODO : LectureService는 주영님이 만든 메서드로 수행.
        List<LecturesByCourse> lectures = lectureService.getLecturesByCourse(convertIntCourseId);

        for (LecturesByCourse lecture : lectures) {
            System.out.println(lecture.orderNo() + ". " +  lecture.title());
        }


    }

    public void addLectureToCourse(Scanner scanner) {
    }
}
