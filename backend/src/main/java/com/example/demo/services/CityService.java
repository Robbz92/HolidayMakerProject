package com.example.demo.services;

import com.example.demo.entities.City;
import com.example.demo.repositories.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepo cityRepo;

    public List<City> getAll() {
        return cityRepo.findAll();
    }
}
