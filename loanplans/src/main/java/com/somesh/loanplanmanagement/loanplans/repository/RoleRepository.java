package com.somesh.loanplanmanagement.loanplans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.somesh.loanplanmanagement.loanplans.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
