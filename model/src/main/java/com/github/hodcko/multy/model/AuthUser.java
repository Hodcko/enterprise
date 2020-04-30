package com.github.hodcko.multy.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "auth_user")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AuthUser {
    @Id
    @Column (name = "id")
    private Integer userId;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private UserType role;

    public AuthUser(String login, String password, UserType role, Integer userId) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.userId = userId;
    }

    public AuthUser(){

    }

    @OneToOne(mappedBy = "authUser", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Student student;

    @OneToOne(mappedBy = "authUser", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Teacher teacher;

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
        AuthUser authUser = (AuthUser) o;
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
