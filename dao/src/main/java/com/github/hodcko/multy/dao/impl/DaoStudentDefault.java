package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.DaoStudent;
import com.github.hodcko.multy.dao.converter.StudentConverter;
import com.github.hodcko.multy.dao.entity.StudentEntity;
import com.github.hodcko.multy.dao.repository.StudentRepository;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.UserType;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;


import javax.persistence.NoResultException;


public class DaoStudentDefault implements DaoStudent {

    @Autowired
    StudentRepository studentRepository;

    private static final Logger log = LoggerFactory.getLogger(DaoStudentDefault.class);

    @Override
    public Student saveStudent(String name, String secondName, String email, int age) {
        StudentEntity studentEntity = studentRepository.save(new StudentEntity(name, secondName, email, age));
        log.info("create student: name {} second name  {} email {}", name, secondName, email);
        return StudentConverter.fromEntity(studentEntity);
    }

    @Override
    public Student getStudent(int id){
        try {
            StudentEntity studentEntity = studentRepository.getOne(id);
            log.info("geted student with id {}", id);
            return StudentConverter.fromEntity(studentEntity);
        }catch (JpaObjectRetrievalFailureException e){
            return null;
        }

    }

    @Override
    public boolean deleteStudent(String email){
        try {
            int id = studentRepository.getId(email);
            StudentEntity studentEntity = studentRepository.getOne(id);
            studentRepository.delete(studentEntity);
            log.info("deleted student with email {}", email);
            return true;
        }catch (HibernateError e){
            log.error("fail to deleted student with email {}", email);
        }
        return false;
    }

    @Override
    public boolean isExist(String email, UserType userType) {
        try {
            String studentEmail = studentRepository.isExist(email);
            if (email.equalsIgnoreCase(studentEmail)) {
                log.info("student with email {} is already exist", email);
                return true;
            }
        } catch (HibernateException | NoResultException e) {
            log.error("fail to check student with email {}", email, e);
        }
        return false;
    }

    @Override
    public int getId(String email, UserType userType) {
        try {
            int id = studentRepository.getId(email);
            log.info("return id {} of student with email {}", id, email);
            return id;
        } catch (HibernateError e) {
            log.error("fail to return id of student with email {}", email, e);
        }
        return 0;
    }

    @Override
    public String passwordGenerate(String email, UserType userType) {
        try {
            StudentEntity studentEntity = studentRepository.passwordGenerate(email);
            log.info("student with email {} generate password {}", email, studentEntity.getSecondName() + studentEntity.getId());
            return studentEntity.getSecondName() + studentEntity.getId();
        } catch (HibernateError e) {
            log.error("fail to generate password for student with email {}", email, e);
        }
        return null;
    }





//    private final SessionFactory factory;
//
//    public DaoStudentDefault(SessionFactory factory) {
//        this.factory = factory;
//    }
//
//
//    @Override
//
//    public Student saveStudent(String name, String second_name, String email, int age) {
//        StudentEntity student = new StudentEntity(name, second_name, email, age);
//        try {
//            Session session =  factory.getCurrentSession();
//            session.saveOrUpdate(student);;
//            log.info("create student: name {} second name  {} email {}", name, second_name, email);
//            return StudentConverter.fromEntity(student);
//        }catch (HibernateError e){
//            log.error("fail to create student: name {} second name  {} email {}", name, second_name, email, e);
//        }
//        return null;
//    }
//
//    @Override
//    public Student getStudent(int id){
//        try {
//            Session session =  factory.getCurrentSession();
//            StudentEntity student = session.get(StudentEntity.class, id);
//            log.info("get student: name {} second name  {} email {}", student.getName(), student.getSecondName(), student.getEmail());
//            return StudentConverter.fromEntity(student);
//        }catch (HibernateError | NullPointerException e){
//            log.error("fail to get student with id{}", id, e);
//        }
//        return null;
//    }
//
//    @Override
//    public boolean deleteStudent(String email){
//        try {
//            Session session =  factory.getCurrentSession();
//            StudentEntity student = session.createQuery("select s from StudentEntity s where email = :mail", StudentEntity.class)
//                    .setParameter("mail", email).getSingleResult();
//            session.delete(student);
//            log.info("deleted student with email {}", email);
//            return true;
//        }catch (HibernateError e){
//            log.error("fail to deleted student with email {}", email);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean isExist(String email, UserType userType) {
//        try {
//            Session session =  factory.getCurrentSession();
//            String studentEmail = (String)session.createQuery("select s.email from StudentEntity s where s.email = :mail")
//                    .setParameter("mail", email).getSingleResult();
//            if (email.equalsIgnoreCase(studentEmail)) {
//                log.info("student with email {} is already existed", email);
//                return true;
//            }
//        } catch (HibernateException | NoResultException e) {
//            log.error("fail to check student with email {}", email, e);
//        }
//        return false;
//    }
//
//    @Override
//    public int getId(String email, UserType userType) {
//        try {
//            Session session =  factory.getCurrentSession();
//            int id = (int) session.createQuery("select s.id from StudentEntity s where s.email = :email")
//                    .setParameter("email", email).getSingleResult();
//            log.info("return id {} of student with email {}", id, email);
//            return id;
//        } catch (HibernateError e) {
//            log.error("fail to return id of student with email {}", email, e);
//        }
//        return 0;
//    }
//
//    @Override
//    public String passwordGenerate(String email, UserType userType) {
//        try {
//            Session session =  factory.getCurrentSession();
//            StudentEntity student = (StudentEntity)session.createQuery("from StudentEntity s where s.email = :email")
//                    .setParameter("email", email).getSingleResult();
//            log.info("student with email {} generate password {}", email, student.getSecondName() + student.getId());
//            return student.getSecondName() + student.getId();
//        } catch (HibernateError e) {
//            log.error("fail to generate password for student with email {}", email, e);
//        }
//        return null;
//    }
}
