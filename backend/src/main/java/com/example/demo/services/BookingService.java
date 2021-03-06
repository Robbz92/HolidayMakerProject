package com.example.demo.services;

import com.example.demo.entities.Bookings;
import com.example.demo.repositories.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepo bookingRepo;

    public void makeBooking(Bookings bookingsObj) {
        bookingRepo.save(bookingsObj);
    }

    public int getLatestBookings() {
        return bookingRepo.findLatestID();
    }

    public void updateBooking( Bookings bookingsObject){
        System.err.println(bookingsObject);
        bookingRepo.save(bookingsObject);
    }

    public String deleteBooking(long id) {
        bookingRepo.deleteById(id);
        return "Booking has been removed";
    }

    public void updatePaymentState(long bookingsID){
        bookingRepo.updatePayment("Paid", bookingsID);
    }
}
