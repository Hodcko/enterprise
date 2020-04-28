package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.dao.DaoAuthUser;
import com.github.hodcko.multy.model.UserType;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DaoAuthUserDefault implements DaoAuthUser {
    private static final Logger log = LoggerFactory.getLogger(DaoAuthUserDefault.class);
    private static volatile DaoAuthUser instance;

    public static DaoAuthUser getInstance() {
        DaoAuthUser localInstance = instance;
        if (localInstance == null) {
            synchronized (DaoAuthUser.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DaoAuthUserDefault();
                }
            }
        }
        return localInstance;
    }



    @Override
    public String getByLogin(String password){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            String login = (String) session.createNativeQuery("select login from auth_user where password = :pass")
                    .setParameter("pass", password).getSingleResult();
            session.getTransaction().commit();
            log.info("get login {} from authUser by password {}", login, password);
            return login;
        }catch (HibernateError e){
            log.error("fail to get login from authUser by password {}", password, e);
        }
        return null;
    }

    @Override
    public AuthUser saveAuthUser(int user_id, String login, String password, UserType role) {
        AuthUser authUser = new AuthUser(login, password, role, user_id);
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(authUser);
            session.getTransaction().commit();
            log.info("create authUser with login  {} password {} role {} id {}", login, password, role, user_id);
            return authUser;
        } catch (HibernateError e) {
            log.error(" fail to create authUser with login  {} password {} role {} id {}", login, password, role, user_id, e);
        }
        return null;
    }


    @Override
    public boolean deleteAuthUser (int id, UserType role) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            AuthUser authUser = session.createQuery("select a from AuthUser a where userId = :user_id and role = :role", AuthUser.class)
                    .setParameter("user_id", id)
                    .setParameter("role", role)
                    .getSingleResult();
            session.delete(authUser);
            session.getTransaction().commit();
            log.info("deleted authUser with id {} role {}", id, role);
            return true;
        }catch (HibernateError e){
            log.error("fail to deleted authUser with id {} role {}", id, role);
        }
        return false;
    }

    @Override
    public UserType getRole(String login, String password){
        String role;
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            role = (String) session.createNativeQuery("select Role from auth_user where login = :login and password = :pass")
                    .setParameter("login", login)
                    .setParameter("pass", password)
                    .getSingleResult();
            session.getTransaction().commit();
            return UserType.valueOf(role.toUpperCase());
        }catch (HibernateError e){
            log.error("fail to get role from authUser with login {} and password {}", login, password, e);
        }
        return null;
    }

    @Override
    public AuthUser getAuthUser(String login, String password){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            AuthUser authUser = session.createQuery("select a from AuthUser a where login = :login and password = :pass",
                    AuthUser.class).setParameter("login", login)
                    .setParameter("pass", password)
                    .getSingleResult();
            session.getTransaction().commit();
            log.info("Get authUser with login {} password{}", login, password);
            return authUser;
        }catch (HibernateError e){
            log.error("fail to get authUser with login {} password{}", login, password);
        }
        return null;
    }

    @Override
    public boolean changePassword(String login, String password, String newPassword){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            AuthUser authUser = session.createQuery("select a from AuthUser a where login = :login and password = :pass",
                    AuthUser.class).setParameter("login", login)
                    .setParameter("pass", password)
                    .getSingleResult();
            authUser.setPassword(newPassword);
            session.getTransaction().commit();
            log.info("password changed by user whit login{} password {} new password {}", login, password, newPassword);
            return true;
        }catch (HibernateError e){
            log.error("fail to changed password by user whit login{} password {} new password {}", login, password, newPassword);
        }
        return false;
    }
}
