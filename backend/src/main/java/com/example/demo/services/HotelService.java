package com.example.demo.services;
import com.example.demo.entities.Hotel;
import com.example.demo.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepo hotelRepo;

    public List<Hotel> getByPhrase(String phrase) {
        List<Hotel> hotelList = new ArrayList<>();
        if(hotelRepo.findByName(phrase) != null){
            hotelList.addAll(hotelRepo.findByName(phrase));
            for(Hotel hotel : hotelList){
                hotel.setComfortList(getComforts(hotel.getId()));
                hotel.setPrice(getPrice(hotel.getId()));
            }
        }
        return hotelList;
    }

    public List<Hotel> getByCity(Long id) {
        List<Hotel> hotelList = hotelRepo.getByCityId(id);
        for(Hotel hotel : hotelList){
            hotel.setComfortList(getComforts(hotel.getId()));
            hotel.setPrice(getPrice(hotel.getId()));
        }
        return hotelList;
    }

    private List<String> getComforts(Long id){
        return hotelRepo.comfortsPerHotel(id);
    }

    private int getPrice(Long id){
        return hotelRepo.cheapestPrice(id);
    }
}
