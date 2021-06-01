package com.example.demo.repositories;

import com.example.demo.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite, Long> {

    @Query(value = "SELECT h.id AS hotel_id, h.name, h.address, h.total_score, h.distance_downtown, h.distance_beach, h.hotel_img, f.id AS favorite_id\n" +
            "FROM hotels h, favorites f\n" +
            "WHERE h.id = f.hotel_id\n" +
            "AND f.user_id = ?1", nativeQuery = true)
    List<Map> getMyFavorites(long userId);
}
