package com.sda.iteventapp.service;

import com.sda.iteventapp.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    List<City> findAllCities();

    Optional<City> findCityByName(String name);

    Optional<City> findCityById(Long id);

    City saveCity(City city);

    City editCity(Long id, City city);

    void deleteCity(City city);
}
