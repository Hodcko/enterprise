package com.github.hodcko.multy.service;



public interface SecurityService {
    String login(String login, String password);

    boolean changePassword(String login, String password, String newPassword);

    String findPassword(String password, String anotherPassword);
}
