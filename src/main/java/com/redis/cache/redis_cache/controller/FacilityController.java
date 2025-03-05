package com.redis.cache.redis_cache.controller;

import com.redis.cache.redis_cache.entity.Facility;
import com.redis.cache.redis_cache.entity.Room;
import com.redis.cache.redis_cache.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @Caching(evict = {
            @CacheEvict(value = "facilitiesByType", allEntries = true),
            @CacheEvict(value = "facilitiesByLocation", allEntries = true)
    })
    @PostMapping
    public Facility createFacility(@RequestBody Facility facility) {
        return facilityService.saveFacility(facility);
    }

    @PostMapping("/filter")
    public List<Facility> filterFacilities(@RequestBody Map<String, Object> filters) {
        return facilityService.filterFacilities(filters);
    }

    @GetMapping("/{id}")
    public Optional<Facility> getFacilityById(@PathVariable Long id) {
        return facilityService.getFacilityById(id);
    }

    @GetMapping
    public List<Facility> getAllFacilities() {
        return facilityService.getAllFacilities();
    }

    @GetMapping("/type/{type}")
    public List<Facility> getFacilitiesByType(@PathVariable String type) {
        return facilityService.getFacilitiesByType(type);
    }

    @GetMapping("/location/{location}")
    public List<Facility> getFacilitiesByLocation(@PathVariable String location) {
        return facilityService.getFacilitiesByLocation(location);
    }

    @GetMapping("/{facilityId}/rooms")
    public Facility getFacilityRooms(@PathVariable Long facilityId) {
        return facilityService.getFacilityById(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found"));
    }

    @PostMapping("/{facilityId}/rooms")
    public Room addRoomToFacility(@PathVariable Long facilityId, @RequestBody Room room) {
        Facility facility = facilityService.getFacilityById(facilityId).orElseThrow();
        room.setFacility(facility);
        return facilityService.saveRoom(room);
    }

    @PutMapping("/update/{name}")
    public Facility updateFacilityByName(@PathVariable String name, @RequestBody Facility updatedFacility) {
        return facilityService.updateFacilityByName(name, updatedFacility);
    }


    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return facilityService.getAllRooms();
    }
}
