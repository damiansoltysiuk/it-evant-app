package com.sda.iteventapp.service;

import com.sda.iteventapp.form.EventMemberForm;
import com.sda.iteventapp.model.EventMember;

import java.util.List;
import java.util.Optional;

public interface EventMemberService {
    List<EventMember> getAllEventMember();

    Optional<EventMember> getEventMemberById(Long id);

    EventMember saveEventMember(EventMemberForm eventMemberForm);

    EventMember editEventMemberById(Long eventMemberId, EventMemberForm eventMemberForm);

    void deleteEventMemberById(Long id);
}
