package com.practice.java_practice.service;

import com.practice.java_practice.entity.CityEntity;
import com.practice.java_practice.repository.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public CityEntity createCity(CityEntity city){
        Optional<CityEntity> exists = cityRepository.findByCityName(city.getCityName());
        if(exists.isPresent()){
            throw new RuntimeException("City Name Already Present");
        }
        return cityRepository.save(city);
    }

    public List<CityEntity> getCities(){
        return cityRepository.findAll();
    }

    public CityEntity getCityInformationById(Long cityId){
        return cityRepository.findById(cityId).orElseThrow(()->new RuntimeException("No City Found with "+cityId));
    }

    public List<CityEntity> findByCityName(String city){
        return cityRepository.findCityByName(city);
    }


    public int UpdatePopulationByName(Long population, String cityName){
        return cityRepository.updateCityPopulationByName(population,cityName);
    }

}
