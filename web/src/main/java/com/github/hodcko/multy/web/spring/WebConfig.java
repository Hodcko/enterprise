package com.github.hodcko.multy.web.spring;

import com.github.hodcko.multy.service.config.ServiceConfig;

import com.github.hodcko.multy.web.controller.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public Validation validation(){
        return new Validation(serviceConfig.serviceIsExist(), serviceConfig.serviceValidation(), serviceConfig.serviceAuthUser());
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
        return new PersonalAreaEntry(serviceConfig.serviceCurs(), serviceConfig.serviceGradebook());
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
        return new CursFactory(serviceConfig.serviceCurs(),serviceConfig.serviceAuthUser(), serviceConfig.serviceGradebook());
    }

    @Bean
    public EntryFromStartPage entryFromStartPage(){
        return new EntryFromStartPage(serviceConfig.securityService(), serviceConfig.serviceCurs(),
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
    public FileManager fileManager(){
        return new FileManager();
    }

    @Bean
    public Login loginController(){
        return new Login(serviceConfig.serviceAuthUser());
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return new CommonsMultipartResolver();
    }



    @Bean
    public UrlBasedViewResolver tilesViewResolver(){
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer(){
        final TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
        return tilesConfigurer;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:i18n/messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Bean
    public CookieLocaleResolver localeResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("ru"));
        resolver.setCookieName("LocaleCookie");
        resolver.setCookieMaxAge(-1);
        return resolver;
    }
}
