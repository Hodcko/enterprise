package com.github.hodcko.multy.service;


import com.github.hodcko.multy.model.UserType;

public interface ServiceGetIdByEmail {

    int getId(String email, UserType userType);

}
