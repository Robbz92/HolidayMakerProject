package com.example.demo.controllers;

import com.example.demo.entities.City;
import com.example.demo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/getCity")
    public List<City> getAll(){ return cityService.getAll(); }

    @GetMapping("/getCityForCountry/{id}")
    public List<City> getById(@PathVariable Long id){ return cityService.getByCountry(id); }

    @GetMapping("citySearch/{phrase}")
    public List<City> getByName(@PathVariable String phrase){ return cityService.getByPhrase(phrase); }
}
