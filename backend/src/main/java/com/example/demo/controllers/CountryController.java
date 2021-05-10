package com.example.demo.controllers;

import com.example.demo.entities.City;
import com.example.demo.entities.Country;
import com.example.demo.entities.Hotel;
import com.example.demo.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class CountryController {
    @Autowired
    private CountryService countryService;

    //depricated
    @GetMapping("/getCountry")
    public List<Country> getAll(){ return countryService.getAll(); }

    //Depricated
    @GetMapping("/getCountry/{id}")
    public Optional<Country> getById(@PathVariable Long id){ return countryService.getById(id); }

    @GetMapping("/countrySearch/{phrase}")
    public List<Hotel> getByName(@PathVariable String phrase){ return countryService.getByPhrase(phrase); }
}
