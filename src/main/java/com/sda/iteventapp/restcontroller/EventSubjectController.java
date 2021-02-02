package com.sda.iteventapp.restcontroller;

import com.sda.iteventapp.dto.EventSubjectDto;
import com.sda.iteventapp.exception.EventSubjectNotFoundException;
import com.sda.iteventapp.mapper.EventSubjectMapper;
import com.sda.iteventapp.model.EventSubject;
import com.sda.iteventapp.service.EventSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/event/subject")
public class EventSubjectController {
    private EventSubjectService eventSubjectService;

    @Autowired
    public EventSubjectController(EventSubjectService eventSubjectService) {
        this.eventSubjectService = eventSubjectService;
    }

    @ApiOperation(value = "Return all event subjects")
    @GetMapping("")
    public ResponseEntity get() {
        List<EventSubject> eventSubjectList = eventSubjectService.getEventSubjects();
        List<EventSubjectDto> subjectDtoList = EventSubjectMapper.MAPPER.eventSubjectDtoList(eventSubjectList);
        return ResponseEntity.ok(subjectDtoList);
    }

    @ApiOperation(value = "Return event subject by id")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        EventSubject eventSubject = eventSubjectService.getEventSubjectById(id).orElseThrow(() -> new EventSubjectNotFoundException(id));
        EventSubjectDto eventSubjectDto = EventSubjectMapper.MAPPER.eventSubjectToEventSubjectDto(eventSubject);
        return ResponseEntity.ok(eventSubjectDto);
    }

    @ApiOperation(value = "Save event subject")
    @PostMapping("")
    public ResponseEntity save(@RequestBody EventSubjectDto eventSubjectDto, HttpServletRequest request) {
        EventSubject eventSubject = EventSubjectMapper.MAPPER.eventSubjectDtoToEventSubject(eventSubjectDto);
        EventSubject saved = eventSubjectService.saveEventSubject(eventSubject);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                .fromContextPath(request)
                .path("api/event/subject")
                .buildAndExpand(saved.getId())
                .toUri()
        ).build();
    }

    @ApiOperation(value = "Update event subject")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody EventSubject eventSubject) {
        eventSubjectService.updateEventSubject(id, eventSubject);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete event subject")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        EventSubject existed = eventSubjectService.getEventSubjectById(id).orElseThrow(() -> new EventSubjectNotFoundException(id));
        eventSubjectService.deleteEventSubject(existed);
        return ResponseEntity.noContent().build();
    }
}
