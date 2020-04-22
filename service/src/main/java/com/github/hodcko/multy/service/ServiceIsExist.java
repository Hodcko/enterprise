package com.github.hodcko.multy.service;


import com.github.hodcko.multy.model.UserType;

public interface ServiceIsExist {

    boolean isExist(String email, UserType userType);

}
