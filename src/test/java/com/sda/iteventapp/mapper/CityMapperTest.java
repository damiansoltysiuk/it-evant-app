package com.sda.iteventapp.mapper;

import com.sda.iteventapp.dto.CityDto;
import com.sda.iteventapp.model.City;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CityMapperTest {

    @Test
    void cityToCityDto() {
        City city = new City(66L, "Sudovia");
        CityDto cityDto = CityMapper.MAPPER.cityToCityDto(city);

        assertNotNull(cityDto);
        assertEquals(city.getId(), cityDto.getId());
        assertEquals(city.getName(), cityDto.getName());
    }

    @Test
    void cityDtoToCity() {
//        CityDto cityDto = new CityDto(55L, "Long Island");
//        City city = CityMapper.MAPPER.cityDtoToCity(cityDto);
//
//        assertNotNull(city);
//        assertEquals(cityDto.getId(), city.getId());
//        assertEquals(cityDto.getName(), city.getName());
    }
}