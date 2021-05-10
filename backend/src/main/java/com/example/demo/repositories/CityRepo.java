package com.example.demo.repositories;

import com.example.demo.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {
    @Query(value = "SELECT * FROM cities WHERE cities.name LIKE ?%", nativeQuery = true)
    City findByName(String name);

    @Query(value = "Select * FROM cities WHERE country_id = ?", nativeQuery = true)
    List<City> getByCountryId (Long id);
}
