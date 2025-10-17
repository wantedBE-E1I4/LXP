package com.lxp;

import com.lxp.config.DatabaseManager;
import com.lxp.course.EnrollmentDAO;
import com.lxp.course.EnrollmentService;
import com.lxp.course.controller.CourseController;
import com.lxp.course.dao.CourseDAO;
import com.lxp.course.dao.CourseDAOtutor;
import com.lxp.course.service.CourseService;
import com.lxp.lecture.controller.LectureController;
import com.lxp.lecture.dao.LectureDAO;
import com.lxp.lecture.service.LectureService;
import com.lxp.user.controller.UserController;
import com.lxp.user.dao.UserDAO;
import com.lxp.user.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException {
        Connection conn = DatabaseManager.getConnection();

        // DAO 계층 객체 생성
        UserDAO userDAO = new UserDAO();
        CourseDAOtutor courseDAOtutor = new CourseDAOtutor(conn);
        CourseDAO courseDAO = new CourseDAO(conn);
        LectureDAO lectureDAO = new LectureDAO(conn);
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO(conn);

        // Service 계층 객체 생성 (필요한 DAO를 주입)
        UserService userService = new UserService(userDAO);
        LectureService lectureService = new LectureService(lectureDAO);
        EnrollmentService enrollmentService = new EnrollmentService(enrollmentDAO);
        // CourseService는 수강신청(enroll) 기능을 위해 여러 DAO가 필요할 수 있습니다.

        CourseService courseService = new CourseService(courseDAO, courseDAOtutor, lectureDAO, enrollmentDAO);

        // Controller 계층 객체 생성 (필요한 Service를 주입)
        UserController userController = new UserController(userService);
        LectureController lectureController = new LectureController(lectureService);
        CourseController courseController = new CourseController(courseService, lectureService, enrollmentService);

        // ====================================================================
        // 2. 메인 애플리케이션 루프
        // ====================================================================
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== 수강신청 시스템 V1.0 =====");
            System.out.println("당신의 역할을 선택해주세요.");
            System.out.println("1. 학생");
            System.out.println("2. 선생님");
            System.out.println("0. 종료");
            System.out.print(">> ");
            String roleChoice = scanner.nextLine();
            switch (roleChoice) {
                case "1":
                    // 학생 메뉴 실행
                    runStudentMenu(scanner, courseController, lectureController);
                    break;
                case "2":
                    // 선생님 메뉴 실행
                    runTutorMenu(scanner, courseController, lectureController, userController);
                    break;
                case "0":
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    conn.close(); // 프로그램 종료 시 Connection 자원 반납
                    return; // 프로그램 완전 종료
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }
    /**
     * 학생 전용 메뉴를 실행하는 메서드
     */
    private static void runStudentMenu(Scanner scanner, CourseController courseController, LectureController lectureController) {
        // Long studentId = 1L; // 실제로는 로그인된 학생의 ID를 받아와야 합니다.
        while (true) {
            //전체 강좌 출력
            courseController.showAllCourses();
            System.out.println("\n--- [학생 메뉴] ---");
            System.out.println("1. 전체 강좌 목록 조회");
            System.out.println("2. 강좌 수강 신청");
            System.out.println("3. 특정 강좌의 강의(차시) 목록 보기");
            System.out.println("0. 역할 선택으로 돌아가기");
            System.out.print(">> ");
            String menuChoice = scanner.nextLine();

            if ("1".equals(menuChoice)) {
                // CourseController를 통해 전체 강좌 목록을 보여줍니다.
                courseController.showAllCourses();
            } else if ("2".equals(menuChoice)) {
                // CourseService의 enroll 기능을 CourseController를 통해 호출합니다.
                courseController.enrollCourse(scanner /*, studentId */);
            } else if ("3".equals(menuChoice)) {
                // LectureController를 통해 특정 강좌의 강의 목록을 보여줍니다.
                lectureController.showLecturesByCourse(scanner);
            } else if ("0".equals(menuChoice)) {
                return; // 메인 메뉴로 복귀
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
    /**
     * 선생님 전용 메뉴를 실행하는 메서드
     */
    private static void runTutorMenu(Scanner scanner, CourseController courseController, LectureController lectureController, UserController userController) {
        // 전체 강좌 출력
        courseController.showAllCourses();
        // Long teacherId = 2L; // 실제로는 로그인된 선생님의 ID를 받아와야 합니다.
        while (true) {
            System.out.println("\n--- [선생님 메뉴] ---");
            System.out.println("1. 내 강좌 관리 "); // 은수님
            System.out.println("2. 내 강좌 목록 보기");
            System.out.println("3. 내 강좌에 강의 관리"); // 주영님
            System.out.println("4. 전체강좌 조회 ");
            System.out.println("0. 역할 선택으로 돌아가기");
            System.out.println("q. 프로그램 종료");
            System.out.print(">> ");
            String menuChoice = scanner.nextLine();

                if ("1".equals(menuChoice)) {
                    // [수정] 강좌 관리에 대한 모든 책임을 CourseController에게 위임합니다.
                    courseController.manageCourses(scanner);
                } else if ("2".equals(menuChoice)) {
                    System.out.println("강사님께서 개설하신 강좌목록입니다.");
                   // courseController.showAllCourses();
                }
                else if ("3".equals(menuChoice)) {
                    System.out.println("== 내 강좌 목록 ==");
                    lectureController.getCourseList();
                    System.out.println("== 원하시는 강좌의 번호를 입력해주세요! ==");
                    Long courseId = scanner.nextLong();
                    lectureController.getLectureList(courseId);
                }

                else if ("0".equals(menuChoice)) {
                    return; // 메인 메뉴로 복귀
                }
                else if ("q".equals(menuChoice)) {
                System.out.println("종료기능 구현중."); }
                else {
                System.out.println("잘못된 입력입니다.");

            }
        }
    }
}

