package com.lxp.user;

// 유저 엔티티
public class User {
    private int id;
    private String name;
    private Role role; // LEARNER or TUTOR
    private String email;
    //private List<Course> enrolledCourses = new ArrayList<>(); // 학습자일 경우 사용

    // 생성자
    // NOTE: 직접 객체를 생성하지 말고, createLearner, createTutor를 사용해주세요.
    private User(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    // 역할별 생성
    public static User createLearner(int id, String name) {
        return new User(id, name, Role.LEARNER);
    }

    public static User createTutor(int id, String name) {
        return new User(id, name, Role.TUTOR);
    }

    // DB 조회용
    static User createFromDB(int id, String name, Role role) {
        return new User(id, name, role);
    }

    // Getter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    //public List<Course> getEnrolledCourses() {
    //    return enrolledCourses;
    //}
    //
    //public void setEnrolledCourses(List<Course> enrolledCourses) {
    //    this.enrolledCourses = enrolledCourses;
    //}

    @Override
    public String toString() {
        return String.format("User{id='%s', name='%s', role=%s}", id, name, role);
    }

    public int getUserId() {
        return 0;
    }
}
