package com.example.demo.services;

import com.example.demo.entities.City;
import com.example.demo.entities.Country;
import com.example.demo.entities.Hotel;
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

    @Autowired HotelService hotelService;

/*
    Skapar Country lista , City lista och hotel lista
 */
    public List<Hotel> getByPhrase(String phrase, String fromDate, String toDate, double size, long room) {
        List<Country> countryList = new ArrayList<>();
        List<City> cityList = new ArrayList<>();
        List<Hotel> hotelList = new ArrayList<>();
            /*
            om CountryPhrase matchar med databasen,
            lägg till landet i CountryList
             */
        if(countryRepo.findByName(phrase) != null){
            System.out.println(countryRepo.findByName(phrase));
            countryList.add(countryRepo.findByName(phrase));
            /*
            lägger till alla städer i det landet som matchades
            och lägger dem i cityList
             */
            for(Country country : countryList){
                cityList.addAll(cityService.getByCountry(country.getId()));
            }
            /*
            lägger vi till alla hotell som matchar staden/städerna
            som läggs till i cityList,
            lägger vi till i hotelList
             */
            for(City city : cityList){
                hotelList.addAll(hotelService.getByCity(city.getId(), fromDate, toDate, size, room));
            }
        }


        return hotelList;
    }

}
