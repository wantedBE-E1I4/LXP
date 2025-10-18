package com.lxp.course.controller;

import com.lxp.course.Course;
import com.lxp.course.dto.CourseWithStatusDTO;
import com.lxp.course.Enrollment;
import com.lxp.course.dto.MyEnrollmentCourseInfo;
import com.lxp.course.service.CourseService;
import com.lxp.course.service.EnrollmentService;
import com.lxp.course.service.dto.CreateCourseDto;
import com.lxp.lecture.Lecture;
import com.lxp.lecture.service.LectureService;
import com.lxp.user.User;
import com.lxp.user.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CourseController {
    Scanner scanner = new Scanner(System.in);

    private CourseService courseService;
    private LectureService lectureService;
    private EnrollmentService enrollmentService;
    private UserService userService;

    public CourseController(
            CourseService courseService,
            LectureService lectureService,
            EnrollmentService enrollmentService,
            UserService userService
    ) {
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.enrollmentService = enrollmentService;
        this.userService = userService;
    }

    public void manageCourses(Scanner scanner) {
        while (true) {
            System.out.println("\n--- [강좌 관리 메뉴] ---");
            System.out.println("1. 강좌 개설");
            System.out.println("2. 강좌 삭제");
            System.out.println("0. 이전 메뉴로 돌아가기");
            System.out.print(">> ");
            String subMenuChoice = scanner.nextLine(); // [핵심] Controller가 직접 입력을 받음

            if ("1".equals(subMenuChoice)) {
                this.createCourse(scanner);
            } else if ("2".equals(subMenuChoice)) {
                this.deleteCourse(scanner);
            } else if ("0".equals(subMenuChoice)) {
                return;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
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
    }

    /**
     * [복원 및 수정] 강좌 삭제 기능을 복원합니다.
     */
    public void deleteCourse(Scanner scanner) {
        // 1. 먼저 사용자에게 삭제 가능한 강좌 목록을 보여줍니다.
        showAllCourses();

        try {
            // 2. 사용자로부터 삭제할 강좌의 ID를 입력받습니다.
            System.out.print("삭제할 강좌의 ID를 입력하세요: ");
            String input = scanner.nextLine();
            int courseIdToDelete = Integer.parseInt(input); // [수정] Long -> int

            // 3. Service에게 실제 삭제 처리를 위임합니다.
            boolean isDeleted = courseService.deleteCourseById(courseIdToDelete);

            // 4. 처리 결과를 사용자에게 알려줍니다.
            if (isDeleted) {
                System.out.println("✅ 강좌가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("❌ 해당 ID의 강좌를 찾을 수 없거나 이미 삭제된 강좌입니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❗ 잘못된 입력입니다. 숫자를 입력해주세요.");
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


    //강사 - 강좌 개설
    public void createCourse(Scanner scanner) {
        // 사용자 입력값 받기
        System.out.println("\n--- 개설할 강좌의 정보를 입력해주세요. ---\n");
        System.out.print("제목 : ");
        String title = scanner.nextLine();
        System.out.print("설명 : ");
        String description = scanner.nextLine();
        System.out.print("카테고리 : ");
        String category = scanner.nextLine();

        // 강사 조회
        User currentTutor = this.userService.getCurrentTutor();

        // DTO 생성
        CreateCourseDto dto = new CreateCourseDto(currentTutor.getId(), title, description, category);

        // 강사의 강좌 개설
        int createdCourseId = courseService.openNewCourse(dto);

        if (createdCourseId == 0) {
            System.out.println("--- 강좌 개설 중 오류가 발생했습니다! 관리자에게 문의해주세요. ---");
        } else {
            System.out.println("--- 강좌가 개설되었습니다! ---");
        }
    }



    public void showMyCoursesForLearner(Scanner scanner, int userId) {

        while(true) {
            // NOTE : 내 수강 목록 띄우기
            List<Enrollment> enrollmentDataList = enrollmentService.getEnrollmentsByUser(userId);

            System.out.println("\n== 내 수강 목록 ==");
            if (enrollmentDataList.isEmpty()) {
                System.out.println("수강 신청한 강의가 없습니다.\n");
            }

            // 2. 받은 데이터를 for문으로 출력
            for (Enrollment data : enrollmentDataList) {

                // Course 객체에 getTitle() 메서드가 있어야 합니다.
                System.out.printf("%d. %s - %s\n", data.getEnrollmentId(), data.getTitle(), data.getUserId());
            }


            System.out.println("\n--- [수강 메뉴] ---");
            System.out.println("1. 강의 듣기");
            System.out.println("2. 수강 취소");
            System.out.println("0. 이전 메뉴로 돌아가기");
            System.out.print(">> ");
            String menuChoice = scanner.nextLine();

            if ("1".equals(menuChoice)) {
                listenCourseForLearner(scanner, userId);
            } else if ("2".equals(menuChoice)) {
                deleteCourseForLerner(scanner, userId, enrollmentDataList);
            } else if ("0".equals(menuChoice)) {
                return;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }


    /**
     * 강좌 듣기 로직
    * */
    private void listenCourseForLearner(Scanner scanner, int userId) {
        List<MyEnrollmentCourseInfo> enrollmentDataList = enrollmentService.getMyEnrollments(userId);

        main:
        while (true) {
            if (enrollmentDataList.isEmpty()) {
                System.out.println("수강 신청한 강의가 없어 이전 메뉴로 돌아갑니다.\n");
                return;
            }

            System.out.println("\n== 내 수강 목록 ==");

            // 받은 데이터를 for문으로 출력
            int index = 1;
            for (MyEnrollmentCourseInfo data : enrollmentDataList) {

                // Course 객체에 getTitle() 메서드가 있어야 합니다.
                System.out.printf("%d. %s by%s (%.1f%%)\n", index, data.courseTitle(), data.tutorName(), data.progress());
                index++;
            }

            System.out.println("\n0. 이전 메뉴로 돌아가기");
            System.out.println("--- 수강하려는 강좌(Course)의 번호를 입력해주세요. ---");
            int selectCourseId = scanner.nextInt();
            scanner.nextLine();

            String selectedCourseTitle = null;
            if (selectCourseId == 0) {
                return;
            } else {
                for (MyEnrollmentCourseInfo data : enrollmentDataList) {
                    if (data.courseId() == selectCourseId) {
                        selectedCourseTitle = data.courseTitle();
                    } else {
                        System.out.println("없는 강좌(Course)를 선택하셨습니다.\n");
                        continue main;
                    }
                }
            }


            listenLectureForLearner(scanner, selectedCourseTitle, selectCourseId);
        }
    }

    /**
     * 강의 듣기 로직
    * */
    private void listenLectureForLearner(Scanner scanner, String title, int selectedCourseId) {
        /*-------구분선--------*/
        main:
        while (true){
            System.out.println("=== " + title + " ===");
            List<Lecture> lectureDataList = lectureService.getLectureList(selectedCourseId);
            for (Lecture data : lectureDataList) {
                System.out.printf("%d. %s\n", data.getOrderNo(), data.getTitle());
            }

            System.out.println("\n0. 이전 메뉴로 돌아가기");
            System.out.println("--- 수강하려는 강의(Lecture)의 번호를 입력해주세요 ---");
            System.out.print(">> ");
            int selectedLecture = scanner.nextInt();
            scanner.nextLine();


            Optional<Lecture> selectedLectureOpt;
            if (selectedLecture == 0) {
                return;
            } else {
                selectedLectureOpt = lectureDataList.stream()
                        .filter(l -> l.getLectureId() == selectedLecture)
                        .findFirst();

                if (selectedLectureOpt.isEmpty()) {
                    System.out.println("없는 강의(Lecture)를 선택하셨습니다.\n");
                    return;
                }
            }

            int selectedLectureId = selectedLectureOpt.get().getLectureId();

            // NOTE : 수강 여부 [y/n]
            System.out.println("수강하시겠습니까 ? [y/n]");
            String answer = scanner.nextLine();
            String lectureTitle = null;

            if (answer.toLowerCase().equals("y")) {
                for (Lecture data : lectureDataList) {
                    if (data.getLectureId() == selectedLectureId) {
                        lectureTitle = data.getTitle();
                        break;
                    } else {
                        System.out.println("없는 강의를 선택하셨습니다.\n");
                        continue main;
                    }
                }
            } else if (answer.toLowerCase().equals("n")) {
                continue;
            } else {
                System.out.println("'y' 혹은 'n'을 입력해주세요.");
            }
            System.out.println("'<" + title + " - " + lectureTitle + ">'" + "수강 처리되었습니다.\n");
            return;
        }
    }

    /**
     * 수강 취소 로직
    * */
    private void deleteCourseForLerner(Scanner scanner, int userId, List<Enrollment> enrollmentDataList) {

        main:
        while (true) {
            if (enrollmentDataList.isEmpty()) {
                System.out.println("수강 신청한 강의가 없어 이전 메뉴로 돌아갑니다.\n");
                return;
            }

            System.out.println("\n== 내 수강 목록 ==");

            // 받은 데이터를 for문으로 출력
            for (Enrollment data : enrollmentDataList) {

                // Course 객체에 getTitle() 메서드가 있어야 합니다.
                System.out.printf("%d. %s - %s\n", data.getEnrollmentId(), data.getTitle(), data.getUserId());
            }

            System.out.println("\n--- 삭제하려는 강좌(Course)의 번호를 입력해주세요. ---");
            int selectCourse = scanner.nextInt();
            scanner.nextLine();

            String title = null;
            for (Enrollment data : enrollmentDataList) {
                if (data.getCourseId() == selectCourse) {
                    title = data.getTitle();
                    int deletedRow = enrollmentService.deleteEnrollment(selectCourse, userId);
                    if (deletedRow > 0) {
                        System.out.println("<" + title + ">" + "이 삭제되었습니다.");
                        return;
                    } else {
                        System.out.println("서버에 오류가 발생했습니다.");
                    }
                } else {
                    System.out.println("없는 강좌(Course)를 선택하셨습니다.\n");
                    continue main;
                }
            }
        }
    }
}

