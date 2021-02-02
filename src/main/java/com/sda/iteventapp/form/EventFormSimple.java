package com.sda.iteventapp.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventFormSimple {
    private String eventName;
    private String eventDescription;
    private String eventDataTime;
    private String eventType;
    private String eventCoverLink;
    private String eventWebsite;
    private String eventSubjects;

    private Long locationId;
    private Long userId;
}
