package com.example.demo.repositories;

import com.example.demo.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Map;

public interface RoomRepo extends JpaRepository<Room, Long> {
    /*
    hämtar ut lediga rum baserat på HotelID + toDate + fromDate
     */
    @Query(value = "SELECT rooms.hotel_id, rooms.id, room_img, type, size, description, price FROM rooms LEFT JOIN booked_rooms ON booked_rooms.rooms_id = rooms.id\n" +
            "Inner JOIN room_types ON room_types.id = rooms.room_type_id\n" +
            "WHERE rooms.hotel_id = ?1 And room_types.size >= ?4 And rooms.id NOT IN (SELECT rooms_id FROM booked_rooms WHERE from_date BETWEEN ?2 AND ?3 Or to_date BETWEEN ?2 AND ?3)", nativeQuery = true)
    List<Map> findFreeRoomsOnDate(long hotelID, String dateOne, String dateTwo, double size);


    @Query(value="SELECT * FROM rooms WHERE hotel_id = ?", nativeQuery = true)
    List<Room>getAllroomsOnHotelId(long id);
}
