package com.example.demo.controllers;

import com.example.demo.entities.City;
import com.example.demo.entities.Hotel;
import com.example.demo.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotelInformation/")
    public List<Hotel> getHotelInformation(){
        return hotelService.getAll();
    }

    @GetMapping("/hotelInformation/{id}")
    public Optional<Hotel> getHotelInformationID(@PathVariable long id){return hotelService.getAllById(id);}

    @GetMapping("/getHotelForCity/{id}")
    public List<Hotel> getByCountry(@PathVariable Long id){ return hotelService.getByCity(id); }

    @GetMapping("hotelSearch/{phrase}")
    public List<Hotel> getByName(@PathVariable String phrase){ return hotelService.getByPhrase(phrase); }
}