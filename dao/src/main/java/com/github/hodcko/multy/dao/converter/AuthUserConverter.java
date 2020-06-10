package com.github.hodcko.multy.dao.converter;

import com.github.hodcko.multy.dao.entity.AuthUserEntity;
import com.github.hodcko.multy.model.AuthUser;

public class AuthUserConverter {

    public static AuthUser fromEntity(AuthUserEntity authUserEntity) {
        if (authUserEntity == null) {
            return null;
        }
        return new AuthUser(
                authUserEntity.getLogin(),
                authUserEntity.getPassword(),
                authUserEntity.getRole(),
                authUserEntity.getUserId());
    }

    public static AuthUserEntity toEntity(AuthUser authUser) {
        if (authUser == null) {
            return null;
        }
        final AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setUserId(authUser.getUserId());
        authUserEntity.setLogin(authUser.getLogin());
        authUserEntity.setPassword(authUser.getPassword());
        authUserEntity.setRole(authUser.getRole());
        return authUserEntity;
    }
}
