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
public class UserController {
    @Autowired
    private UserService userService;
    /*
    tar emot registrerings uppgifter
     */
    @PostMapping("rest/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }
    /*
    kollar vem som är inloggad
     */
    @GetMapping("rest/whoami")
    public User whoAmI(){return userService.whoAmI();}

    @GetMapping ("auth/allMyBooknings")
    public List<Map> getAllMyBookings(){ return userService.allMyBookings();}

    @GetMapping("auth/bookingById/{bookingId}")
    public List<Map> getBookingById(@PathVariable long bookingId){return userService.bookingById(bookingId);}

    @PostMapping("auth/newReview")
    public Review addReview(@RequestBody Review review){return userService.addReview(review);}

    @GetMapping("auth/bookedRoomsById/{bookingId}")
    public List<Map> getBookedRooms(@PathVariable long bookingId){return userService.bookedRoomsById(bookingId);}


}
