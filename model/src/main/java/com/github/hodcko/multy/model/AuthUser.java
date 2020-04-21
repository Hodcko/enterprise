package com.github.hodcko.multy.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "auth_user")
public class AuthUser {
    @Id
    @Column (name = "id")
    private int userId;
    @Column
    private String login;
    @Column
    private String password;
    @Column (name = "Role")
    private String role;

    public AuthUser(String login, String password, String role, int userId) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.userId = userId;
    }

    public AuthUser(){

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getUserId() {
        return userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserId(int userId) {
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
        return userId == authUser.userId &&
                Objects.equals(login, authUser.login) &&
                Objects.equals(password, authUser.password) &&
                Objects.equals(role, authUser.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role, userId);
    }
}
