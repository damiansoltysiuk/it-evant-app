package com.sda.iteventapp.mapper;

import com.sda.iteventapp.dto.CommentaryDto;
import com.sda.iteventapp.model.Commentary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentaryMapper {
    CommentaryMapper MAPPER = Mappers.getMapper(CommentaryMapper.class);

    CommentaryDto commentaryToCommentaryDTO(Commentary commentary);
    List<CommentaryDto> commentaryDtoList(List<Commentary> commentaryList);
}
