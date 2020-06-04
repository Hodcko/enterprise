package com.github.hodcko.multy.web.spring;

import com.github.hodcko.multy.service.ServiceAuthUser;
import com.github.hodcko.multy.service.ServiceIsExist;
import com.github.hodcko.multy.service.ServiceValidation;
import com.github.hodcko.multy.service.config.ServiceConfig;

import com.github.hodcko.multy.web.controller.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public Registration registration(){
        return new Registration(serviceConfig.serviceIsExist(), serviceConfig.serviceValidation(), serviceConfig.serviceAuthUser());
    }

    @Bean
    public StudentRegistration studentRegistration(){
        return new StudentRegistration(serviceConfig.serviceStudent());
    }

    @Bean
    public TeacherRegistration teacherRegistration(){
        return new TeacherRegistration(serviceConfig.serviceTeacher(), serviceConfig.serviceCurs());
    }

    @Bean
    public PersonalAreaEntry personalAreaEntry(){
        return new PersonalAreaEntry(serviceConfig.serviceAuthUser(), serviceConfig.serviceGetIdByEmail(),
                serviceConfig.serviceCurs(), serviceConfig.serviceGradebook());
    }

    @Bean
    public ChangePassword changePassword(){
        return new ChangePassword(serviceConfig.securityService());
    }

    @Bean
    public Cleaner cleaner(){
        return new Cleaner();
    }

    @Bean
    public CursFactory cursFactory(){
        return new CursFactory(serviceConfig.serviceCurs(),serviceConfig.serviceAuthUser());
    }

    @Bean
    public EntryFromStartPage entryFromStartPage(){
        return new EntryFromStartPage(serviceConfig.securityService(), serviceConfig.serviceAuthUser(), serviceConfig.serviceCurs(),
                serviceConfig.serviceStudent(), serviceConfig.serviceTeacher(), serviceConfig.serviceGradebook());
    }

    @Bean
    public Logout logout(){
        return new Logout();
    }

    @Bean
    public Pagination pagination(){
        return new Pagination(serviceConfig.serviceCurs());
    }

    @Bean
    public Study study(){
        return new Study(serviceConfig.serviceGradebook(), serviceConfig.serviceCurs());
    }

    @Bean
    public Test test(){
        return new Test(serviceConfig.serviceGradebook(), serviceConfig.serviceCurs());
    }


    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
