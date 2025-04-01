package com.practice.java_practice.controller;

import com.practice.java_practice.entity.CityEntity;
import com.practice.java_practice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;


    @PostMapping("/save-city")
    public CityEntity createCity(@RequestBody CityEntity cityEntity){
        return cityService.createCity(cityEntity);
    }

    @GetMapping("/list")
    public List<CityEntity> getAllCities(){
        return cityService.getCities();
    }

    @GetMapping("/{id}")
    public CityEntity getCityById(@PathVariable Long id){
        return  cityService.getCityInformationById(id);
    }

    @GetMapping("/city/{name}")
    public List<CityEntity> getCityByName(@PathVariable String name){
        return cityService.findByCityName(name);
    }

    @PutMapping("/city/{population}/{name}")
    public int updateCityPopulationByName(@PathVariable Long population, @PathVariable String name){
        return cityService.UpdatePopulationByName(population,name);
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleError(RuntimeException runtimeException){
        return runtimeException.getMessage();
    }


}
