package com.somesh.loanplanmanagement.loanplans.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somesh.loanplanmanagement.loanplans.entity.JwtRequest;
import com.somesh.loanplanmanagement.loanplans.entity.JwtResponse;
import com.somesh.loanplanmanagement.loanplans.entity.User;
import com.somesh.loanplanmanagement.loanplans.security.JwtHelper;
import com.somesh.loanplanmanagement.loanplans.service.CustomUserDetailsService;
import com.somesh.loanplanmanagement.loanplans.service.RoleService;
import com.somesh.loanplanmanagement.loanplans.service.UserService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private CustomUserDetailsService Custom;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    // @PostConstruct
    // public void initRoleAndUser(){
    //     userService.initRoleAndUser();
    // }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = new JwtResponse();
        response.setJwtToken(token);
        response.setUsername(userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user, user.getName());
    }    

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/roles")
    public List<com.somesh.loanplanmanagement.loanplans.entity.Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/role/{name}")
    public com.somesh.loanplanmanagement.loanplans.entity.Role getRoleByName(@PathVariable String name) {
        return roleService.findByName(name);
    }

    @GetMapping("/user/{email}")    
    public UserDetails getUserByEmail(@PathVariable String email) {
        return Custom.loadUserByUsername(email);
    }

}

