package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/rest/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }
    @GetMapping("auth/whoami")
    public User whoAmI(){return userService.whoAmI();}

}
