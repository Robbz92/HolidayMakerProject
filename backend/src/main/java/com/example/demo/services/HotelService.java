package com.example.demo.services;

import com.example.demo.entities.Hotel;
import com.example.demo.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HotelService {
    @Autowired
    private HotelRepo hotelRepo;

    public List<Map> getByPhrase(String phrase) {
        List<Map> hotelList = new ArrayList<>();
        if(hotelRepo.findDescriptionByName(phrase) != null){
            hotelList.addAll(hotelRepo.findDescriptionByName(phrase));
        }
        return hotelList;
    }

    public List<Hotel> getByCity(Long id) { return hotelRepo.getByCityId(id); }
}
