package com.redis.cache.redis_cache.controller;

import com.redis.cache.redis_cache.entity.Room;
import com.redis.cache.redis_cache.service.RoomService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private RoomService roomService;

    @GetMapping("/type/{type}")
    public List<Room> getRoomsByType(@PathVariable String type) {
        return roomService.getRoomsByType(type);
    }

    @GetMapping("/facility/{facilityId}")
    public List<Room> getRoomsByFacility(@PathVariable Long facilityId) {
        return roomService.getRoomsByFacility(facilityId);
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    public Room saveRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }
}
