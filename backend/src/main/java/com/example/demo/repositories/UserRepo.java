package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    /*
    kollar på email vem som är inloggad
     */
    User findByEmail(String email);

    @Query(value="SELECT booked_rooms.from_date, booked_rooms.to_date, bookings.id, booked_rooms.board, booked_rooms.extra_bed_amount, bookings.hotel_id, bookings.total_cost, hotels.name, hotels.hotel_img, rooms.room_img, room_types.type FROM users \n" +
            "INNER JOIN bookings ON users.id = bookings.user_id\n" +
            "INNER JOIN booked_rooms ON bookings.id = booked_rooms.bookings_id\n" +
            "INNER JOIN rooms ON rooms.id = booked_rooms.rooms_id\n" +
            "INNER JOIN room_types ON room_types.id = rooms.room_type_id\n" +
            "INNER JOIN hotels ON bookings.hotel_id = hotels.id\n" +
            "WHERE users.id = ?1",nativeQuery = true )
    List<Map> getAllMyBookings(long userId);

    @Query(value="SELECT booked_rooms.from_date, booked_rooms.to_date, booked_rooms.board, hotels.address, bookings.hotel_id, bookings.total_cost, hotels.name, hotels.hotel_img, rooms.room_img, room_types.type, bookings.id FROM users \n" +
            "INNER JOIN bookings ON users.id = bookings.user_id\n" +
            "INNER JOIN booked_rooms ON bookings.id = booked_rooms.bookings_id\n" +
            "INNER JOIN rooms ON rooms.id = booked_rooms.rooms_id\n" +
            "INNER JOIN room_types ON room_types.id = rooms.room_type_id\n" +
            "INNER JOIN hotels ON bookings.hotel_id = hotels.id\n" +
            "WHERE bookings.id = ?1",nativeQuery = true )
    List<Map> getBookingById(long bookingId);
}
