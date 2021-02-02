package com.sda.iteventapp.service;

import com.sda.iteventapp.form.LocationForm;
import com.sda.iteventapp.model.Location;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LocationService {

    List<Location> findAllLocation();

    List<Location> findLocation(String phrase);

    Location saveLocation(@RequestBody LocationForm locationForm);

    Location editLocation(Long id, LocationForm locationForm);

    void deleteLocationById(Long id);
}
