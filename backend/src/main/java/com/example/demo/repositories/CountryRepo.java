package com.example.demo.repositories;

import com.example.demo.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {
    /*
    hämtar ut alla länder som börjar på din sökfras(sök funktion)
     */
    @Query(value = "SELECT * FROM countries WHERE countries.name LIKE ?%", nativeQuery = true)
    Country findByName(String name);
}
