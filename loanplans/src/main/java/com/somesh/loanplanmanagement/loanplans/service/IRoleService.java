package com.somesh.loanplanmanagement.loanplans.service;

import java.util.List;

import com.somesh.loanplanmanagement.loanplans.entity.Role;

public interface IRoleService {
    Role findByName(String name);
    List<Role> getAllRoles();
}
