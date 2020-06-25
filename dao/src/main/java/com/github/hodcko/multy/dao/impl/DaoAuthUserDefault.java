package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.dao.converter.AuthUserConverter;
import com.github.hodcko.multy.dao.entity.AuthUserEntity;
import com.github.hodcko.multy.dao.repository.AuthUserRepository;
import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.dao.DaoAuthUser;
import com.github.hodcko.multy.model.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class DaoAuthUserDefault implements DaoAuthUser {
    private static final Logger log = LoggerFactory.getLogger(DaoAuthUserDefault.class);

    @Autowired
    AuthUserRepository authUserRepository;

    @Override
    public String getLoginByPassword(String password){
        try {
            AuthUserEntity authUserEntity = authUserRepository.findByPassword(password);
            log.info("get login {} from authUser by password {}", authUserEntity.getLogin(), password);
            return authUserEntity.getLogin();
        }catch (Exception e){
            log.error("fail to get login from authUser by password {}", password, e);
        }
        return null;
    }



    @Override
    public AuthUser saveAuthUser(int userID, String login, String password, UserType role) {
        AuthUserEntity authUser = new AuthUserEntity(login, password, role, userID);
        try {
            authUserRepository.save(authUser);
            log.info("create authUser with login  {} password {} role {} id {}", login, password, role, userID);
            return AuthUserConverter.fromEntity(authUser);
        } catch (Exception e) {
            log.error(" fail to create authUser with login  {} password {} role {} id {}", login, password, role, userID, e);
        }
        return null;
    }



    @Override
    public boolean deleteAuthUser (int id, UserType role) {
        try {
            int result = authUserRepository.deleteByUserIdAndRole(id, role);
            if(result == 1) {
                log.info("deleted authUser with id {} role {}", id, role);
                return true;
            }return false;
        }catch (Exception e){
            log.error("fail to deleted authUser with id {} role {}", id, role);
        }
        return false;
    }



    @Override
    public UserType getRole(String login, String password){
        try {
            AuthUserEntity authUserEntity = authUserRepository.findByLoginAndPassword(login, password);
            return AuthUserConverter.fromEntity(authUserEntity).getRole();
        }catch (Exception e){
            log.error("fail to get role from authUser with login {} and password {}", login, password, e);
        }
        return null;
    }


    @Override
    public AuthUser getAuthUser(String login, String password){
        try {
            AuthUserEntity authUser = authUserRepository.findByLoginAndPassword(login, password);
            log.info("Get authUser with login {} password{}", login, password);
            return AuthUserConverter.fromEntity(authUser);
        }catch (Exception e){
            log.error("fail to get authUser with login {} password{}", login, password);
        }
        return null;
    }


    @Override
    public boolean changePassword(String login, String password, String newPassword) {
        try {
            int result = authUserRepository.changePassword(login, password, newPassword);
            log.info("password changed by user whit login{} password {} new password {}", login, password, newPassword);
            return result == 1;
        } catch (Exception e) {
            log.error("fail to changed password by user whit login{} password {} new password {}", login, password, newPassword);
        }
        return false;
    }
}
