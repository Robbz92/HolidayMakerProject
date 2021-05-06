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
}
