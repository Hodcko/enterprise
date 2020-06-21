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
                .antMatchers("/").permitAll()
                .antMatchers("/studentReg").permitAll()
                .antMatchers("/teacherReg").permitAll()
                .antMatchers("/enterFromStartPage").permitAll()
                .antMatchers("/loginAfterRegistration").permitAll()
                .antMatchers("/loginFromStartPage").permitAll()
                .antMatchers("/teacher").permitAll()
                .antMatchers("/student").permitAll()
                .antMatchers("/validation").permitAll()
                .antMatchers("/upload").permitAll()
                .antMatchers("/study").hasRole("STUDENT")
                .anyRequest().authenticated();
    }
}
