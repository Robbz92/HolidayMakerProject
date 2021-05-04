package com.example.demo.services;

import com.example.demo.entities.City;
import com.example.demo.entities.Country;
import com.example.demo.entities.Hotel;
import com.example.demo.repositories.CityRepo;
import com.example.demo.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private HotelService hotelService;

    public List<City> getAll() {
        return cityRepo.findAll();
    }

    public List<City> getByCountry(Long id) { return cityRepo.getByCountryId(id); }

    public List<Hotel> getByPhrase(String phrase) {
        List<City> cityList = new ArrayList<>();
        List<Hotel> hotelList = new ArrayList<>();
        if(cityRepo.findByName(phrase) != null){
            System.out.println(cityRepo.findByName(phrase));
            cityList.add(cityRepo.findByName(phrase));

            for(City city : cityList){
                hotelList.addAll(hotelService.getByCity(city.getId()));
            }
        }
        return hotelList;
    }

    public Optional<City> getById(Long id) {
        return cityRepo.findById(id);
    }
}
