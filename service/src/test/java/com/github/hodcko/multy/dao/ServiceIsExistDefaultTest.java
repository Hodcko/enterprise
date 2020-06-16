package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceIsExist;
import com.github.hodcko.multy.service.impl.ServiceIsExistDefault;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceIsExistDefaultTest {

    @Mock
    private DaoStudent daoStudent;

    @Mock
    private DaoTeacher daoTeacher;

    @InjectMocks
    private ServiceIsExistDefault serviceIsExist;



    @Test
     void isExistTest(){
        String email = "hodckoq@gmail.com";
        when(daoStudent.isExist(email)).thenReturn(true);
        boolean result = serviceIsExist.isExist(email, UserType.STUDENT);
        assertTrue(result);
    }

    @Test
    void isExistTest2(){
        String email = "hodckoq@gmail.com";
        when(daoTeacher.isExist(email)).thenReturn(true);
        boolean result = serviceIsExist.isExist(email, UserType.TEACHER);
        assertTrue(result);
    }


}
