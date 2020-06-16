package com.github.hodcko.multy.dao.repository;

import com.github.hodcko.multy.dao.entity.AuthUserEntity;
import com.github.hodcko.multy.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AuthUserRepository extends JpaRepository<AuthUserEntity, Integer> {

    AuthUserEntity findByPassword(String password);

    AuthUserEntity findByLoginAndPassword(String login, String password);

    int deleteByUserIdAndRole(int userId, UserType role);

    @Transactional
    @Modifying
    @Query(value = "update AuthUserEntity a set a.password = :newPassword where a.login = :login and a.password = :password")
    int changePassword(@Param("login") String login, @Param("password") String password, @Param("newPassword") String newPassword);

}
