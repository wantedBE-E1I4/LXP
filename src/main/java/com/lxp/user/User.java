package com.lxp.user;

public class User {
    private int id;
    private String name;
    private Role role; // LEARNER or TUTOR
    private String email;

    private User(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public static User createLearner(int id, String name) {
        return new User(id, name, Role.LEARNER);
    }

    public static User createTutor(int id, String name) {
        return new User(id, name, Role.TUTOR);
    }

    /**
     * DB 조회 결과를 바탕으로 User 객체를 생성하는 팩토리 메서드
     * [FIX] 접근 제어자를 'package-private'에서 'public'으로 변경했습니다.
     * 이제 com.lxp.user.dao 패키지에서도 이 메서드를 호출할 수 있습니다.
     */
    public static User createFromDB(int id, String name, Role role) {
        return new User(id, name, role);
    }

    // --- Getter and Setter ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public Role getRole() { return role; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("User{id='%s', name='%s', role=%s}", id, name, role);
    }
}