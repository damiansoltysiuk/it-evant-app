package com.sda.iteventapp.service;

import com.sda.iteventapp.model.EventSubject;

import java.util.List;
import java.util.Optional;

public interface EventSubjectService {
    List<EventSubject> getEventSubjects();

    Optional<EventSubject> getEventSubjectById(Long id);

    EventSubject saveEventSubject(EventSubject eventSubject);

    EventSubject updateEventSubject(Long id, EventSubject eventSubject);

    void deleteEventSubject(EventSubject eventSubject);
}
