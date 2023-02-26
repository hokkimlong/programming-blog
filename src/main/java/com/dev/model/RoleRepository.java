package com.dev.model;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer> {
    Role getRoleByName(String name);
}