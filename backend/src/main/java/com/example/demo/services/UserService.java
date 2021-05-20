package com.example.demo.services;

import com.example.demo.configs.MyUserDetailService;
import com.example.demo.entities.Review;
import com.example.demo.entities.User;

import com.example.demo.repositories.HotelRepo;
import com.example.demo.repositories.ReviewRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private MyUserDetailService detailService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private HotelRepo hotelRepo;

    /*
    skickar informationen vidare till MyUserDetailService
     */
    public User register (User user){

        return detailService.registerUser(user);
    }
    /*
    kollar i repot på email vem som är inloggad
     */
    public User whoAmI(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepo.findByEmail(email);
    }

    public List<Map> allMyBookings() {
        User user = whoAmI();
        long userId = user.getId();
        return userRepo.getAllMyBookings(userId);
    }

    public List<Map> bookingById(long bookingId) {
        return userRepo.getBookingById(bookingId);
    }

    public Review addReview(Review review) {
        User user = whoAmI();
        long user_id = user.getId();
        long hotel_id = review.getHotel_id();

        if(userRepo.existsById(user_id) && hotelRepo.existsById(hotel_id)){
            Review newReview = new Review(review.getText(), review.getScore(), user_id, hotel_id);

            reviewRepo.save(newReview);

            return newReview;
        }
        return null;
    }
}
