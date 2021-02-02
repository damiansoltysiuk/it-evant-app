package com.sda.iteventapp.repository;

import com.sda.iteventapp.model.EventMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventMemberRepository extends JpaRepository<EventMember, Long> {
    @Query(value = "SELECT * FROM event_members em WHERE em.event_id = :id_ AND em.role_user_event LIKE 'ORGANIZER'", nativeQuery = true)
    Optional<EventMember> eventMemberOrganizerEvent(@Param("id_") Long id);
}
