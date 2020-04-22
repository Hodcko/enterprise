package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.UserType;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.NoResultException;


public class DaoIsExistDefault implements com.github.hodcko.multy.dao.DaoIsExist {

    private static volatile com.github.hodcko.multy.dao.DaoIsExist instance;
    private static final Logger log = LoggerFactory.getLogger(DaoIsExistDefault.class);



    public static com.github.hodcko.multy.dao.DaoIsExist getInstance(){
        com.github.hodcko.multy.dao.DaoIsExist localInstance = instance;
        if(localInstance == null){
            synchronized (com.github.hodcko.multy.dao.DaoIsExist.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DaoIsExistDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean isExist(String email, UserType userType){
        if (userType.equals(UserType.STUDENT)) {
            try (Session session = SFUtil.getSession()) {
                session.beginTransaction();
                String studentEmail = (String) session.createNativeQuery("select email from student where email = :mail")
                        .setParameter("mail", email).getSingleResult();
                session.getTransaction().commit();
                if (email.equalsIgnoreCase(studentEmail)) {
                    log.info("student with email {} is already existed", email);
                    return true;
                }
            }catch (NoResultException e ){
                log.info("fail to check student with email {}", email, e);
            }
        } else if (userType.equals(UserType.TEACHER)) {
            try (Session session = SFUtil.getSession()) {
                session.beginTransaction();
                String teacherEmail = (String) session.createNativeQuery("select email from teacher where email = :mail")
                        .setParameter("mail", email).getSingleResult();
                session.getTransaction().commit();
                if (email.equalsIgnoreCase(teacherEmail)) {
                    log.info("teacher with email {} is already existed", email);
                    return true;
                }
            }catch (NoResultException e){
                log.info("fail to check teacher with email {}", email, e);
            }
        }
        return false;
    }
}
