package com.somesh.loanplanmanagement.loanplans.service;

import java.util.List;

import com.somesh.loanplanmanagement.loanplans.dto.UserRegistrationDto;
import com.somesh.loanplanmanagement.loanplans.entity.User;

public interface IUserService {
    User saveUser(User user, String name);
    User fetchUserByEmail(String email);
    List<User> getAllUsers();
    
}
