package com.sda.iteventapp.restcontroller;

import com.sda.iteventapp.dto.LocationDto;
import com.sda.iteventapp.form.LocationForm;
import com.sda.iteventapp.mapper.LocationMapper;
import com.sda.iteventapp.model.Location;
import com.sda.iteventapp.service.LocationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @ApiOperation(value = "Return all locations")
    @GetMapping("")
    public ResponseEntity all() {
        List<Location> locationList = locationService.findAllLocation();
        List<LocationDto> locationDtoList = LocationMapper.MAPPER.locationDtoList(locationList);
        return ResponseEntity.ok(locationDtoList);
    }

    @ApiOperation(value = "Return locations by id/city")
    @GetMapping("/{phrase}")
    public ResponseEntity get(@PathVariable("phrase") String phrase) {
        List<Location> locationList = locationService.findLocation(phrase);
        List<LocationDto> locationDtoList = LocationMapper.MAPPER.locationDtoList(locationList);
        return ResponseEntity.ok(locationDtoList);
    }

    @ApiOperation(value = "Save location")
    @PostMapping("")
    public ResponseEntity save(@RequestBody LocationForm locationForm, HttpServletRequest request) {
        Location saved = locationService.saveLocation(locationForm);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/api/location/")
                        .buildAndExpand(saved.getId())
                        .toUri()
        ).build();
    }

    @ApiOperation(value = "Update location")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody LocationForm locationForm) {
        locationService.editLocation(id, locationForm);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete location")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        locationService.deleteLocationById(id);
        return ResponseEntity.noContent().build();
    }
}
