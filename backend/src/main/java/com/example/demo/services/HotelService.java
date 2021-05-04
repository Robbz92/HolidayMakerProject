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

    public List<Hotel> getAll() {
        return hotelRepo.findAll();
    }

    public Optional<Hotel> getAllById(long id) {
        return hotelRepo.findById(id);
    }

    public List<Hotel> getByPhrase(String phrase) {
        List<Hotel> hotelList = new ArrayList<>();
        if(hotelRepo.findByName(phrase) != null){
            hotelList.addAll(hotelRepo.findByName(phrase));
        }
        return hotelList;
    }

    public List<Hotel> getByCity(Long id) { return hotelRepo.getByCityId(id); }
}
