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

    /*
    tar emot fromDate + toDate + HotelID
    skickar till repo
     */
    public List<Map> getFreeRooms(String dateOne, String dateTwo, long hotelID, double size) {
        return roomRepo.findFreeRoomsOnDate(hotelID,dateOne, dateTwo, size);
    }

    public List<Room> getAllRoomsOnHotelId(int id) {
        return roomRepo.getAllroomsOnHotelId(id);
    }
}
