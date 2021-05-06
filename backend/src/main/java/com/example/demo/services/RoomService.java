package com.example.demo.services;

import com.example.demo.entities.Country;
import com.example.demo.entities.Room;
import com.example.demo.repositories.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {
    @Autowired
    private RoomRepo roomRepo;

    public List<Map> getFreeRooms(String dateOne, String dateTwo, long hotelID) {
        return roomRepo.findFreeRoomsOnDate(hotelID,dateOne, dateTwo);
    }

    public List<Room> getAll() {
        return roomRepo.findAll();
    }
}
