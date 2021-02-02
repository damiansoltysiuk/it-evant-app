package com.sda.iteventapp.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationForm {
    private String name;
    private String city;
    private String address;
    private String notes;
    private String latitude;
    private String longitude;
}