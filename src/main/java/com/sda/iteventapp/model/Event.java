package com.sda.iteventapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;
    private String name;
    @Column(length = 1000)
    private String description = "";
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_event_type")
    private EventType eventType;
    @Column(name = "cover_photo")
    private String coverPhotoPath = "";
    private String website = "";
    @ManyToMany(targetEntity = EventSubject.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "event_event_subjects",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")})
    private Set<EventSubject> subjectList = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_location")
    private Location location;

    public Event(String name, LocalDateTime dateTime, EventType eventType, Location location) {
        this.name = name;
        this.dateTime = dateTime;
        this.eventType = eventType;
        this.location = location;
    }

    public void addEventSubject(EventSubject eventSubject) {
        subjectList.add(eventSubject);
        eventSubject.getEvents().add(this);
    }

    public void removeEventSubject(EventSubject eventSubject) {
        subjectList.remove(eventSubject);
        eventSubject.getEvents().remove(this);
    }

}
