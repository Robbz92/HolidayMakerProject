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

    @RequestMapping("/getRoomOnDate/{hotelID}/{dateOne}/{dateTwo}")
    private List<Map> getRooms(@PathVariable String dateOne, @PathVariable String dateTwo, @PathVariable long hotelID){
        return roomService.getFreeRooms(dateOne, dateTwo, hotelID);
    }

    @GetMapping("/getRooms")
    public List<Room> getAllRooms(){
        return roomService.getAll();
    }
}
