package com.example.demo.controllers;

import com.example.demo.entities.Room;
import com.example.demo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class RoomController {

    @Autowired
    private RoomService roomService;
    /*
    tar emot HotelID + fromDate + toDate
    och letar efter lediga rum
     */
    @RequestMapping("/getRoomOnDate/{hotelID}/{dateOne}/{dateTwo}/{size}")
    private List<Map> getRooms(@PathVariable String dateOne, @PathVariable String dateTwo, @PathVariable long hotelID, @PathVariable double size){
        return roomService.getFreeRooms(dateOne, dateTwo, hotelID, size);
    }

}

