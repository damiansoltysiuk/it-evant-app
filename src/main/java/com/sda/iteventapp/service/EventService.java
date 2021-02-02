package com.sda.iteventapp.service;

import com.sda.iteventapp.form.EventForm;
import com.sda.iteventapp.model.Event;

import java.util.List;

public interface EventService {

    List<Event> getEvents();

    List<Event> getEventsByPhrase(String phrase);

    List<Event> getEventsByQuery(String phrase, String name);

    List<Event> getEventBetweenDates(String from, String to);

    List<Event> getAllUserEvents(Long id);

    Event saveEvent(EventForm eventForm);

    Event updateEvent(Long id, EventForm eventForm);

    void deleteEvent(Long id);
}
