package com.kataacademy.schoolportal.secutity.services.impl;

import com.kataacademy.schoolportal.secutity.enums.ERole;
import com.kataacademy.schoolportal.secutity.models.Role;
import com.kataacademy.schoolportal.secutity.repository.RoleRepository;
import com.kataacademy.schoolportal.secutity.services.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role findRoleByRoleName(ERole roleName) throws RoleNotFoundException {
        return roleRepository.findRoleByRoleName(roleName)
                .orElseThrow(() -> new RoleNotFoundException
                ("Заданной роли: " + roleName + " не существует"));
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
