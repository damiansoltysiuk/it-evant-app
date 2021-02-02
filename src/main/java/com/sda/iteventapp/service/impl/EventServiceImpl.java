package com.sda.iteventapp.service.impl;

import com.sda.iteventapp.exception.EventMemberNotFoundException;
import com.sda.iteventapp.exception.EventNotFoundException;
import com.sda.iteventapp.exception.UserNotFoundException;
import com.sda.iteventapp.form.EventForm;
import com.sda.iteventapp.model.*;
import com.sda.iteventapp.repository.*;
import com.sda.iteventapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private LocationRepository locationRepository;
    private CityRepository cityRepository;
    private EventTypeRepository eventTypeRepository;
    private EventSubjectRepository eventSubjectRepository;
    private UserRepository userRepository;
    private EventMemberRepository eventMemberRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository, CityRepository cityRepository, EventTypeRepository eventTypeRepository, EventSubjectRepository eventSubjectRepository, UserRepository userRepository, EventMemberRepository eventMemberRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.cityRepository = cityRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.eventSubjectRepository = eventSubjectRepository;
        this.userRepository = userRepository;
        this.eventMemberRepository = eventMemberRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByPhrase(String phrase) {
        try {
            long id = Long.parseLong(phrase);
            return Arrays.asList(eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id)));
        } catch (NumberFormatException e) {
            Optional<City> eventsByCity = cityRepository.findCityByName(phrase);
            if (eventsByCity.isPresent()) {
                return searchEventsByCity(phrase).orElseThrow(() -> new EventNotFoundException(phrase));
            }

            Optional<List<Event>> eventsByEventType = eventRepository.findAllByEventType_Name(phrase);
            if (eventsByEventType.isPresent()) {
                return searchEventByType(phrase).orElseThrow(() -> new EventNotFoundException(phrase));
            }

            Optional<List<Event>> eventsBySubject = eventRepository.findAllByEventSubject(phrase);
            if (eventsBySubject.isPresent()) {
                return searchEventsBySubject(phrase).orElseThrow(() -> new EventNotFoundException(phrase));
            }

            Optional<List<Event>> eventsByName = getEventsByName(phrase);
            if (eventsByName.isPresent()) {
                return eventsByName.orElseThrow(() -> new EventNotFoundException(phrase));
            }

            throw new EventNotFoundException(phrase);
        }
    }

    public List<Event> getEventsByQuery(String phrase, String name) {
        Optional<List<Event>> result;
        if (phrase == null) {
            result = eventRepository.findAllByNameContains(name);
        } else {
            result = eventRepository.findAllByNameContainsOrDescriptionContains(phrase);
        }
        return result.orElseThrow(() -> new EventNotFoundException(name));
    }

    //date format from uri: yyyyMMdd-HHmm
    public List<Event> getEventBetweenDates(String from, String to) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String start = (from == null ? LocalDateTime.now().format(dtf) : dateFromFormatter(from));
        String end = (to == null ? LocalDateTime.of(9999, 12, 31, 23, 59).format(dtf) : dateToFormatter(to));
        return eventRepository.findAllEventsBetweenTwoDate(start, end).orElseThrow(() -> new EventNotFoundException());
    }

    public List<Event> getAllUserEvents(Long id) {
        return eventRepository.findAllEventUser(id).orElseThrow(() -> new EventNotFoundException());
    }

    public Event saveEvent(EventForm eventForm) {
        Event event = new Event();
        EventMember eventMember = new EventMember();

        event.setLocation(locationFromEventForm(eventForm.getCityName(), eventForm.getLocationName(), eventForm.getAddress(), eventForm.getNotes(), eventForm.getLatitude(), eventForm.getLongitude()));
        event.setSubjectList(subjectListFromEventForm(eventForm.getEventSubjects()));
        event.setEventType(eventTypeFromEventForm(eventForm.getEventType()));
        event.setDateTime(localDateTimeFromEventForm(eventForm.getEventDataTime()));
        event.setCoverPhotoPath(eventForm.getEventCoverLink());
        event.setDescription(eventForm.getEventDescription());
        event.setName(eventForm.getEventName());
        event.setWebsite(eventForm.getEventWebsite());
        Event saved = eventRepository.save(event);

        eventMember.setEvent(saved);
        eventMember.setUser(userFromUserId(eventForm.getUserId()));
        eventMember.setRoleUserEvent(RoleUserEvent.ORGANIZER);
        eventMemberRepository.save(eventMember);

        return saved;
    }

    public Event updateEvent(Long id, EventForm eventForm) {
        Event eventToModify = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));

        if (!eventForm.getEventName().equals(eventToModify.getName())) {
            eventToModify.setName(eventForm.getEventName());
        }

        if (!eventForm.getEventDescription().equals(eventToModify.getDescription())) {
            eventToModify.setDescription(eventForm.getEventDescription());
        }

        LocalDateTime localDateTime = localDateTimeFromEventForm(eventForm.getEventDataTime());
        if (!eventToModify.getDateTime().equals(localDateTime)) {
            eventToModify.setDateTime(localDateTime);
        }

        EventType eventType = eventTypeFromEventForm(eventForm.getEventType());
        if (!eventToModify.getEventType().equals(eventType)) {
            eventToModify.setEventType(eventType);
        }

        if (!eventToModify.getCoverPhotoPath().equals(eventForm.getEventCoverLink())) {
            eventToModify.setCoverPhotoPath(eventForm.getEventCoverLink());
        }

        if (!eventToModify.getWebsite().equals(eventForm.getEventWebsite())) {
            eventToModify.setWebsite(eventForm.getEventWebsite());
        }

        Set<EventSubject> eventSubjects = subjectListFromEventForm(eventForm.getEventSubjects());
        if (!eventToModify.getEventType().equals(eventSubjects)) {
            eventToModify.setSubjectList(eventSubjects);
        }

        Location location = locationFromEventForm(eventForm.getCityName(), eventForm.getLocationName(), eventForm.getAddress(), eventForm.getNotes(), eventForm.getLatitude(), eventForm.getLongitude());
        if (!eventToModify.getLocation().getId().equals(location.getId())) {
            eventToModify.setLocation(location);
        }

        User user = userFromUserId(eventForm.getUserId());
        EventMember eventMember = eventMemberRepository.eventMemberOrganizerEvent(id).orElseThrow(() -> new EventMemberNotFoundException());
        if (!eventMember.getUser().getId().equals(user.getId())) {
            eventMember.setUser(user);
        }

        Event updated = eventRepository.save(eventToModify);
        eventMemberRepository.save(eventMember);
        return updated;
    }

    public void deleteEvent(Long id) {
        Event existed = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        eventRepository.delete(existed);
    }

    private Optional<List<Event>> getEventsByName(String name) {
        return eventRepository.findAllByEventsName(name);
    }

    private Location locationFromEventForm(String city, String locationName, String address, String notes, String latitude, String longitude) {
        Location location = new Location();
        Optional<City> cityByName = cityRepository.findCityByName(city);
        if (!cityByName.isPresent()) {
            City newCity = new City(city);
            cityRepository.save(newCity);
            location = new Location(locationName, newCity, address, notes, latitude, longitude);
            return locationRepository.save(location);
        } else {
            City existedCity = cityByName.get();
            location.setCity(existedCity);
            Optional<Location> locationsByNameAndAddress = locationRepository.findLocationByNameAndAddress(locationName, address);
            if (!locationsByNameAndAddress.isPresent()) {
                Location newLocation = new Location(locationName, existedCity, address, notes, latitude, longitude);
                return locationRepository.save(newLocation);
            } else {
                return locationsByNameAndAddress.get();
            }
        }
    }

    private Set<EventSubject> subjectListFromEventForm(String eventSubjects) {
        Set<EventSubject> result = new HashSet<>();

        Arrays.stream(eventSubjects.split(",")).forEach(s -> {
            EventSubject subject = eventSubjectRepository.findFirstByName(s).orElseGet(() -> {
                EventSubject eventSubject = new EventSubject(s);
                return eventSubject;
            });
            result.add(subject);
        });

        return result;
    }

    private User userFromUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    private EventType eventTypeFromEventForm(String type) {
        return eventTypeRepository.findByName(type).orElseGet(() -> {
            EventType eventType = new EventType(type);
            eventTypeRepository.save(eventType);
            return eventType;
        });
    }

    private LocalDateTime localDateTimeFromEventForm(String timestamp) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(timestamp, dtf);
    }

    private Optional<List<Event>> searchEventsByCity(String city) {
        return eventRepository.findAllByLocation_City_Name(city);
    }

    private Optional<List<Event>> searchEventsBySubject(String subject) {
        return eventRepository.findAllByEventSubject(subject);
    }

    private Optional<List<Event>> searchEventByType(String type) {
        return eventRepository.findAllByEventType_Name(type);
    }

    private StringBuilder dateFormatter(String dateURI) {
        if (dateURI.length() < 8) throw new IllegalArgumentException("date to short");
        StringBuilder sb = new StringBuilder(dateURI.substring(0, 4));
        sb.append("-" + dateURI.substring(4, 6));
        sb.append("-" + dateURI.substring(6, 8));
        return sb;
    }

    private String dateFromFormatter(String dateURI) {
        StringBuilder sb = dateFormatter(dateURI);
        if (dateURI.contains("-")) {
            sb.append(" " + dateURI.substring(9, 11) + ":" + dateURI.substring(11, 13));
        } else {
            sb.append(" 00:00");
        }
        return sb.toString();
    }

    private String dateToFormatter(String dateURI) {
        StringBuilder sb = dateFormatter(dateURI);
        if (dateURI.contains("-")) {
            sb.append(" " + dateURI.substring(9, 11) + ":" + dateURI.substring(11, 13));
        } else {
            sb.append(" 23:59");
        }
        return sb.toString();
    }
}