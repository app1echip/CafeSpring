package com.github.no_maids_cafe.cafe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserAuthenticationEntryPoint entryPoint;
    @Autowired
    private RequestFilter filter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] anonymous = { "/authenticate", "/register" };
        String[] loggedin = {"/menu","/profile/**","/order/**"};
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/*").hasRole("ADMIN")
            .antMatchers(loggedin).authenticated()
            .antMatchers(anonymous).permitAll()
            .anyRequest().denyAll()
            .and()
            .exceptionHandling().authenticationEntryPoint(entryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}