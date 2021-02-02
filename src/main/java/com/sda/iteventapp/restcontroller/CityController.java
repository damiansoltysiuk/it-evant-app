package com.sda.iteventapp.restcontroller;

import com.sda.iteventapp.dto.CityDto;
import com.sda.iteventapp.exception.CityNotFoundException;
import com.sda.iteventapp.mapper.CityMapper;
import com.sda.iteventapp.model.City;
import com.sda.iteventapp.service.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {
    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @ApiOperation(value = "Get all city in db")
    @GetMapping("")
    public ResponseEntity all() {
        List<City> allCities = cityService.findAllCities();
        List<CityDto> cityDtoList = CityMapper.MAPPER.cityDtoList(allCities);
        return ResponseEntity.ok(cityDtoList);
    }

    @ApiOperation(value = "Get city by id or name")
    @GetMapping("/{phrase}")
    public ResponseEntity get(@ApiParam(value = "enter the id (Long) or city name (String)", example = "Warszawa") @PathVariable("phrase") String phrase) {
        try {
            Long id = Long.parseLong(phrase);
            City city = cityService.findCityById(id).orElseThrow(() -> new CityNotFoundException(id));
            CityDto cityDto = CityMapper.MAPPER.cityToCityDto(city);
            return ResponseEntity.ok(cityDto);
        } catch (NumberFormatException e) {
            City city = cityService.findCityByName(phrase).orElseThrow(() -> new CityNotFoundException(phrase));
            CityDto cityDto = CityMapper.MAPPER.cityToCityDto(city);
            return ResponseEntity.ok(cityDto);
        }
    }

    @ApiOperation(value = "Save city")
    @PostMapping("")
    public ResponseEntity save(@ApiParam(value = "A JSON value representing a transaction. An example of the expected schema can be found down here.",
            examples = @Example(value = {@ExampleProperty(value = "{'id': '1', 'name': 'London'}", mediaType = "application/json")})) @RequestBody CityDto cityDto, HttpServletRequest request) {
        City city = CityMapper.MAPPER.cityDtoToCity(cityDto);
        City saved = cityService.saveCity(city);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/api/city/")
                        .buildAndExpand(saved.getId())
                        .toUri()
        ).build();
    }

    @ApiOperation(value = "Update city")
    @PutMapping("/{id}")
    public ResponseEntity update(
            @ApiParam(example = "10") @PathVariable("id") Long id,
            @ApiParam(value = "val dto", examples = @Example(value = {@ExampleProperty(value =
                    "{\"id\": \"10\", \"name\": \"bobo\"}", mediaType = "application/json")})) @RequestBody CityDto cityDto) {
        City city = CityMapper.MAPPER.cityDtoToCity(cityDto);
        cityService.editCity(id, city);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete city")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        City existed = cityService.findCityById(id).orElseThrow(() -> new CityNotFoundException(id));
        cityService.deleteCity(existed);
        return ResponseEntity.noContent().build();
    }
}