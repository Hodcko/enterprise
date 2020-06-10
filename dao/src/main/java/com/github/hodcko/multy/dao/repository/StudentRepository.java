package com.github.hodcko.multy.dao.repository;

import com.github.hodcko.multy.dao.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    @Query("select s.email from StudentEntity s where s.email = :email")
    String isExist(@Param("email") String email);

    @Query("select s.id from StudentEntity s where s.email = :email")
    int getId(@Param("email") String email);

    @Query("select s from StudentEntity s where s.email = :email")
    StudentEntity passwordGenerate(@Param("email") String email);

//    @Transactional
//    @Modifying()
//    @Query(value = "delete from StudentEntity s where s.email = :email")
//    int deleteStudent(@Param("email") String email);

}
