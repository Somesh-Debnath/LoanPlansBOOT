package com.somesh.loanplanmanagement.loanplans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somesh.loanplanmanagement.loanplans.entity.Role;
import com.somesh.loanplanmanagement.loanplans.repository.RoleRepository;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    private RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        Role role = roleRepository.findRoleByName(name);
        System.out.println("Role: " + role);
        return role;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    
}
