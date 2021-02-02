package com.sda.iteventapp.service.impl;

import com.sda.iteventapp.exception.CityNotFoundException;
import com.sda.iteventapp.model.City;
import com.sda.iteventapp.repository.CityRepository;
import com.sda.iteventapp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> findCityByName(String name) {
        return cityRepository.findCityByName(name);
    }

    public Optional<City> findCityById(Long id) {
        return cityRepository.findById(id);
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public City editCity(Long id, City city) {
        City existed = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
        existed.setName(city.getName());
        return cityRepository.save(existed);
    }

    public void deleteCity(City city) {
        cityRepository.delete(city);
    }
}
