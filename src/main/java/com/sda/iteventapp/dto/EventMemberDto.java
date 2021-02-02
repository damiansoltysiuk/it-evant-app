package com.sda.iteventapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventMemberDto {
    private Long id;
    private EventDto event;
    private UserDto user;
    private String roleUserEvent;
}
