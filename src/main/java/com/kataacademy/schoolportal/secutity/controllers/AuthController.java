package com.kataacademy.schoolportal.secutity.controllers;

import com.kataacademy.schoolportal.secutity.enums.ERole;
import com.kataacademy.schoolportal.secutity.models.Role;
import com.kataacademy.schoolportal.secutity.models.User;
import com.kataacademy.schoolportal.secutity.jwt.JWTUtils;
import com.kataacademy.schoolportal.secutity.request.LoginRequest;
import com.kataacademy.schoolportal.secutity.request.SignupRequest;
import com.kataacademy.schoolportal.secutity.response.JwtResponse;
import com.kataacademy.schoolportal.secutity.response.MessageResponse;
import com.kataacademy.schoolportal.secutity.services.RoleService;
import com.kataacademy.schoolportal.secutity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JWTUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(user);

        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getUsername(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Ошибка: Выбранное имя уже используется!"));
        }

        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> signUpRequestRole = signUpRequest.getRole();
        Set<Role> roleSet = new HashSet<>();

        if (signUpRequestRole == null) {
            Role userRole = null;
            try {
                userRole = roleService.findRoleByRoleName(ERole.ROLE_PUPIL);
            } catch (RoleNotFoundException e) {
                e.printStackTrace();
            }
            roleSet.add(userRole);
        } else {
            signUpRequestRole.forEach(role -> {
                switch (role) {
                    case "director":
                        Role directorRole = null;
                        try {
                            directorRole = roleService.findRoleByRoleName(ERole.ROLE_DIRECTOR);
                        } catch (RoleNotFoundException e) {
                            e.printStackTrace();
                        }
                        roleSet.add(directorRole);

                        break;
                    case "head_teacher":
                        Role headTeacherRole = null;
                        try {
                            headTeacherRole = roleService.findRoleByRoleName(ERole.ROLE_HEAD_TEACHER);
                        } catch (RoleNotFoundException e) {
                            e.printStackTrace();
                        }
                        roleSet.add(headTeacherRole);

                        break;
                    case "teacher":
                        Role teacherRole = null;
                        try {
                            teacherRole = roleService.findRoleByRoleName(ERole.ROLE_TEACHER);
                        } catch (RoleNotFoundException e) {
                            e.printStackTrace();
                        }
                        roleSet.add(teacherRole);

                        break;
                    default:
                        Role pupilRole = null;
                        try {
                            pupilRole = roleService.findRoleByRoleName(ERole.ROLE_PUPIL);
                        } catch (RoleNotFoundException e) {
                            e.printStackTrace();
                        }
                        roleSet.add(pupilRole);
                }
            });
        }

        user.setRoles(roleSet);
        userService.saveUser(user);

        return ResponseEntity.ok(new MessageResponse("Пользователь успешно зарегистрирован!"));
    }
}
