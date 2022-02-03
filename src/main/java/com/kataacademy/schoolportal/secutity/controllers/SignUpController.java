package com.kataacademy.schoolportal.secutity.controllers;

import com.kataacademy.schoolportal.secutity.enums.ERole;
import com.kataacademy.schoolportal.secutity.models.Role;
import com.kataacademy.schoolportal.secutity.models.User;
import com.kataacademy.schoolportal.secutity.request.SignupRequest;
import com.kataacademy.schoolportal.secutity.response.MessageResponse;
import com.kataacademy.schoolportal.secutity.services.RoleService;
import com.kataacademy.schoolportal.secutity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
public class SignUpController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder encoder;

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
                    case "ROLE_DIRECTOR":
                        Role directorRole = null;
                        try {
                            directorRole = roleService.findRoleByRoleName(ERole.ROLE_DIRECTOR);
                        } catch (RoleNotFoundException e) {
                            e.printStackTrace();
                        }
                        roleSet.add(directorRole);

                        break;
                    case "ROLE_HEAD_TEACHER":
                        Role headTeacherRole = null;
                        try {
                            headTeacherRole = roleService.findRoleByRoleName(ERole.ROLE_HEAD_TEACHER);
                        } catch (RoleNotFoundException e) {
                            e.printStackTrace();
                        }
                        roleSet.add(headTeacherRole);

                        break;
                    case "ROLE_TEACHER":
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

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("Пользователь успешно зарегистрирован!"));
    }
}
