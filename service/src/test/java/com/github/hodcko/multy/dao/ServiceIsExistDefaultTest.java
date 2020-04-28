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
    private static DaoStudent daoStudent;

    @InjectMocks
    private static ServiceIsExist serviceIsExist;


    @BeforeAll
    public static void createInstance() {
        serviceIsExist = ServiceIsExistDefault.getInstance();
    }

    @Test
    void isExistTest(){
        String email = "hodckoq@gmail.com";
        when(daoStudent.isExist(email, UserType.STUDENT)).thenReturn(true);
        boolean result = serviceIsExist.isExist(email, UserType.STUDENT);
        assertTrue(result);

    }


}
