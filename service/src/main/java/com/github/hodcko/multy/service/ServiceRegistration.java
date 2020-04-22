package com.github.hodcko.multy.service;

import com.github.hodcko.multy.model.UserType;

public interface ServiceRegistration {

    boolean registration(String name, String secondName, String email, int age, UserType userType, String langType);

}
