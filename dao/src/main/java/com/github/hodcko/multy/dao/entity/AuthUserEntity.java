package com.github.hodcko.multy.dao.entity;


import com.github.hodcko.multy.model.UserType;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "auth_user")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy. READ_WRITE)
public class AuthUserEntity {
    @Id
    @Column(name = "id")
    private Integer userId;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private UserType role;

    public AuthUserEntity(String login, String password, UserType role, Integer userId) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.userId = userId;
    }

    public AuthUserEntity(){

    }

    @OneToOne(mappedBy = "authUser", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private StudentEntity student;

    @OneToOne(mappedBy = "authUser", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private TeacherEntity teacher;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserType getRole() {
        return role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthUserEntity authUser = (AuthUserEntity) o;
        return Objects.equals(userId, authUser.userId) &&
                Objects.equals(login, authUser.login) &&
                Objects.equals(password, authUser.password) &&
                role == authUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, role);
    }
}
