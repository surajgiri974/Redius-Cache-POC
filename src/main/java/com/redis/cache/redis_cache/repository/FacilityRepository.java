package com.redis.cache.redis_cache.repository;

import com.redis.cache.redis_cache.entity.Facility;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long>, JpaSpecificationExecutor<Facility> {

    @EntityGraph(attributePaths = "rooms")
    Optional<Facility> findById(Long id);
    Optional<Facility> findByName(String name);
    List<Facility> findByType(String type);
    List<Facility> findByLocation(String location);


}
