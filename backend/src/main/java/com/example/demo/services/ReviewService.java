package com.example.demo.services;

import com.example.demo.entities.Review;
import com.example.demo.entities.User;
import com.example.demo.repositories.ReviewRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private UserService userService;
    /*
    tar emot HotelID
    och matchar reviews
    returnerar lista med reviews
     */
    public List<Map> getReviewsByHotelId(long id) {
        return reviewRepo.getReviews(id);
    }
    public List<Integer> getHotelidList(long id) {return reviewRepo.findReviews(id);}
}
