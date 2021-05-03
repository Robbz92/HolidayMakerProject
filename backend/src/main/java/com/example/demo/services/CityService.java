package com.example.demo.services;

import com.example.demo.entities.City;
import com.example.demo.repositories.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepo cityRepo;

    public List<City> getAll() {
        return cityRepo.findAll();
    }

    public List<City> getByCountry(Long id) { return cityRepo.getByCountryId(id); }

    public List<City> getByPhrase(String phrase) {
        List<City> cityList = new ArrayList<>();
        if(cityRepo.findByName(phrase).getName() != ""){
            System.out.println(cityRepo.findByName(phrase));
            cityList.add(cityRepo.findByName(phrase));
        }
        return cityList;
    }
}
