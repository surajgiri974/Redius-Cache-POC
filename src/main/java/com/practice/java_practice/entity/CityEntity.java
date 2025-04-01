package com.practice.java_practice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "city")
@Entity
public class CityEntity  {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name",nullable = false)
    private String cityName;

    @Column(name = "country_name",nullable = false)
    private String countryName;

    @Column(name = "population",nullable = false)
    private Long population;


}
