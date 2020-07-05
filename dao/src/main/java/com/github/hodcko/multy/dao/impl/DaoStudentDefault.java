package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.DaoStudent;
import com.github.hodcko.multy.dao.converter.StudentConverter;
import com.github.hodcko.multy.dao.entity.StudentEntity;
import com.github.hodcko.multy.dao.repository.StudentRepository;
import com.github.hodcko.multy.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;


public class DaoStudentDefault implements DaoStudent {

    @Autowired
    StudentRepository studentRepository;

    private static final Logger log = LoggerFactory.getLogger(DaoStudentDefault.class);

    @Override
    public Student saveStudent(String name, String secondName, String email, int age) {
        try {
            StudentEntity studentEntity = studentRepository.save(new StudentEntity(name, secondName, email, age));
            log.info("create student: name {} second name  {} email {}", name, secondName, email);
            return StudentConverter.fromEntity(studentEntity);
        }catch (Exception e){
            log.error("fail to create student: name {} second name  {} email {}", name, secondName, email);
            return null;
        }
    }

    @Override
    public Student getStudent(int id){
        try {
            StudentEntity studentEntity = studentRepository.getOne(id);
            log.info("get student with id {}", id);
            return StudentConverter.fromEntity(studentEntity);
        }catch (JpaObjectRetrievalFailureException e){
            return null;
        }

    }

    @Override
    public boolean deleteStudent(String email){
        try {
            long id = studentRepository.deleteByEmail(email);
            log.info("deleted student with email {}", email);
            return id == 1;
        }catch (Exception e){
            log.error("fail to deleted student with email {}", email);
        }
        return false;
    }

    @Override
    public boolean isExist(String email) {
        try {
            int id = studentRepository.getId(email);
            boolean result = studentRepository.existsById(id);
            if (result) {
                log.info("student with email {} is already exist", email);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("fail to check student with email {}", email, e);
        }
        return false;
    }

    @Override
    public int getId(String email) {
        try {
            int id = studentRepository.getId(email);
            log.info("return id {} of student with email {}", id, email);
            return id;
        } catch (Exception e) {
            log.error("fail to return id of student with email {}", email, e);
        }
        return 0;
    }

    @Override
    public String passwordGenerate(String email) {
        try {
            StudentEntity studentEntity = studentRepository.findByEmail(email);
            log.info("student with email {} generate password {}", email, studentEntity.getSecondName() + studentEntity.getId());
            return studentEntity.getSecondName() + studentEntity.getId();
        } catch (Exception e) {
            log.error("fail to generate password for student with email {}", email, e);
        }
        return null;
    }
}
