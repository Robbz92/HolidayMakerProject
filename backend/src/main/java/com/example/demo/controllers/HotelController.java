package com.example.demo.controllers;

import com.example.demo.entities.Hotel;
import com.example.demo.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping("/getHotelForCity/{id}")
    public List<Hotel> getByCountry(@PathVariable Long id){ return hotelService.getByCity(id); }

    @GetMapping("/hotelInfo/{id}")
    public List<Map> getHotelById(@PathVariable Long id){return hotelService.hotelById(id);}

    @GetMapping("/hotelCity/{id}")
    public List<Map> getCityById(@PathVariable Long id){return hotelService.cityById(id);}

    @GetMapping("/hotelAttraction/{id}")
    public List<Map> getAttractionById(@PathVariable Long id){return hotelService.attractionById(id);}

    @GetMapping("/hotelComforts/{id}")
    public List<Map> getComfortsById(@PathVariable Long id){return hotelService.comfortsById(id);}

    @GetMapping("hotelSearch/{phrase}")
    public List<Hotel> getByName(@PathVariable String phrase){ return hotelService.getByPhrase(phrase); }
}
