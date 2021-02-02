package com.sda.iteventapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentaryDto {
    private Long id;
    private UserDto author;
    private EventDto event;
    private String message;
    private LocalDateTime publishedAt;
}
