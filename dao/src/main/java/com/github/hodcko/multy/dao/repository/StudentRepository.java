package com.github.hodcko.multy.dao.repository;

import com.github.hodcko.multy.dao.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    @Query("select s.id from StudentEntity s where s.email = :email")
    int getId(@Param("email") String email);

    StudentEntity findByEmail(String email);

    long deleteByEmail(String email);

    @Override
    boolean existsById(Integer integer);
}
