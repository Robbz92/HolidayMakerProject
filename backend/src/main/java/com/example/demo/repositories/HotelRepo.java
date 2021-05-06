package com.example.demo.repositories;

import com.example.demo.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
    @Query(value = "SELECT * FROM hotels WHERE hotels.name LIKE %?%", nativeQuery = true)
    List<Hotel> findByName(String name);

    @Query(value = "Select * FROM hotels WHERE city_id = ?", nativeQuery = true)
    List<Hotel> getByCityId (Long id);

    //Get all comforts for hotel, add it to comfortList
    @Query(value = "SELECT name FROM comforts INNER JOIN hc_list ON comforts.id = hc_list" +
            ".comforts_id WHERE hc_list.hotel_id = ?", nativeQuery = true)
    List<String> comfortsPerHotel (Long id);

    @Query(value = "SELECT MIN(price) FROM rooms WHERE rooms.hotel_id = ?", nativeQuery = true)
    int cheapestPrice (Long id);
}
