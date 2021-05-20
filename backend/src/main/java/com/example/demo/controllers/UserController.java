package com.example.demo.controllers;

import com.example.demo.entities.Review;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    /*
    tar emot registrerings uppgifter
     */
    @PostMapping("/rest/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }
    /*
    kollar vem som är inloggad
     */
    @GetMapping("auth/whoami")
    public User whoAmI(){return userService.whoAmI();}

    @GetMapping ("rest/allMyBooknings")
    public List<Map> getAllMyBookings(){ return userService.allMyBookings();}

    @GetMapping("/rest/bookingById/{bookingId}")
    public List<Map> getBookingById(@PathVariable long bookingId){return userService.bookingById(bookingId);}

    @PostMapping("/rest/newReview")
    public Review addReview(@RequestBody Review review){return userService.addReview(review);}
}
