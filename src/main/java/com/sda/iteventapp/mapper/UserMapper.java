package com.sda.iteventapp.mapper;

import com.sda.iteventapp.dto.UserDto;
import com.sda.iteventapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);
    List<UserDto> listUserDto(List<User> userList);
}