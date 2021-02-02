package com.sda.iteventapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table( name = "locations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;
    private String address;
    private String notes;
    private String latitude;
    private String longitude;


    public Location(String name,City city, String address) {
        this.name = name;
        this.city=city;
        this.address = address;
    }

    public Location(String name,City city, String address, String notes, String latitude, String longitude) {
        this.name = name;
        this.city=city;
        this.address = address;
        this.notes = notes;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}