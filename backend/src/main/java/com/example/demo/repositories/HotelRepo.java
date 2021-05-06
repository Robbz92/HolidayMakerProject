package com.example.demo.repositories;

import com.example.demo.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
    @Query(value = "SELECT hotels.name AS hotelName, total_score, temperature, cities.name AS city, countries.name AS country FROM hotels \n" +
            "INNER JOIN countries ON hotels.id = countries.id\n" +
            "INNER JOIN cities ON hotels.id = cities.country_id\n" +
            "WHERE hotels.name LIKE %?%", nativeQuery = true)
    List<Map> findDescriptionByName(String phrase);

    @Query(value = "Select * FROM hotels WHERE city_id = ?", nativeQuery = true)
    List<Hotel> getByCityId (Long id);
}
