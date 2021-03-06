package com.github.hodcko.multy.model;


import java.util.Objects;


public class AuthUser {
    private Integer userId;

    private String login;

    private String password;

    private UserType role;

    public AuthUser(String login, String password, UserType role, Integer userId) {
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
