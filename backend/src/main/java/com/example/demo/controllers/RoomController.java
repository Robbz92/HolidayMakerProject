package com.example.demo.controllers;

import com.example.demo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
