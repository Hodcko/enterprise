package com.github.hodcko.multy.service;


import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.model.UserType;

public interface ServiceAuthUser {
    AuthUser saveAuthUser(int userId, String login, String password, UserType role) ;

    String getLoginByPassword(String password);

    boolean deleteAuthUser(int id, UserType role);

    String passwordGenerate(String email, UserType userType);

    UserType getRole(String login, String password);

    AuthUser getAuthUser(String login, String password);
}
