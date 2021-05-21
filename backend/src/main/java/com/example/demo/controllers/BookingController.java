package com.example.demo.controllers;

import com.example.demo.entities.Bookings;
import com.example.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Robin Persson,
 */
@RestController
@RequestMapping("/rest")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    // Skapar en entry i databasen med en bokning i bookings
    @PostMapping("/makeBooking")
    public void makeBooking(@RequestBody Bookings bookingsObj) {
        bookingService.makeBooking(bookingsObj);
    }

    // hämtar alla bokningar från bookings + bookedRooms i en lista.
    @GetMapping("/getLatestBookings")
    public int getLatestBookings() {
        return bookingService.getLatestBookings();
    }

    // Delete booking
    @DeleteMapping("/deleteBooking/{id}")
    public String deleteBooking(@PathVariable long id) {
        return bookingService.deleteBooking(id);
    }

}