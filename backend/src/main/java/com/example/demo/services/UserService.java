package com.example.demo.services;

import com.example.demo.configs.MyUserDetailService;
import com.example.demo.entities.User;

import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MyUserDetailService detailService;

    @Autowired
    private UserRepo userRepo;

    public User register (User user){

        return detailService.registerUser(user);
    }
    public User whoAmI(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepo.findByEmail(email);
    }

}
