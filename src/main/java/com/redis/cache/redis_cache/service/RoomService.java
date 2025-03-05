package com.redis.cache.redis_cache.service;

import com.redis.cache.redis_cache.entity.Room;
import com.redis.cache.redis_cache.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import java.util.List;

@Transactional
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }


    @Caching(evict = {
            @CacheEvict(value = "roomsByType", allEntries = true),
            @CacheEvict(value = "roomsByFacility", allEntries = true)
    })
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Transactional
    @Cacheable(value = "roomsByType", key = "#type")
    public List<Room> getRoomsByType(String type) {
        return roomRepository.findByType(type);
    }

    @Transactional
    @Cacheable(value = "roomsByFacility", key = "#facilityId")
    public List<Room> getRoomsByFacility(Long facilityId) {
        return roomRepository.findByFacilityId(facilityId);
    }

}
