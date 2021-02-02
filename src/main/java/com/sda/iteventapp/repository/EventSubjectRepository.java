package com.sda.iteventapp.repository;

import com.sda.iteventapp.model.EventSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventSubjectRepository extends JpaRepository<EventSubject, Long> {
    Optional<EventSubject> findFirstByName(String name);
}
