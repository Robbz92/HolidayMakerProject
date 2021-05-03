package com.example.demo.services;

import com.example.demo.entities.Country;
import com.example.demo.repositories.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CountryService {
    @Autowired
    private CountryRepo countryRepo;

    public List<Country> getAll() {
        List<Country> countryList = countryRepo.findAll();
        return countryList;
    }

    public List<Country> getByPhrase(String phrase) {
        List<Country> countryList = new ArrayList<>();
        if(countryRepo.findByName(phrase).getName() != ""){
            System.out.println(countryRepo.findByName(phrase));
            countryList.add(countryRepo.findByName(phrase));
        }
        return countryList;
    }
}
