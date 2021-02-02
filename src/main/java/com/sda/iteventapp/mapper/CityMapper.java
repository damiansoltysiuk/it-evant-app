package com.sda.iteventapp.mapper;

import com.sda.iteventapp.dto.CityDto;
import com.sda.iteventapp.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityMapper MAPPER = Mappers.getMapper(CityMapper.class);

    CityDto cityToCityDto(City city);
    List<CityDto> cityDtoList(List<City> cities);

    City cityDtoToCity(CityDto cityDto);
}
