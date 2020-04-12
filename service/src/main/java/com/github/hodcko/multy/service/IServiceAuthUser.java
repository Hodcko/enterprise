package com.github.hodcko.multy.service;


import com.github.hodcko.multy.model.AuthUser;

public interface IServiceAuthUser {
    AuthUser saveAuthUser(int user_id, String login, String password, String role) ;

    String getByLogin(String login);

    boolean deleteAuthUser(int id, String role);

    String passwordGenerate(String email, String userType);

    String getRole(String login, String password);

    AuthUser getAuthUser(String login, String password);


}
