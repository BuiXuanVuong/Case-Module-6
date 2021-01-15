package com.mvc.service;

import com.mvc.model.Role;
import com.mvc.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RoleService {
    @Autowired
    private RoleRepository roleRepository;


    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    public void remove(Long id) {
        roleRepository.deleteById(id);
    }
}
