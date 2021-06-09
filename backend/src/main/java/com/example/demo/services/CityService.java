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


    /*
    kollar alla städer som tillhör ett CountryID
     */
    public List<City> getByCountry(Long id) { return cityRepo.getByCountryId(id); }

    /*
    tar emot en fras som ska matcha med en stad.
    Visar alla hotell som matchar denna stad.
     */
    public List<Hotel> getByPhrase(String phrase, String fromDate, String toDate, double size, long room) {
        List<City> cityList = new ArrayList<>();
        List<Hotel> hotelList = new ArrayList<>();
        if(cityRepo.findByName(phrase) != null){
            System.out.println(cityRepo.findByName(phrase));
            cityList.add(cityRepo.findByName(phrase));

            for(City city : cityList){
                hotelList.addAll(hotelService.getByCity(city.getId(), fromDate, toDate, size, room));
            }
        }
        return hotelList;
    }

}
