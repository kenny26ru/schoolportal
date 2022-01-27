package com.kataacademy.schoolportal.secutity.handlers;

import com.kataacademy.schoolportal.secutity.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User userDetails = (User) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        if (userDetails.hasRole("DIRECTOR")) {
            redirectURL = "director";
        } else if (userDetails.hasRole("HEAD_TEACHER")) {
            redirectURL = "head-teacher";
        } else if (userDetails.hasRole("TEACHER")) {
            redirectURL = "teacher";
        } else if (userDetails.hasRole("PUPIL")) {
            redirectURL = "pupil";
        }
        response.sendRedirect(redirectURL);
    }
}

