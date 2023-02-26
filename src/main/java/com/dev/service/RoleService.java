package com.dev.service;

import com.dev.model.Role;
import com.dev.model.RoleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String roleName) {
        Role role = roleRepository.getRoleByName(roleName);
        if (role == null) {
            Role newRole = new Role();
            newRole.setName(roleName);
            return roleRepository.save(newRole);
        }
        return role;
    }

}
