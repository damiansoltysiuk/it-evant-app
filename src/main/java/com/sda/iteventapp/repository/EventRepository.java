package com.sda.iteventapp.repository;

import com.sda.iteventapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    Optional<List<Event>> findAllByLocation_City_Name(String city);

    @Query(value = "SELECT DISTINCT e.* FROM events e " +
            "INNER JOIN event_event_subjects ees ON ees.event_id = e.event_id " +
            "INNER JOIN event_subjects es ON ees.subject_id = es.event_subject_id WHERE LOWER(es.name) = LOWER(:subjectName)", nativeQuery = true)
    Optional<List<Event>> findAllByEventSubject(@Param("subjectName") String subject);

    Optional<List<Event>> findAllByEventType_Name(String type);

    @Query(value = "SELECT DISTINCT * FROM events e WHERE e.name like CONCAT('%',:phrase,'%') or LOWER(e.description) like LOWER(CONCAT('%',:phrase,'%'))", nativeQuery = true)
    Optional<List<Event>> findAllByNameContainsOrDescriptionContains(@Param("phrase") String phrase);

    @Query(value = "SELECT DISTINCT * FROM events e WHERE LOWER(e.name) = LOWER(:name)", nativeQuery = true)
    Optional<List<Event>> findAllByEventsName(@Param("name") String name);

    Optional<List<Event>> findAllByNameContains(String eventName);

    @Query(value = "SELECT * FROM events e WHERE CAST(e.date_time as datetime) BETWEEN :start AND :end_", nativeQuery = true)
    Optional<List<Event>> findAllEventsBetweenTwoDate(@Param("start") String from, @Param("end_") String to);

    @Query(value = "SELECT e.* FROM users u " +
            "INNER JOIN event_member em ON em.user_id = u.user_id " +
            "INNER JOIN events e ON em.event_id = e.event_id " +
            "WHERE u.user_id = :userID", nativeQuery = true)
    Optional<List<Event>> findAllEventUser(@Param("userID") long id);
}
