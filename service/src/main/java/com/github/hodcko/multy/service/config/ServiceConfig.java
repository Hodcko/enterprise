package com.github.hodcko.multy.service.config;



import com.github.hodcko.multy.dao.config.DaoConfig;
import com.github.hodcko.multy.service.*;
import com.github.hodcko.multy.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(DaoConfig.class)
public class ServiceConfig {

    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }


    @Bean
    public SecurityService securityService(){
        return new SecurityServiceDefault(daoConfig.daoAuthUser());
    }

    @Bean
    public ServiceAuthUser serviceAuthUser(){
        return new ServiceAuthUserDefault(daoConfig.daoAuthUser(), daoConfig.daoStudent(), daoConfig.daoTeacher());
    }

    @Bean
    public ServiceCurs serviceCurs(){
        return new ServiceCursDefault(daoConfig.daoCurs());
    }

    @Bean
    public ServiceGetIdByEmail serviceGetIdByEmail(){
        return new ServiceGetIdByEmailDefault(daoConfig.daoTeacher(), daoConfig.daoStudent());
    }

    @Bean
    public ServiceGradebook serviceGradebook(){
        return new ServiceGradebookDefault(daoConfig.daoGradebook());
    }

    @Bean
    public ServiceIsExist serviceIsExist(){
        return new ServiceIsExistDefault(daoConfig.daoStudent(), daoConfig.daoTeacher());
    }

    @Bean
    public ServiceRegistration serviceRegistration(){
        return new ServiceRegistrationDefault(serviceStudent(), serviceTeacher(), serviceCurs());
    }

    @Bean
    public ServiceStudent serviceStudent(){
        return new ServiceStudentDefault(daoConfig.daoStudent());
    }

    @Bean
    public ServiceTeacher serviceTeacher(){
        return new ServiceTeacherDefault(daoConfig.daoTeacher());
    }

    @Bean
    public ServiceValidation serviceValidation(){
        return new ServiceValidationDefault();
    }
}