package com.example.demo.repositories;

import com.example.demo.entities.City;
import com.example.demo.entities.Country;
import com.example.demo.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
    /*
    hämtar ut all information som vi vill ha till ett hotellkort (hover funktion)
     */
    @Query(value = "SELECT h.name AS hotelName, h.distance_beach, h.distance_downtown, h.total_score, c.temperature, y.name AS city, c.name AS country\n" +
            "FROM hotels h, countries c, cities y\n" +
            "WHERE h.name LIKE %?%\n" +
            "AND y.country_id = c.id AND h.city_id = y.id", nativeQuery = true)
    List<Map> findDescriptionByName(String phrase);

    /*
   hämtar hotel på en hotelfras(sök funktion)
     */
    @Query(value = "SELECT hotels.id, hotels.name, rooms.id AS RoomID, hotels.hotel_img, hotels.address, hotels.city_id, hotels.distance_beach, hotels.distance_downtown, hotels.total_score\n" +
            "FROM hotels INNER JOIN rooms ON hotels.id = rooms.hotel_id\n" +
            "INNER JOIN room_types on rooms.room_type_id = room_types.id\n" +
            "WHERE hotels.name LIKE %?1% AND room_types.size >= ?4 \n" +
            "AND rooms.id\n" +
            "NOT IN (SELECT rooms_id FROM booked_rooms WHERE from_date BETWEEN ?2 AND ?3 Or to_date BETWEEN ?2 AND ?3) \n" +
            "GROUP BY hotels.name\n" +
            "HAVING COUNT(rooms.id) >= ?5\n" +
            "ORDER BY hotels.id", nativeQuery = true)
    List<Hotel> findByName(String phrase, String fromDate, String toDate, double size, long room);

    /*
    hämtar allt från hotel på ett city ID(sök funktion)
     */
   @Query(value = "SELECT hotels.id, hotels.name, COUNT(rooms.id), hotels.hotel_img, hotels.address, hotels.city_id, hotels.distance_beach, hotels.distance_downtown, hotels.total_score\n" +
           "FROM hotels INNER JOIN rooms ON hotels.id = rooms.hotel_id\n" +
           "INNER JOIN room_types on rooms.room_type_id = room_types.id\n" +
           "WHERE room_types.size >= ?4 AND hotels.city_id = ?1\n" +
           "AND rooms.id\n" +
           "NOT IN (SELECT rooms_id FROM booked_rooms WHERE from_date BETWEEN ?2 AND ?3 Or to_date BETWEEN ?2 AND ?3) \n" +
           "GROUP BY hotels.name\n" +
           "HAVING COUNT(rooms.id) >= ?5\n" +
           "ORDER BY hotels.id", nativeQuery = true)
    List<Hotel> getByCityId (Long id, String fromDate, String toDate, double size, long room);

   /*
   hämtar allt från hotel på HotelID(onClick hotelCard funktion)
    */
    @Query(value="SELECT * FROM hotels WHERE id = ?1",nativeQuery = true )
     List<Map> getInfoHotelById(Long id);

    /*
    hämtar specifik information gällande hotell på HotelID(onClick hotelCard funktion)
     */
    @Query(value="SELECT cities.name as City, countries.name as Country, countries.temperature\n" +
            "FROM cities INNER JOIN countries on countries.id = cities.country_id\n" +
            "INNER JOIN hotels on hotels.city_id = cities.id\n" +
            "WHERE hotels.id = ?1",nativeQuery = true )
    List<Map> temperatureInfoByHotelId(Long id);

    /*
    Hämtar attractions till hotell på HotelID(onClick hotelCard funktion)
     */
    @Query(value="SELECT attractions.name FROM attractions\n" +
            "INNER JOIN ca_list ON attractions.id = ca_list.attractions_id\n" +
            "INNER JOIN cities ON cities.id = ca_list.city_id\n" +
            "INNER JOIN hotels on hotels.city_id = cities.id\n" +
            "WHERE hotels.id = ?1", nativeQuery = true )
    List<Map> allAttractions(Long id);
    /*
    Hämtar Comforts till hotell på HotelID(onClick hotelCard funktion)
     */
    @Query(value="SELECT comforts.name FROM comforts INNER JOIN hc_list on comforts.id = hc_list.comforts_id\n" +
            "INNER JOIN hotels on hotels.id = hc_list.hotel_id WHERE hotels.id = ?1", nativeQuery = true)
    List<Map> hotelsComforts(Long id);

    /*
    hämtar alla comforts och lägger i en comforts lista(Hover funktion)
     */
    @Query(value = "SELECT name FROM comforts INNER JOIN hc_list ON comforts.id = hc_list" +
            ".comforts_id WHERE hc_list.hotel_id = ?", nativeQuery = true)
    List<String> comfortsPerHotel (Long id);

    @Query(value = "SELECT name FROM attractions INNER JOIN ca_list ON attractions.id = ca_list" +
            ".attractions_id WHERE ca_list.city_id = ?", nativeQuery = true)
    List<String> attractionsPerHotel (Long id);

    /*
    Hämtar ut minsta pris på rum baserat på HotelID
     */
    @Query(value = "SELECT MIN(price) FROM rooms WHERE rooms.hotel_id = ?", nativeQuery = true)
    int cheapestPrice (Long id);

    @Query(value = "SELECT cities.name as City, countries.name as Country FROM cities INNER JOIN " +
            "countries on countries.id = cities.country_id " +
            "INNER JOIN hotels on hotels.city_id = cities.id WHERE hotels.id = ?", nativeQuery =
            true)
    String placeName (Long id);

    @Query(value = "SELECT hotels.id, hotels.name, hotels.hotel_img, hotels.address, hotels.city_id, hotels.distance_beach, hotels.distance_downtown, hotels.total_score\n" +
            "FROM hotels INNER JOIN rooms ON hotels.id = rooms.hotel_id\n" +
            "INNER JOIN room_types on rooms.room_type_id = room_types.id\n" +
            "WHERE room_types.size >= ?3\n" +
            "AND rooms.id\n" +
            "NOT IN (SELECT rooms_id FROM booked_rooms WHERE from_date BETWEEN ?1 AND ?2 Or to_date BETWEEN ?1 AND ?2) \n" +
            "GROUP BY hotels.name\n" +
            "HAVING COUNT(rooms.id) >= ?4\n" +
            "ORDER BY hotels.id", nativeQuery = true)
    List<Hotel> findAll(String fromDate, String toDate, double size, long room );

    @Query(value= "SELECT hotels.id, hotels.name, hotels.hotel_img, hotels.address, hotels.city_id, hotels.distance_beach, hotels.distance_downtown, hotels.total_score\n" +
            "FROM hotels \n" +
            "          INNER JOIN cities ON cities.id = hotels.city_id\n" +
            "          INNER JOIN countries ON countries.id = cities.country_id\n" +
            "          INNER JOIN rooms ON hotels.id = rooms.hotel_id\n" +
            "          INNER JOIN room_types on rooms.room_type_id = room_types.id\n" +
            "WHERE countries.temperature >= ?4\n" +
            "AND countries.temperature <= ?5 AND room_types.size >= ?3\n" +
            "AND rooms.id\n" +
            "           NOT IN (SELECT rooms_id FROM booked_rooms WHERE from_date BETWEEN ?1 AND ?2 Or to_date BETWEEN ?1 AND ?2) \n" +
            "   GROUP BY hotels.name\n" +
            "   HAVING COUNT(rooms.id) >= ?4\n" +
            "   ORDER BY hotels.id", nativeQuery = true)
    List<Hotel> countryTemperature(String fromDate, String toDate, double size, long room, int temp1, int temp2);
}
