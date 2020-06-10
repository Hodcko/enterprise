package com.github.hodcko.multy.dao.config;




import com.github.hodcko.multy.dao.*;
import com.github.hodcko.multy.dao.impl.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
public class DaoConfig {

    private final SessionFactory sessionFactory;

    public DaoConfig(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Bean
    public DaoAuthUser daoAuthUser() {
        return new DaoAuthUserDefault(sessionFactory);
    }

    @Bean
    public DaoCurs daoCurs() {
        return new DaoCursDefault(sessionFactory);
    }

    @Bean
    public DaoGradebook daoGradebook() {
        return new DaoGradebookDefault(sessionFactory);
    }

    @Bean
    public DaoStudent daoStudent() {
        return new DaoStudentDefault();
    }

    @Bean
    public DaoTeacher daoTeacher() {
        return new DaoTeacherDefault(sessionFactory);
    }
}