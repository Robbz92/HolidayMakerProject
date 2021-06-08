package com.example.demo.repositories;

import com.example.demo.entities.Review;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {

    /*
    hämtar ut reviews baserat på HotelID

     */

    @Query(value="SELECT first_name, last_name, text, score FROM users INNER JOIN reviews ON users.id = reviews.user_id WHERE hotel_id = ?1",nativeQuery = true)
    List<Map> getReviews(long id);

    @Query(value = "SELECT booking_id from reviews where user_id = ?", nativeQuery = true)
    List<Integer> findReviews(long id);
}
