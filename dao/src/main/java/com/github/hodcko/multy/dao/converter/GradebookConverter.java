package com.github.hodcko.multy.dao.converter;

import com.github.hodcko.multy.dao.entity.GradebookEntity;
import com.github.hodcko.multy.model.Gradebook;

public class GradebookConverter {

    public static Gradebook fromEntity(GradebookEntity gradebookEntity) {
        if (gradebookEntity == null) {
            return null;
        }
        return new Gradebook(
                gradebookEntity.getId(),
                gradebookEntity.getStudentId(),
                gradebookEntity.getCursId(),
                gradebookEntity.getGrade());
    }

    public static GradebookEntity toEntity(Gradebook gradebook) {
        if (gradebook == null) {
            return null;
        }
        final GradebookEntity gradebookEntity = new GradebookEntity();
        gradebookEntity.setId(gradebook.getId());
        gradebookEntity.setStudentId(gradebook.getStudentId());
        gradebookEntity.setCursId(gradebook.getCursId());
        gradebookEntity.setGrade(gradebook.getGrade());
        return gradebookEntity;
    }
}