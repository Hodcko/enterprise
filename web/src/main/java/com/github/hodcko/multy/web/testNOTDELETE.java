package com.github.hodcko.multy.web;


import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.dao.impl.DaoCursDefault;
import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.Student;
import org.hibernate.Session;


public class testNOTDELETE {
    public static void main(String[] args) {

        DaoCurs daoCurs = DaoCursDefault.getInstance();
        System.out.println(daoCurs.getMyStudents(1,2));







    }
}
