package com.example.demo.services;

import com.example.demo.entities.Country;
import com.example.demo.repositories.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepo countryRepo;

    public List<Country> getAll() {
        return countryRepo.findAll();
    }
}
