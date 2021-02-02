package com.sda.iteventapp.repository;

import com.sda.iteventapp.model.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
    @Query(value = "SELECT * FROM comments c WHERE c.event_id = :id ORDER BY published_at ASC", nativeQuery = true)
    Optional<List<Commentary>> findAllCommentsInEvent(@Param("id") Long id);
}
