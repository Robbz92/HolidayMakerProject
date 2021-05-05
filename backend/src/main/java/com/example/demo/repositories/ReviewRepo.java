package com.example.demo.repositories;

import com.example.demo.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {

    @Query(value="SELECT first_name, last_name, text, score FROM users INNER JOIN reviews ON users.id = reviews.user_id WHERE hotel_id = ?1",nativeQuery = true)
    List<Map> getReviews(long id);
}
