package com.sda.iteventapp.mapper;

import com.sda.iteventapp.dto.EventSubjectDto;
import com.sda.iteventapp.model.EventSubject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventSubjectMapper {
    EventSubjectMapper MAPPER = Mappers.getMapper(EventSubjectMapper.class);

    EventSubjectDto eventSubjectToEventSubjectDto(EventSubject eventSubject);
    List<EventSubjectDto> eventSubjectDtoList(List<EventSubject> eventSubjectList);

    EventSubject eventSubjectDtoToEventSubject(EventSubjectDto eventSubjectDto);
}
