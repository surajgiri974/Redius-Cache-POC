package com.redis.cache.redis_cache.service;

import com.redis.cache.redis_cache.FacilitySpecification;
import com.redis.cache.redis_cache.entity.Facility;
import com.redis.cache.redis_cache.entity.Room;
import com.redis.cache.redis_cache.repository.FacilityRepository;
import com.redis.cache.redis_cache.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Save Facility
    public Facility saveFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    @Transactional
    public List<Facility> filterFacilities(Map<String, Object> filters) {
        Specification<Facility> spec = FacilitySpecification.getFacilitiesByFilters(filters);
        return facilityRepository.findAll(spec);
    }

    public Facility updateFacilityByName(String name, Facility updatedFacility) {
        Facility existingFacility = facilityRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Facility not found"));

        existingFacility.setLocation(updatedFacility.getLocation());
        existingFacility.setType(updatedFacility.getType());
        existingFacility.setCapacity(updatedFacility.getCapacity());

        return facilityRepository.save(existingFacility);
    }


    @Transactional
    @Cacheable(value = "facilitiesById", key = "#id")
    public Optional<Facility> getFacilityById(Long id) {
        return facilityRepository.findById(id);
    }

    @Transactional
    @Cacheable(value = "facilitiesByType", key = "#type")
    public List<Facility> getFacilitiesByType(String type) {
        return facilityRepository.findByType(type);
    }

    @Transactional
    @Cacheable(value = "facilitiesByLocation", key = "#location")
    public List<Facility> getFacilitiesByLocation(String location) {
        return facilityRepository.findByLocation(location);
    }

    // Get All Facilities
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    // Save Room
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    // Get All Rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
