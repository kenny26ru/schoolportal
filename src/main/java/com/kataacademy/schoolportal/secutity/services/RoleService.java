package com.kataacademy.schoolportal.secutity.services;

import com.kataacademy.schoolportal.secutity.enums.ERole;
import com.kataacademy.schoolportal.secutity.models.Role;

import javax.management.relation.RoleNotFoundException;

public interface RoleService {
    Role findRoleByRoleName(ERole roleName) throws RoleNotFoundException;
    void saveRole(Role role);
}
