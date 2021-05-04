package com.example.demo.repositories;

import com.example.demo.entities.Country;
import com.example.demo.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
    @Query(value = "SELECT * FROM hotels WHERE hotels.name LIKE %?%", nativeQuery = true)
    List<Hotel> findByName(String name);
}
