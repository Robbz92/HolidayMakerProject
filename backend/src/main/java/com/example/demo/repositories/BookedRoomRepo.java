package com.example.demo.repositories;

import com.example.demo.entities.BookedRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BookedRoomRepo extends JpaRepository<BookedRooms, Long> {
/*
    @Query(value = "UPDATE booked_rooms SET board=?, SET rooms_id=?, SET bookings_id=?, SET extra_bed_amount=? WHERE id=?;", nativeQuery = true)
    <bookedRoomsObj, id>
    BookedRooms updateEditBooking(bookedRoomsObj);


 */

}
