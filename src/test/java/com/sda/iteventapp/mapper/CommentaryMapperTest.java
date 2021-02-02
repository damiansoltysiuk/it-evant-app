package com.sda.iteventapp.mapper;

import com.sda.iteventapp.dto.CommentaryDto;
import com.sda.iteventapp.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommentaryMapperTest {

    @Test
    void commentaryToCommentaryDTO() {
        Commentary commentary = new Commentary(
                new User(1L,
                        "Damian",
                        "mail@em.il",
                        "xxx",
                        Arrays.asList("ADMIN"),
                        LocalDate.now()
                ),
                new Event(
                        1L,
                        "name",
                        "description",
                        LocalDateTime.now(),
                        EventType.builder().id(1L).name("Event Type").build(),
                        "cover path",
                        "website",
                        new HashSet<EventSubject>(),
                        new Location(2l, "name", new City(1L, "name"), "address", "notes", "lat", "long")
                ),
                "message");

        CommentaryDto commentaryDto = CommentaryMapper.MAPPER.commentaryToCommentaryDTO(commentary);

        assertNotNull(commentaryDto);
        assertEquals(commentaryDto.getAuthor().getId(), commentary.getAuthor().getId());
    }
}