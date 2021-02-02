package com.sda.iteventapp.service.impl;

import com.sda.iteventapp.exception.EventSubjectNotFoundException;
import com.sda.iteventapp.model.EventSubject;
import com.sda.iteventapp.repository.EventSubjectRepository;
import com.sda.iteventapp.service.EventSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventSubjectServiceImpl implements EventSubjectService {
    private EventSubjectRepository eventSubjectRepository;

    @Autowired
    public EventSubjectServiceImpl(EventSubjectRepository eventSubjectRepository) {
        this.eventSubjectRepository = eventSubjectRepository;
    }

    @Override
    public List<EventSubject> getEventSubjects() {
        return eventSubjectRepository.findAll();
    }

    @Override
    public Optional<EventSubject> getEventSubjectById(Long id) {
        return eventSubjectRepository.findById(id);
    }

    @Override
    public EventSubject saveEventSubject(EventSubject eventSubject) {
        return eventSubjectRepository.save(eventSubject);
    }

    @Override
    public EventSubject updateEventSubject(Long id, EventSubject eventSubject) {
        EventSubject existed = eventSubjectRepository.findById(id).orElseThrow(() -> new EventSubjectNotFoundException(id));
        existed.setName(eventSubject.getName());
        existed.setEvents(eventSubject.getEvents());
        return eventSubjectRepository.save(existed);
    }

    @Override
    public void deleteEventSubject(EventSubject eventSubject) {
        eventSubjectRepository.delete(eventSubject);
    }
}
