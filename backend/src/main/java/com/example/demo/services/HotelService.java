package com.example.demo.services;
import com.example.demo.entities.Hotel;
import com.example.demo.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
