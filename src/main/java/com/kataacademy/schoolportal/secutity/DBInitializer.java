package com.kataacademy.schoolportal.secutity;

import com.kataacademy.schoolportal.secutity.enums.ERole;
import com.kataacademy.schoolportal.secutity.models.Role;
import com.kataacademy.schoolportal.secutity.models.User;
import com.kataacademy.schoolportal.secutity.securityconfig.WebSecurityConfig;
import com.kataacademy.schoolportal.secutity.services.RoleService;
import com.kataacademy.schoolportal.secutity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInitializer {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    WebSecurityConfig webSecurityConfig;

    @PostConstruct
    public void initializer() {
        Set<Role> roleSet = new HashSet<>();
        Role director = new Role(ERole.ROLE_DIRECTOR);
        Role headTeacher = new Role(ERole.ROLE_HEAD_TEACHER);
        Role teacher = new Role(ERole.ROLE_TEACHER);
        Role pupil = new Role(ERole.ROLE_PUPIL);
        roleSet.add(director);

        roleService.saveRole(director);
        roleService.saveRole(headTeacher);
        roleService.saveRole(teacher);
        roleService.saveRole(pupil);

        User hren = new User();
        hren.setUsername("User");
        hren.setPassword(webSecurityConfig.passwordEncoder().encode("User"));
        hren.setRoles(roleSet);

        userService.saveUser(hren);
    }
}
