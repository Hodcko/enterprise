package com.github.hodcko.multy.dao.converter;

import com.github.hodcko.multy.dao.entity.CursEntity;
import com.github.hodcko.multy.model.Curs;

public class CursConverter {

    public static Curs fromEntity(CursEntity cursEntity) {
        if (cursEntity == null) {
            return null;
        }
        return new Curs(
                cursEntity.getId(),
                cursEntity.getName(),
                cursEntity.getStart(),
                cursEntity.getEnd());
    }

    public static CursEntity toEntity(Curs curs) {
        if (curs == null) {
            return null;
        }
        final CursEntity cursEntity = new CursEntity();
        cursEntity.setId(curs.getId());
        cursEntity.setName(curs.getName());
        cursEntity.setStart(curs.getStart());
        cursEntity.setEnd(curs.getEnd());
        return cursEntity;
    }
}
