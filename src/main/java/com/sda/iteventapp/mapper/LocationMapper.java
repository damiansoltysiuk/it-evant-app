package com.sda.iteventapp.mapper;

import com.sda.iteventapp.dto.LocationDto;
import com.sda.iteventapp.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationMapper MAPPER = Mappers.getMapper(LocationMapper.class);

    LocationDto locationToLocationDto(Location location);
    List<LocationDto> locationDtoList(List<Location> locationList);

    Location locationDtoToLocation(LocationDto locationDto);
}
