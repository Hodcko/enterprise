package com.github.hodcko.multy.model;

import java.util.Objects;

public class AuthUser {
    private String login;
    private String password;
    private String role;
    private int userId;

    public AuthUser(String login, String password, String role, int userId) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.userId = userId;
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
