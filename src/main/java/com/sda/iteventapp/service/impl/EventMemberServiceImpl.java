package com.sda.iteventapp.service.impl;

import com.sda.iteventapp.exception.EventMemberNotFoundException;
import com.sda.iteventapp.exception.EventNotFoundException;
import com.sda.iteventapp.exception.UserNotFoundException;
import com.sda.iteventapp.form.EventMemberForm;
import com.sda.iteventapp.model.Event;
import com.sda.iteventapp.model.EventMember;
import com.sda.iteventapp.model.User;
import com.sda.iteventapp.repository.EventMemberRepository;
import com.sda.iteventapp.repository.EventRepository;
import com.sda.iteventapp.repository.UserRepository;
import com.sda.iteventapp.service.EventMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventMemberServiceImpl implements EventMemberService {
    EventMemberRepository eventMemberRepository;
    EventRepository eventRepository;
    UserRepository userRepository;

    @Autowired
    public EventMemberServiceImpl(EventMemberRepository eventMemberRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.eventMemberRepository = eventMemberRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<EventMember> getAllEventMember() {
        return eventMemberRepository.findAll();
    }

    public Optional<EventMember> getEventMemberById(Long id) {
        return eventMemberRepository.findById(id);
    }

    public EventMember saveEventMember(EventMemberForm eventMemberForm) {
        Long eventId = eventMemberForm.getEventId();
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventMemberNotFoundException(eventId));

        Long userId = eventMemberForm.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        EventMember eventMember = new EventMember(event, user, eventMemberForm.getRoleUserEvent());
        return eventMemberRepository.save(eventMember);
    }

    public EventMember editEventMemberById(Long eventMemberId, EventMemberForm eventMemberForm) {
        EventMember eventMember = eventMemberRepository.findById(eventMemberId).orElseThrow(() -> new EventMemberNotFoundException(eventMemberId));

        Long eventId = eventMemberForm.getEventId();
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));

        Long userId = eventMemberForm.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        eventMember.setEvent(event);
        eventMember.setUser(user);
        eventMember.setRoleUserEvent(eventMemberForm.getRoleUserEvent());
        return eventMemberRepository.save(eventMember);
    }

    public void deleteEventMemberById(Long id) {
        EventMember existed = eventMemberRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        eventMemberRepository.delete(existed);
    }
}
