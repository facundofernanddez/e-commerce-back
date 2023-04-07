package com.ecommerce.ecommerce.security.config;

import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v*/registration/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .loginPage("http://localhost:5173/login")
                .loginProcessingUrl("http://localhost:5173/check-login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("http://localhost:5173/home")
                .permitAll();
    }
}
