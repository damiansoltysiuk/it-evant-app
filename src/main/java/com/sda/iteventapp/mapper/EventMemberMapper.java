package com.sda.iteventapp.mapper;

import com.sda.iteventapp.dto.EventMemberDto;
import com.sda.iteventapp.model.EventMember;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMemberMapper {
    EventMemberMapper MAPPER = Mappers.getMapper(EventMemberMapper.class);

    EventMemberDto eventMemberToEventMemberDto(EventMember eventMember);
    List<EventMemberDto> eventMemberDtoList(List<EventMember> eventMemberList);
}
