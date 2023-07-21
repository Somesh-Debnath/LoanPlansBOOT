package com.somesh.loanplanmanagement.loanplans.service;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.somesh.loanplanmanagement.loanplans.entity.Role;
import com.somesh.loanplanmanagement.loanplans.entity.User;
import com.somesh.loanplanmanagement.loanplans.repository.RoleRepository;
import com.somesh.loanplanmanagement.loanplans.repository.UserRepository;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setName("Admin");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("User");
        roleRepository.save(userRole);

        User adminUser = new User();
        adminUser.setName("Admin");
        adminUser.setEmail("admin@edu");
        adminUser.setPassword(bcryptEncoder.encode("admin"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userRepository.save(adminUser);
    }

    @Override
    public User saveUser(User user, String name ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findRoleByName(name);
        if (role == null) {
            throw new IllegalArgumentException("Invalid role name: " + name);
        }

        user.setRoles(Collections.singleton(role));
        return userRepository.save(user);
    }


    @Override
    public User fetchUserByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchUserByEmail'");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

}
