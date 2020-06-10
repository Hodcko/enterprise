package com.github.hodcko.multy.dao.converter;

import com.github.hodcko.multy.dao.entity.StudentEntity;
import com.github.hodcko.multy.model.Student;

public class StudentConverter {

    public static Student fromEntity(StudentEntity studentEntity) {
        if (studentEntity == null) {
            return null;
        }
        return new Student(
                studentEntity.getId(),
                studentEntity.getName(),
                studentEntity.getSecondName(),
                studentEntity.getEmail(),
                studentEntity.getAge());
    }

    public static StudentEntity toEntity(Student student) {
        if (student == null) {
            return null;
        }
        final StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.getId());
        studentEntity.setName(student.getName());
        studentEntity.setSecondName(student.getSecondName());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setAge(student.getAge());
        return studentEntity;
    }
}
