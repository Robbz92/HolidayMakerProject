package com.example.demo.controllers;

import com.example.demo.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
