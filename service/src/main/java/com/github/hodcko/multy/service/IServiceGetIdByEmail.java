package com.github.hodcko.multy.service;

import java.sql.SQLException;

public interface IServiceGetIdByEmail {
    int getId(String email, String userType);

}
