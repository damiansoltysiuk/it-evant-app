package com.sda.iteventapp.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventForm {
//    Event date
    private String eventName;
    private String eventDescription;
    private String eventDataTime;
    private String eventType;
    private String eventCoverLink;
    private String eventWebsite;
    private String eventSubjects;
//    Place date
    private String locationName;
    private String address;
    private String notes;
    private String latitude;
    private String longitude;
    private String cityName;
//    User data
    private Long userId;
}
