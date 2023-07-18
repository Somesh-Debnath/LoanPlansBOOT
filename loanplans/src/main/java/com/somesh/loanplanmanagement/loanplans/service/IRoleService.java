package com.somesh.loanplanmanagement.loanplans.service;

import com.somesh.loanplanmanagement.loanplans.entity.Role;

public interface IRoleService {
    Role findByName(String name);
}
