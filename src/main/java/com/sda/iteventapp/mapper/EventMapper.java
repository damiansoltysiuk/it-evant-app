package com.sda.iteventapp.mapper;

import com.sda.iteventapp.dto.EventDto;
import com.sda.iteventapp.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper MAPPER = Mappers.getMapper(EventMapper.class);

    EventDto eventToEventDto(Event event);
    List<EventDto> eventDtoList(List<Event> eventList);
}
