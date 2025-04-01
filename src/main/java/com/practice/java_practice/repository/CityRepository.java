package com.practice.java_practice.repository;

import com.practice.java_practice.entity.CityEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity,Long> {

    Optional<CityEntity> findByCityName(String cityName);

    @Query(value = "SELECT * FROM city ce WHERE ce.city_name = :name",nativeQuery = true)
    List<CityEntity> findCityByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("UPDATE CityEntity ce SET ce.population = :population WHERE ce.cityName = :name")
    int updateCityPopulationByName(@Param("population")Long population, @Param("name") String name);
}
