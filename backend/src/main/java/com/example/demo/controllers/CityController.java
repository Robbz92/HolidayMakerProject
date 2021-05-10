package com.example.demo.controllers;

import com.example.demo.entities.City;
import com.example.demo.entities.Hotel;
import com.example.demo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class CityController {
    @Autowired
    private CityService cityService;
    /*
    hämtar alla städer i ett land baserat på CountryID
     */
    @GetMapping("/getCityForCountry/{id}")
    public List<City> getByCountry(@PathVariable Long id){ return cityService.getByCountry(id); }
    /*
    Hämtar alla städer baserat på fras(phrase)
     */
    @GetMapping("citySearch/{phrase}")
    public List<Hotel> getByName(@PathVariable String phrase){ return cityService.getByPhrase(phrase); }
}
