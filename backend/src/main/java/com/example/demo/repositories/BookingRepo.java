package com.example.demo.repositories;

import com.example.demo.entities.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookingRepo extends JpaRepository<Bookings, Long> {
    @Query(value = "SELECT bookings_id, max(id) FROM booked_rooms group by id DESC LIMIT 1;", nativeQuery = true)
    int findLatestID();
}
