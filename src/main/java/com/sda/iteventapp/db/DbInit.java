package com.sda.iteventapp.db;

import com.sda.iteventapp.model.*;
import com.sda.iteventapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private EventRepository eventRepository;
    private PasswordEncoder passwordEncoder;
    private CityRepository cityRepository;
    private EventSubjectRepository eventSubjectRepository;
    private EventTypeRepository eventTypeRepository;
    private LocationRepository locationRepository;
    private EventMemberRepository eventMemberRepository;
    private CommentaryRepository commentaryRepository;

    @Autowired
    public DbInit(UserRepository userRepository, EventRepository eventRepository, PasswordEncoder passwordEncoder, CityRepository cityRepository, EventSubjectRepository eventSubjectRepository, EventTypeRepository eventTypeRepository, LocationRepository locationRepository, EventMemberRepository eventMemberRepository, CommentaryRepository commentaryRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.passwordEncoder = passwordEncoder;
        this.cityRepository = cityRepository;
        this.eventSubjectRepository = eventSubjectRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.locationRepository = locationRepository;
        this.eventMemberRepository = eventMemberRepository;
        this.commentaryRepository = commentaryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder().username("user").password(passwordEncoder.encode("user")).email("user@ex.ex").role(Arrays.asList(UserRole.USER)).build();
        User admin = User.builder().username("admin").password(passwordEncoder.encode("admin")).email("admin@ex.ex").role(Arrays.asList(UserRole.ADMIN)).build();
        User moderator = User.builder().username("moderator").password(passwordEncoder.encode("moderator")).email("modereator@ex.ex").role(Arrays.asList(UserRole.MODERATOR)).build();

        User user1 = User.builder().username("Jan Kowalski").password(passwordEncoder.encode("user")).email("jankowalski@ex.ex").role(Arrays.asList(UserRole.USER)).build();
        User user2 = User.builder().username("Matt Deamon").password(passwordEncoder.encode("user")).email("m.deamon@ex.ex").role(Arrays.asList(UserRole.USER)).build();
        User user3 = User.builder().username("Tommy Lee Jones").password(passwordEncoder.encode("user")).email("tl.jones@ex.ex").role(Arrays.asList(UserRole.USER)).build();
        User user4 = User.builder().username("Alicia Vikander").password(passwordEncoder.encode("user")).email("a.vicander@ex.ex").role(Arrays.asList(UserRole.USER)).build();
        User user5 = User.builder().username("Vincent Cassel").password(passwordEncoder.encode("user")).email("v.cassel@ex.ex").role(Arrays.asList(UserRole.USER)).build();
        User user6 = User.builder().username("Julia Stiles").password(passwordEncoder.encode("user")).email("j.stiles@ex.ex").role(Arrays.asList(UserRole.USER)).build();
        User user7 = User.builder().username("Riz Ahmed").password(passwordEncoder.encode("user")).email("r.ahmed@ex.ex").role(Arrays.asList(UserRole.USER)).build();
        User user8 = User.builder().username("Ato Essandoh").password(passwordEncoder.encode("user")).email("a.essandox@ex.ex").role(Arrays.asList(UserRole.USER)).build();
        User user9 = User.builder().username("Scott Shepherd").password(passwordEncoder.encode("user")).email("s.shepherd@ex.ex").role(Arrays.asList(UserRole.USER)).build();
        User user10 = User.builder().username("Bill Camp").password(passwordEncoder.encode("user")).email("b.camp@ex.ex").role(Arrays.asList(UserRole.USER)).build();
        List<User> userList = Arrays.asList(admin, user, moderator, user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
        userRepository.saveAll(userList);

        List<City> cities = Arrays.asList(
                new City("Warszawa"),
                new City("Krakow"),
                new City("Łodź"),
                new City("Wrocław"),
                new City("Poznań"),
                new City("Gdańsk"),
                new City("Szczecin"),
                new City("Bydgoszcz"),
                new City("Lublin"),
                new City("Bialystok"),
                new City("Katowice"));
        cityRepository.saveAll(cities);


        List<EventSubject> eventSubjects = Arrays.asList(
                new EventSubject("Android"),
                new EventSubject("C_Sharp"),
                new EventSubject("C++"),
                new EventSubject("Java"),
                new EventSubject("JS"),
                new EventSubject("Python"),
                new EventSubject("Shell"),
                new EventSubject("SQL"),
                new EventSubject(".NET"));
        eventSubjectRepository.saveAll(eventSubjects);


        List<EventType> eventTypes = Arrays.asList(
                new EventType("Conference"),
                new EventType("Workshop"),
                new EventType("Meeting"),
                new EventType("Happening"),
                new EventType("Course"),
                new EventType("Bootcamp"));
        eventTypeRepository.saveAll(eventTypes);


        Location location1 = new Location("Inkubator Przedsiębiorczości", cityRepository.findCityByName("Bialystok").get(), "Zwierzyniecka 10", "", "53.118668", "23.151269");
        Location location2 = new Location("Białostocki Park Naukowo-Technologiczny", cityRepository.findCityByName("Bialystok").get(), "Zamenhofa 25", "", "53.134550", "23.160070");
        Location location3 = new Location("Pałac Kultury i Nauki", cityRepository.findCityByName("Warszawa").get(), "Plac Defilad 1", "", "52.231918", "21.006781");
        Location location4 = new Location("HubHub Nowogrodzka Square", cityRepository.findCityByName("Warszawa").get(), "Aleje Jerozolimskie 93", "", "52.227992", "21.004729");
        Location location5 = new Location("Przestrzeń from Facebook", cityRepository.findCityByName("Warszawa").get(), "Koszykowa 61", "", "52.222508", "21.011852");
        List<Location> locations = Arrays.asList(location1, location2, location3, location4, location5);
        locationRepository.saveAll(locations);

        LocalDateTime localDateTime = LocalDateTime.of(2020, 10, 20, 20, 00);
        Event event1 = new Event("MoBi", localDateTime, eventTypeRepository.findById(3L).get(), locationRepository.findById(2L).get());

        City cityNew = new City("Suwalki");
        cityRepository.save(cityNew);
        Location locationNew = new Location("Browar Pólnocny", cityNew, "Noniewicza 42", "", "54.103931", "22.932445");
        locationNew.setCity(cityNew);
        locationRepository.save(locationNew);

        Event event2 = new Event("MoBiSuw", localDateTime.plusDays(50L), eventTypeRepository.findById(2L).get(), locationNew);

        event1.addEventSubject(new EventSubject("React Native"));
        event1.addEventSubject(new EventSubject("TypeScript"));
        eventRepository.save(event1);
        EventMember eventMember1 = new EventMember(event1, userRepository.findById(1L).get(), RoleUserEvent.ORGANIZER);
        eventMemberRepository.save(eventMember1);
        event2.addEventSubject(new EventSubject("Reactjs"));
        event2.addEventSubject(new EventSubject("Angular"));
        eventRepository.save(event2);
        EventMember eventMember2 = new EventMember(event2, userRepository.findById(4L).get(), RoleUserEvent.ORGANIZER);
        eventMemberRepository.save(eventMember2);

        Event event3 = new Event("Hello Spring!", LocalDateTime.of(2020, 03, 21, 9, 30), eventTypeRepository.findById(2L).get(), locationRepository.findById(1l).get());
        Set<EventSubject> set = new HashSet<>();
        set.add(eventSubjectRepository.findFirstByName("Java").get());
//        event3.setSubjectList(set);
        event3.setDescription("We regret to inform you that EPAM’s QA TechTalk – a meetup originally scheduled for " +
                "March 11, 2020 is canceled due to the unavoidable circumstances. We will do our best to reschedule it " +
                "as soon as possible and get back to you with the originally planned presentations. Please accept our " +
                "apologies with regards to this unfortunate matter. EPAM Marketing Team");
        event3.setWebsite("https://events.epam.com/events/hello-spring-hackathon");
        event3.setCoverPhotoPath("https://crossweb.pl/upload/gallery/event/41662/meetup_(1).png");
        EventMember eventMember3 = new EventMember(event3, userRepository.findById(3l).get(), RoleUserEvent.ORGANIZER);
        eventRepository.save(event3);
        eventMemberRepository.save(eventMember3);

        Event event4 = new Event("BiteIT #56: Java nie zna kompromisów",
                LocalDateTime.of(2020, 05, 21, 17, 30),
                eventTypeRepository.findById(3L).get(),
                locationRepository.findById(2l).get());
//        event4.addEventSubject(eventSubjectRepository.findFirstByName("Java").get());
        event4.setDescription("Zapraszamy na kolejny meetup z cyklu BiteIT. W czwartek 21 maja spotykamy się w Strefie " +
                "Centralnej, gdzie nasi prelegenci przybliżą nam temat programowania obiektowego oraz RScoket jako " +
                "jednej z metod komunikacji miedzy mikroserwisami. Jakich prelekcji możecie się spodziewać?\n" +
                "Marcin Chrost: Dobre praktyki programowania obiektowego w Javie: Programowanie obiektowe wydaje się " +
                "być bardzo naturalnym i oczywistym stylem kodowania. Jeżeli jednak nie zastosujemy się w nim do paru " +
                "podstawowych zasad, doprowadzimy do sytuacji, która skończy się nadspodziewanie zawiłym i trudnym w " +
                "utrzymaniu kodem. Podczas wykładu Marcin omówi kilka podstawowych reguł programowania obiektowego, " +
                "których znajomość zaoszczędzi nam powyższych problemów.");
        event4.setWebsite("https://www.meetup.com/BiteIT-IT-meetups-by-JCommerce/events/269298561/");
        event4.setCoverPhotoPath("https://crossweb.pl/upload/gallery/cycles/9244/300x300/999_(kopiowanie).png");
        EventMember eventMember4 = new EventMember(event3, userRepository.findById(3l).get(), RoleUserEvent.ORGANIZER);
        eventRepository.save(event4);
        eventMemberRepository.save(eventMember4);


        Commentary commentary1 = new Commentary(userRepository.findById(3l).get(), event1, "First commit");
        Commentary commentary2 = new Commentary(userRepository.findById(1l).get(), event1, "Secundo commento");
        Commentary commentary3 = new Commentary(userRepository.findById(3l).get(), event2, "Covid-19 Party");
        Commentary commentary4 = new Commentary(userRepository.findById(2l).get(), event2, "Cancer");
        Commentary commentary5 = new Commentary(userRepository.findById(3l).get(), event2, "Flu party");
        Commentary commentary6 = new Commentary(user3, event2, "Flu party");
        List<Commentary> comments = Arrays.asList(commentary1, commentary2, commentary3, commentary4, commentary5, commentary6);
        commentaryRepository.saveAll(comments);
    }
}
