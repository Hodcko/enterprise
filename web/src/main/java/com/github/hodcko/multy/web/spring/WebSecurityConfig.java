package com.github.hodcko.multy.web.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/studentReg","/teacherReg", "/enterFromStartPage", "/loginAfterRegistration",
                        "/loginFromStartPage", "/teacher", "/student","/validation", "/upload", "/customLogout").permitAll()
                .antMatchers("/study").hasRole("STUDENT")
                .anyRequest().authenticated();
    }
}
