package com.example.demo.services;

import com.example.demo.entities.City;
import com.example.demo.entities.Hotel;
import com.example.demo.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelService {
    @Autowired
    private HotelRepo hotelRepo;

    public List<Hotel> getByPhrase(String phrase) {
        List<Hotel> hotelList = new ArrayList<>();
        if(hotelRepo.findByName(phrase) != null){
            hotelList.addAll(hotelRepo.findByName(phrase));
            for(Hotel hotel : hotelList){
                hotel.setComfortList(getComfortsForCard(hotel.getId()));
                hotel.setAttractionList(getAttractionsForCard(hotel.getCityId()));
                hotel.setPrice(getPrice(hotel.getId()));
                hotel.setPlaceName(getPlaceName(hotel.getId()));
            }
        }
        return hotelList;
    }

    public List<Hotel> getByCity(Long id) {
        List<Hotel> hotelList = hotelRepo.getByCityId(id);
        for(Hotel hotel : hotelList){
            for(Map map : attractionById(hotel.getId())){

            }
            hotel.setComfortList(getComfortsForCard(hotel.getId()));
            hotel.setAttractionList(getAttractionsForCard(hotel.getCityId()));
            hotel.setPrice(getPrice(hotel.getId()));
            hotel.setPlaceName(getPlaceName(hotel.getId()));
        }
        return hotelList;
    }

    public List<Map> hotelById(Long id) {
         List<Map> countryCity = hotelRepo.infoHotelById(id);
      return countryCity;
    }

    public List<Map> cityById(Long id) {
        List<Map> hotelInfo = hotelRepo.theInfo(id);
        return  hotelInfo;
    }

    public List<Map> attractionById(Long id) {
        List<Map> attractions = hotelRepo.allAttractions(id);
        return attractions;
    }

    public List<Map> comfortsById(Long id) {
        List<Map> allComfortsById = hotelRepo.hotelsComforts(id);
        return allComfortsById;
    }


    //Martins speciella listor
    private int getPrice(Long id){
        return hotelRepo.cheapestPrice(id);
    }
    private List<String> getComfortsForCard(Long id){
        return hotelRepo.comfortsPerHotel(id);
    }
    private List<String> getAttractionsForCard(Long id){
        return hotelRepo.attractionsPerHotel(id);
    }
    private String getPlaceName(Long id){
        String placeName = hotelRepo.placeName(id);
        String[] newName = placeName.split(",");
        placeName = newName[0] + ", " + newName[1];
        return placeName;
    }
}
