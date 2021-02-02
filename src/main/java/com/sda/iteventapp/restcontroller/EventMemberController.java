package com.sda.iteventapp.restcontroller;

import com.sda.iteventapp.dto.EventMemberDto;
import com.sda.iteventapp.exception.EventMemberNotFoundException;
import com.sda.iteventapp.form.EventMemberForm;
import com.sda.iteventapp.mapper.EventMemberMapper;
import com.sda.iteventapp.model.EventMember;
import com.sda.iteventapp.service.EventMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/member")
public class EventMemberController {
    private EventMemberService eventMemberService;

    @Autowired
    public EventMemberController(EventMemberService eventMemberService) {
        this.eventMemberService = eventMemberService;
    }

    @ApiOperation(value = "Return all event members")
    @GetMapping("")
    public ResponseEntity all() {
        List<EventMember> eventMemberList = eventMemberService.getAllEventMember();
        List<EventMemberDto> eventMemberDtoList = EventMemberMapper.MAPPER.eventMemberDtoList(eventMemberList);
        return ResponseEntity.ok(eventMemberDtoList);
    }

    @ApiOperation(value = "Return all event member by id")
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        EventMember eventMember = eventMemberService.getEventMemberById(id).orElseThrow(() -> new EventMemberNotFoundException(id));
        EventMemberDto eventMemberDto = EventMemberMapper.MAPPER.eventMemberToEventMemberDto(eventMember);
        return ResponseEntity.ok(eventMemberDto);
    }

    @ApiOperation(value = "Save event member")
    @PostMapping("")
    public ResponseEntity save(@RequestBody EventMemberForm eventMemberForm, HttpServletRequest request) {
        EventMember saved = eventMemberService.saveEventMember(eventMemberForm);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/api/member/")
                        .buildAndExpand(saved.getId())
                        .toUri()
        ).build();
    }

    @ApiOperation(value = "Update event member")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id, @RequestBody EventMemberForm eventMemberForm) {
        eventMemberService.editEventMemberById(id, eventMemberForm);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete event member")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        eventMemberService.deleteEventMemberById(id);
        return ResponseEntity.noContent().build();
    }
}
