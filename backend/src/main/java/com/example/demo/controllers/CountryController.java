package com.example.demo.controllers;

import com.example.demo.entities.Country;
import com.example.demo.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/getCountry")
    public List<Country> getAll(){ return countryService.getAll(); }

    @GetMapping("/countrySearch/{phrase}")
    public List<Country> getByName(@PathVariable String phrase){ return countryService.getByPhrase(phrase); }
}
