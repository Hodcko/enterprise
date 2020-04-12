package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.IDaoCurs;
import com.github.hodcko.multy.dao.impl.DaoCursManager;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.DTOGroup;
import com.github.hodcko.multy.service.IServiceCurs;

import java.sql.Date;
import java.util.List;

public class ServiceCurs implements IServiceCurs {
    private IDaoCurs iDaoCurs = DaoCursManager.getInstance();

    private static volatile IServiceCurs instance;

    public static IServiceCurs getInstance(){
        IServiceCurs localInstance = instance;
        if(localInstance == null){
            synchronized (IServiceCurs.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceCurs();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Curs createCurs(String name, Date start, Date end){
        return iDaoCurs.createCurs(name, start, end);
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
        return iDaoCurs.getCurs(curs_id);
    }

    @Override
    public List<DTOGroup> getMyStudents(int curs_id) {
        return iDaoCurs.getMyStudents(curs_id);
    }
}
