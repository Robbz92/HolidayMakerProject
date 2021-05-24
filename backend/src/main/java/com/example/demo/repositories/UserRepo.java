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

    @Query(value="SELECT booked_rooms.from_date, booked_rooms.to_date, COUNT(booked_rooms.id) AS BookedRooms, bookings.id, " +
            "booked_rooms.board, bookings.hotel_id, bookings.total_cost, hotels.name, hotels.hotel_img " +
            "FROM users " +
            "INNER JOIN bookings ON users.id = bookings.user_id " +
            "INNER JOIN booked_rooms ON bookings.id = booked_rooms.bookings_id " +
            "INNER JOIN rooms ON rooms.id = booked_rooms.rooms_id " +
            "INNER JOIN room_types ON room_types.id = rooms.room_type_id " +
            "INNER JOIN hotels ON bookings.hotel_id = hotels.id " +
            "WHERE users.id = ?1\n" +
            "GROUP BY bookings.id",nativeQuery = true )
    List<Map> getAllMyBookings(long userId);

    @Query(value="SELECT bookings.id, booked_rooms.from_date, booked_rooms.to_date, hotels.address, bookings.hotel_id, bookings.total_cost, hotels.name, COUNT(booked_rooms.id) AS BookedRooms, hotels.hotel_img FROM users \n" +
            "            INNER JOIN bookings ON users.id = bookings.user_id\n" +
            "            INNER JOIN booked_rooms ON bookings.id = booked_rooms.bookings_id\n" +
            "            INNER JOIN rooms ON rooms.id = booked_rooms.rooms_id\n" +
            "            INNER JOIN room_types ON room_types.id = rooms.room_type_id\n" +
            "            INNER JOIN hotels ON bookings.hotel_id = hotels.id\n" +
            "            WHERE bookings.id = ?1 LIMIT 1",nativeQuery = true )
    List<Map> getBookingById(long bookingId);

    @Query(value="SELECT rooms.room_img, rooms.price, room_types.type, booked_rooms.board, booked_rooms.extra_bed_amount AS extraBed FROM  bookings \n" +
            "            INNER JOIN booked_rooms ON bookings.id = booked_rooms.bookings_id\n" +
            "            INNER JOIN rooms ON rooms.id = booked_rooms.rooms_id\n" +
            "            INNER JOIN room_types ON room_types.id = rooms.room_type_id\n" +
            "            INNER JOIN hotels ON bookings.hotel_id = hotels.id\n" +
            "            WHERE bookings.id = ?1",nativeQuery = true )
    List<Map> getBookedRoomsById(long bookingId);
}
