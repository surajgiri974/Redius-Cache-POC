package com.redis.cache.redis_cache.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
@Getter
@Setter
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private String type;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    @JsonIgnore  // Prevents infinite recursion in JSON serialization
    private Facility facility;
}
