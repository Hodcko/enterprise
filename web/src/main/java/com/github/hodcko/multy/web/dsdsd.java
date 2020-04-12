package com.github.hodcko.multy.web;


import com.github.hodcko.multy.dao.IDaoAuth;
import com.github.hodcko.multy.dao.IDaoCurs;
import com.github.hodcko.multy.dao.IDaoStudent;
import com.github.hodcko.multy.dao.impl.DaoCursManager;
import com.github.hodcko.multy.dao.impl.DaoStudentManager;
import com.github.hodcko.multy.dao.impl.DaoUserAuth;
import com.github.hodcko.multy.service.*;
import com.github.hodcko.multy.service.impl.*;

import java.sql.Date;


public class dsdsd {
    public static void main(String[] args) {


        IDaoStudent iDaoStudent = DaoStudentManager.getInstance();
        iDaoStudent.saveStudent("aleswx", "23s23", "2323d2wwe3sd23@gmail.com", 11, 1);


    }
}
