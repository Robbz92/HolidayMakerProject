package com.example.demo.controllers;

import com.example.demo.entities.Review;
import com.example.demo.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    /*
    hämtar reviews baserat på HotelID
     */
    @GetMapping("/reviews/{id}")
    public List<Map> getReviews(@PathVariable long id){return reviewService.getReviewsByHotelId(id);}


}
