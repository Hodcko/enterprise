package com.github.hodcko.multy.dao.converter;

import com.github.hodcko.multy.dao.entity.TeacherEntity;
import com.github.hodcko.multy.model.Teacher;

public class TeacherConverter {

    public static Teacher fromEntity(TeacherEntity teacherEntity) {
        if (teacherEntity == null) {
            return null;
        }
        return new Teacher(
                teacherEntity.getId(),
                teacherEntity.getName(),
                teacherEntity.getSecondName(),
                teacherEntity.getEmail(),
                teacherEntity.getCursId());
    }

    public static TeacherEntity toEntity(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        final TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId(teacher.getId());
        teacherEntity.setName(teacher.getName());
        teacherEntity.setSecondName(teacher.getSecondName());
        teacherEntity.setEmail(teacher.getEmail());
        teacherEntity.setCursId(teacher.getCursId());
        return teacherEntity;
    }
}
