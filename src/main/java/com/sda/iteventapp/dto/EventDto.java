package com.sda.iteventapp.dto;

import com.sda.iteventapp.model.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime dateTime;
    private EventTypeDto eventType;
    private String coverPhotoPath;
    private String website;
    private Set<EventSubjectDto> subjectList;
    private Location location;
}
