package com.sda.iteventapp.restcontroller;

import com.sda.iteventapp.dto.EventDto;
import com.sda.iteventapp.form.EventForm;
import com.sda.iteventapp.mapper.EventMapper;
import com.sda.iteventapp.model.Event;
import com.sda.iteventapp.service.EventService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ApiOperation(value = "Return all events")
    @GetMapping("")
    public ResponseEntity get() {
        List<Event> eventList = eventService.getEvents();
        List<EventDto> eventDtoList = EventMapper.MAPPER.eventDtoList(eventList);
        return ResponseEntity.ok(eventDtoList);
    }

    @ApiOperation(value = "Return events by id/city/event_type/event_subject/event_name")
    @GetMapping("/{phrase}")
    public ResponseEntity getPhrase(@PathVariable("phrase") String phrase) {
        List<Event> eventList = eventService.getEventsByPhrase(phrase);
        List<EventDto> eventDtoList = EventMapper.MAPPER.eventDtoList(eventList);
        return ResponseEntity.ok(eventDtoList);
    }

    @ApiOperation(value = "Return events by query phrase or name")
    @GetMapping("/query")
    public ResponseEntity getQuery(@RequestParam(name = "phrase", required = false) String phrase, @RequestParam(name = "name", required = false) String name) {
        List<Event> eventList = eventService.getEventsByQuery(phrase, name);
        List<EventDto> eventDtoList = EventMapper.MAPPER.eventDtoList(eventList);
        return ResponseEntity.ok(eventDtoList);
    }

    @ApiOperation(value = "Return events between dates. Format date: yyyyMMdd-HHmm or yyyyMMdd")
    @GetMapping("/date")
    public ResponseEntity getDate(@RequestParam(name = "from", required = false) String from, @RequestParam(name = "to", required = false) String to) {
        List<Event> eventList = eventService.getEventBetweenDates(from, to);
        List<EventDto> eventDtoList = EventMapper.MAPPER.eventDtoList(eventList);
        return ResponseEntity.ok(eventDtoList);
    }

    @ApiOperation(value = "Return users (member and organizer) events")
    @GetMapping("/user/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long id) {
        List<Event> eventList = eventService.getAllUserEvents(id);
        List<EventDto> eventDtoList = EventMapper.MAPPER.eventDtoList(eventList);
        return ResponseEntity.ok(eventDtoList);
    }

    @ApiOperation(value = "Save event")
    @PostMapping("")
    public ResponseEntity save(@RequestBody EventForm eventForm, HttpServletRequest request) {
        Event saved = eventService.saveEvent(eventForm);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/api/event/")
                        .buildAndExpand(saved.getId())
                        .toUri()
        ).build();
    }

    @ApiOperation(value = "Update event")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody EventForm eventForm) {
        eventService.updateEvent(id, eventForm);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete event")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
