package com.example.demo.services;

import com.example.demo.entities.Review;
import com.example.demo.repositories.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    public List<Map> getReviewsByHotelId(long id) {
        List<Map> reviewsList = new ArrayList<>();

        reviewsList = reviewRepo.getReviews(id);


        return  reviewsList;
    }
}
