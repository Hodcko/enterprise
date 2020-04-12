package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.AuthUser;

public interface IDaoAuth {
    String getByLogin(String login);

    AuthUser saveAuthUser(int user_id, String login, String password, String role);

    String passwordGenerate(String email, String userType);

    boolean deleteAuthUser(int id, String role);

    String getRole(String login, String password);

    AuthUser getAuthUser(String login, String password);

    boolean changePassword(String login, String password, String newPassword);

}
