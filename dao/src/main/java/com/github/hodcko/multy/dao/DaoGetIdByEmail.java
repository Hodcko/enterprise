package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.model.UserType;

public interface DaoGetIdByEmail {

    int getId(String email, UserType userType);
}
