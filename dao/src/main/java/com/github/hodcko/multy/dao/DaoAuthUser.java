package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.model.UserType;

public interface DaoAuthUser {

    String getByLogin(String login);

    AuthUser saveAuthUser(int user_id, String login, String password, UserType role);

    String passwordGenerate(String email, UserType userType);

    boolean deleteAuthUser(int id, UserType role);

    UserType getRole(String login, String password);

    AuthUser getAuthUser(String login, String password);

    boolean changePassword(String login, String password, String newPassword);

}
