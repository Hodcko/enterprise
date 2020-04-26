package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.dao.impl.DaoCursDefault;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.DTOGroup;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ServiceCursDefault implements com.github.hodcko.multy.service.ServiceCurs {
    private DaoCurs daoCurs = DaoCursDefault.getInstance();

    private static volatile com.github.hodcko.multy.service.ServiceCurs instance;

    public static com.github.hodcko.multy.service.ServiceCurs getInstance(){
        com.github.hodcko.multy.service.ServiceCurs localInstance = instance;
        if(localInstance == null){
            synchronized (com.github.hodcko.multy.service.ServiceCurs.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceCursDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Curs createCurs(String name, LocalDate start, LocalDate end){
        return daoCurs.createCurs(name, start, end);
    }

    @Override
    public int getCurs_id(String langType) {
        if (langType.equalsIgnoreCase("java")){
            return 1;
        }else if(langType.equalsIgnoreCase("php")){
            return 2;
        }else if(langType.equalsIgnoreCase("c++")){
            return 3;
        }
        return 0;
    }

    @Override
    public Curs getCurs(int curs_id){
        return daoCurs.getCurs(curs_id);
    }

    @Override
    public List<DTOGroup> getMyStudents(int curs_id, int numPage) {
        return daoCurs.getMyStudents(curs_id, numPage);
    }
}
