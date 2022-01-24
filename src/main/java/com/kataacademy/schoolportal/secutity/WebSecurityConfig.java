package com.kataacademy.schoolportal.secutity;

import com.kataacademy.schoolportal.secutity.enums.ERole;
import com.kataacademy.schoolportal.secutity.jwt.AuthEntryPointJWT;
import com.kataacademy.schoolportal.secutity.jwt.AuthTokenFilter;
import com.kataacademy.schoolportal.secutity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Autowired
    private AuthEntryPointJWT unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/", "/login", "/logout", "/school-project/home", "/VAADIN/**").permitAll()
                .antMatchers("/school-project/director/**").hasAuthority(String.valueOf(ERole.ROLE_DIRECTOR))
                .antMatchers("/school-project/head-teacher/**").hasAuthority(String.valueOf(ERole.ROLE_HEAD_TEACHER))
                .antMatchers("/school-project/teacher/**").hasAuthority(String.valueOf(ERole.ROLE_TEACHER))
                .antMatchers("/school-project/pupil/**").hasAuthority(String.valueOf(ERole.ROLE_PUPIL))
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
