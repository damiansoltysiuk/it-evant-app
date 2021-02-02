package com.sda.iteventapp.service.impl;

import com.sda.iteventapp.exception.CityNotFoundException;
import com.sda.iteventapp.exception.LocationNotFoundException;
import com.sda.iteventapp.form.LocationForm;
import com.sda.iteventapp.model.City;
import com.sda.iteventapp.model.Location;
import com.sda.iteventapp.repository.CityRepository;
import com.sda.iteventapp.repository.LocationRepository;
import com.sda.iteventapp.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;
    private CityRepository cityRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, CityRepository cityRepository) {
        this.locationRepository = locationRepository;
        this.cityRepository = cityRepository;
    }

    public List<Location> findAllLocation() {
        return locationRepository.findAll();
    }

    public List<Location> findLocation(String phrase) {
        try {
            Long id = Long.parseLong(phrase);
            Location location = locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
            return Arrays.asList(location);
        } catch (NumberFormatException e) {
            City city = cityRepository.findCityByName(phrase).orElseThrow(() -> new CityNotFoundException(phrase));
            return locationRepository.findLocationByCity(city).orElseThrow(() -> new LocationNotFoundException(city.getName()));
        }

    }

    public Location saveLocation(LocationForm locationForm) {
        City city = cityRepository.findCityByName(locationForm.getCity()).orElseGet(() -> {
            City newCity = new City(locationForm.getCity());
            cityRepository.save(newCity);
            return newCity;
        });
        Location location = new Location(locationForm.getName(), city, locationForm.getAddress(),
                locationForm.getNotes(), locationForm.getLatitude(), locationForm.getLongitude());
        return locationRepository.save(location);
    }

    public Location editLocation(Long id, LocationForm locationForm) {
        Location existed = locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
        City city = cityRepository.findCityByName(locationForm.getCity()).orElseGet(() -> {
            City newCity = new City(locationForm.getCity());
            cityRepository.save(newCity);
            return newCity;
        });
        existed.setName(locationForm.getName());
        existed.setAddress(existed.getAddress());
        existed.setCity(city);
        existed.setNotes(locationForm.getNotes());
        existed.setLatitude(locationForm.getLatitude());
        existed.setLongitude(locationForm.getLongitude());
        return locationRepository.save(existed);
    }

    public void deleteLocationById(Long id) {
        Location existed = locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
        locationRepository.delete(existed);
    }
}