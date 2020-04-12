package com.github.hodcko.multy.service;



public interface ISecurityService {
    String login(String login, String password);

    boolean changePassword(String login, String password, String newPassword);

    String findPassword(String password, String anotherPassword);


}
