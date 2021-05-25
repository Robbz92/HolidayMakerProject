package com.example.demo.services;

import com.example.demo.entities.BookedRooms;
import com.example.demo.repositories.BookedRoomRepo;
import com.example.demo.repositories.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookedRoomService {
    @Autowired
    private BookedRoomRepo bookedRoomRepo;
    @Autowired
    private BookingRepo bookingRepo;

    public void bookRoom(BookedRooms bookedRoomsObj) {

        bookedRoomRepo.save(bookedRoomsObj);
    }

    public void editBooking(BookedRooms bookedRoomsObj) {

        bookedRoomRepo.save(bookedRoomsObj);

    }
}
