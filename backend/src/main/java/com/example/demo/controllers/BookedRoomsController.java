package com.example.demo.controllers;

import com.example.demo.entities.BookedRooms;
import com.example.demo.services.BookedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Robin Persson,
 */
@RestController
@RequestMapping("/rest")
public class BookedRoomsController {
    @Autowired
    private BookedRoomService bookedRoomService;

    // skapar en entry i dabasasen med room bokning
    @PostMapping("/bookRoom")
    public void bookRoom(@RequestBody BookedRooms bookedRoomsObj){
        bookedRoomService.bookRoom(bookedRoomsObj);
    }
}
