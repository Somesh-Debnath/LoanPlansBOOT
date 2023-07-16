package com.somesh.loanplanmanagement.loanplans.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.somesh.loanplanmanagement.loanplans.entity.User;
import com.somesh.loanplanmanagement.loanplans.repository.UserRepository;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        //user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User fetchUserByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchUserByEmail'");
    }

    @Override
    public List<User> getAllUsers() {
       return userRepository.findAll();
    }


}
