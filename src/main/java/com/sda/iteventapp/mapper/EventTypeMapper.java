package com.sda.iteventapp.mapper;

import com.sda.iteventapp.dto.EventTypeDto;
import com.sda.iteventapp.model.EventType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventTypeMapper {
    EventTypeMapper MAPPER = Mappers.getMapper(EventTypeMapper.class);

    EventTypeDto eventTypeToEventTypeDto(EventType eventType);
    List<EventTypeDto> eventTypeDtoList(List<EventType> eventTypeList);
}
