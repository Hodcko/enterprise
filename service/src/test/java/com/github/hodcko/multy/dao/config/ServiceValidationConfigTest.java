package com.github.hodcko.multy.dao.config;

import com.github.hodcko.multy.service.ServiceValidation;
import com.github.hodcko.multy.service.impl.ServiceValidationDefault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceValidationConfigTest {
    @Bean
    public ServiceValidation serviceValidation(){
        return new ServiceValidationDefault();
    }
}
