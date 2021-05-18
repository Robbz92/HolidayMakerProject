package com.example.demo.repositories;

import com.example.demo.entities.BookedRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Robin Persson,
 */
@Repository
public interface BookedRoomRepo extends JpaRepository<BookedRooms, Long> {

}
