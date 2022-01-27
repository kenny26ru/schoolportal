package com.kataacademy.schoolportal.secutity.securityconfig;

import com.kataacademy.schoolportal.secutity.enums.ERole;

import com.kataacademy.schoolportal.secutity.handlers.AuthSuccessHandler;
import com.kataacademy.schoolportal.secutity.jwt.AuthEntryPointJWT;
import com.kataacademy.schoolportal.secutity.jwt.AuthTokenFilter;
import com.kataacademy.schoolportal.secutity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
    private UserService userService;

    @Autowired
    private AuthEntryPointJWT unauthorizedHandler;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;


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
                .requestCache().requestCache(new CustomRequestCache())
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
/*                .antMatchers("/VAADIN/**", "/HEARTBEAT/**", "/UIDL/**", "/resources/**"
                        , "/login", "/login**", "/login/**", "/manifest.json", "/icons/**",
                        "/images/**", "/registration", "/registration/**", "/grid", "/", "/logout",
                        "/school-project/home", "/signin", "/signup", "/ws/**", "/vaadinServlet/**").permitAll()*/
                .antMatchers("/school-project/director/**").hasAuthority(ERole.ROLE_DIRECTOR.name())
                .antMatchers("/school-project/head-teacher/**").hasAuthority(String.valueOf(ERole.ROLE_HEAD_TEACHER))
                .antMatchers("/school-project/teacher/**").hasAuthority(String.valueOf(ERole.ROLE_TEACHER))
                .antMatchers("/school-project/pupil/**").hasAuthority(String.valueOf(ERole.ROLE_PUPIL))
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/signin").successHandler(authSuccessHandler).permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                // Vaadin Flow static resources
                "/VAADIN/**",

                // the standard favicon URI
                "/favicon.ico",

                // the robots exclusion standard
                "/robots.txt",

                // web application manifest
                "/manifest.webmanifest",
                "/sw.js",
                "/offline-page.html",

                // icons and images
                "/icons/**",
                "/images/**",

                // (development mode) static resources
                "/frontend/**",

                // (development mode) webjars
                "/webjars/**",

                // (development mode) H2 debugging console
                "/h2-console/**",

                // (production mode) static resources
                "/frontend-es5/**", "/frontend-es6/**"

                , "/signin", "/signup");
    }
}

