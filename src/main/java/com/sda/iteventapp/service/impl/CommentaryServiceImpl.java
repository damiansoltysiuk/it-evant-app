package com.sda.iteventapp.service.impl;

import com.sda.iteventapp.exception.CommentNotFoundException;
import com.sda.iteventapp.exception.EventNotFoundException;
import com.sda.iteventapp.exception.UserNotFoundException;
import com.sda.iteventapp.form.CommentForm;
import com.sda.iteventapp.model.Commentary;
import com.sda.iteventapp.model.Event;
import com.sda.iteventapp.model.User;
import com.sda.iteventapp.repository.CommentaryRepository;
import com.sda.iteventapp.repository.EventRepository;
import com.sda.iteventapp.repository.UserRepository;
import com.sda.iteventapp.service.CommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaryServiceImpl implements CommentaryService {
    private CommentaryRepository commentaryRepository;
    private EventRepository eventRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentaryServiceImpl(CommentaryRepository commentaryRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.commentaryRepository = commentaryRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Commentary> getAllComments() {
        return commentaryRepository.findAll();
    }

    public Optional<Commentary> getCommentsById(Long id) {
        return commentaryRepository.findById(id);
    }

    public Optional<List<Commentary>> getAllCommentsInEvent(Long id) {
        return commentaryRepository.findAllCommentsInEvent(id);
    }

    public Commentary saveComments(CommentForm commentForm) {
        Long userId = commentForm.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Long eventId = commentForm.getEventId();
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));

        Commentary commentary = new Commentary(user, event, commentForm.getMessage());
        return commentaryRepository.save(commentary);
    }

    @Override
    public Commentary editComments(CommentForm commentForm, Long id) {
        Commentary existed = commentaryRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));

        Long userId = commentForm.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Long eventId = commentForm.getEventId();
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));

        existed.setAuthor(user);
        existed.setEvent(event);
        existed.setMessage(commentForm.getMessage());
        return commentaryRepository.save(existed);
    }

    public void deleteComment(Long id) {
        Commentary existed = commentaryRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        commentaryRepository.delete(existed);
    }
}
