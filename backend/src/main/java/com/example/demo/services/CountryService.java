package com.example.demo.services;

import com.example.demo.entities.City;
import com.example.demo.entities.Country;
import com.example.demo.repositories.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private CityService cityService;

    public List<Country> getAll() {
        List<Country> countryList = countryRepo.findAll();
        return countryList;
    }

    public List<City> getByPhrase(String phrase) {
        List<Country> countryList = new ArrayList<>();
        List<City> cityList = new ArrayList<>();

        if(countryRepo.findByName(phrase) != null){
            System.out.println(countryRepo.findByName(phrase));
            countryList.add(countryRepo.findByName(phrase));

            for(Country country : countryList){
                cityList.addAll(cityService.getByCountry(country.getId()));
            }
        }

        return cityList;
    }

    public Optional<Country> getById(Long id) { return countryRepo.findById(id); }
}
