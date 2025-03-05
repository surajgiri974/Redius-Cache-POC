package com.redis.cache.redis_cache.repository;

import com.redis.cache.redis_cache.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByType(String type);
    List<Room> findByFacilityId(Long facilityId);
}
