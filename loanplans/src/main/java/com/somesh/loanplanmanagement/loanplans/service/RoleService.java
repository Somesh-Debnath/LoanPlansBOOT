package com.somesh.loanplanmanagement.loanplans.service;

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
        return role;
    }

    
}
