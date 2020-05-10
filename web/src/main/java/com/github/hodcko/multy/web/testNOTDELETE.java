package com.github.hodcko.multy.web;


import com.github.hodcko.multy.dao.DaoAuthUser;
import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.dao.DaoGradebook;
import com.github.hodcko.multy.dao.impl.DaoAuthUserDefault;
import com.github.hodcko.multy.dao.impl.DaoCursDefault;
import com.github.hodcko.multy.dao.impl.DaoGradebookDefault;
import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.UserType;
import org.hibernate.Session;


public class testNOTDELETE {
    public static void main(String[] args) {

        DaoAuthUser daoAuthUser = DaoAuthUserDefault.getInstance();
        System.out.println(daoAuthUser.getLoginByPassword("Hodsdsddcko36"));







    }
}
