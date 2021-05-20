package com.example.demo.repositories;

import com.example.demo.entities.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Robin Persson,
 */
public interface BookingRepo extends JpaRepository<Bookings, Long> {
    @Query(value = "SELECT max(id) as id FROM bookings;", nativeQuery = true)
    int findLatestID();
}
