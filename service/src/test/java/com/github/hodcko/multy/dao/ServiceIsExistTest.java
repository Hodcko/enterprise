package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.service.IServiceIsExist;
import com.github.hodcko.multy.service.impl.ServiceIsExist;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceIsExistTest {

    @Mock
    private static IDaoIsExist iDaoIsExist;

    @InjectMocks
    private static IServiceIsExist iServiceIsExist;


    @BeforeAll
    public static void createInstance() {
        iServiceIsExist = ServiceIsExist.getInstance();
    }

    @Test
    void isExistTest(){
        String email = "hodckoq@gmail.com";
        String userType = "student";
        when(iDaoIsExist.isExist(email, userType)).thenReturn(true);
        boolean result = iServiceIsExist.isExist(email, userType);
        assertTrue(result);

    }


}
