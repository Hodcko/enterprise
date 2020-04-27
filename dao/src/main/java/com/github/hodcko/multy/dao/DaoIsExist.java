package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.model.UserType;

public interface DaoIsExist {

    boolean isExist(String email, UserType userType);


}
