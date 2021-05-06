package com.example.demo.repositories;

import com.example.demo.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
    @Query(value = "SELECT h.name AS hotelName, h.distance_beach, h.distance_downtown, h.total_score, c.temperature, y.name AS city, c.name AS country\n" +
            "FROM hotels h, countries c, cities y\n" +
            "WHERE h.name LIKE %?%\n" +
            "AND y.country_id = c.id AND h.city_id = y.id", nativeQuery = true)

    List<Map> findDescriptionByName(String phrase);

    @Query(value = "SELECT * FROM hotels WHERE hotels.name LIKE %?%", nativeQuery = true)
    List<Hotel> findByName(String name);

    @Query(value = "Select * FROM hotels WHERE city_id = ?", nativeQuery = true)
    List<Hotel> getByCityId (Long id);

    @Query(value="SELECT * FROM hotels WHERE id = ?1",nativeQuery = true )
     List<Map> infoHotelById(Long id);
    
    @Query(value="SELECT cities.name as City, countries.name as Country, countries.temperature\n" +
            "FROM cities INNER JOIN countries on countries.id = cities.country_id\n" +
            "INNER JOIN hotels on hotels.city_id = cities.id\n" +
            "WHERE hotels.id = ?1",nativeQuery = true )
    List<Map> theInfo(Long id);

    @Query(value="SELECT attractions.name FROM attractions\n" +
            "INNER JOIN ca_list ON attractions.id = ca_list.attractions_id\n" +
            "INNER JOIN cities ON cities.id = ca_list.city_id\n" +
            "INNER JOIN hotels on hotels.city_id = cities.id\n" +
            "WHERE hotels.id = ?1", nativeQuery = true )
    List<Map> allAttractions(Long id);

    @Query(value="SELECT comforts.name FROM comforts INNER JOIN hc_list on comforts.id = hc_list.comforts_id\n" +
            "INNER JOIN hotels on hotels.id = hc_list.hotel_id WHERE hotels.id = ?1", nativeQuery = true)
    List<Map> hotelsComforts(Long id);

    //Get all comforts for hotel, add it to comfortList
    @Query(value = "SELECT name FROM comforts INNER JOIN hc_list ON comforts.id = hc_list" +
            ".comforts_id WHERE hc_list.hotel_id = ?", nativeQuery = true)
    List<String> comfortsPerHotel (Long id);

    @Query(value = "SELECT MIN(price) FROM rooms WHERE rooms.hotel_id = ?", nativeQuery = true)
    int cheapestPrice (Long id);
}
