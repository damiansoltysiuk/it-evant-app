package com.sda.iteventapp.service;

import com.sda.iteventapp.form.CommentForm;
import com.sda.iteventapp.model.Commentary;

import java.util.List;
import java.util.Optional;

public interface CommentaryService {

    List<Commentary> getAllComments();

    Optional<Commentary> getCommentsById(Long id);

    Optional<List<Commentary>> getAllCommentsInEvent(Long id);

    Commentary saveComments(CommentForm commentForm);

    Commentary editComments(CommentForm commentForm, Long id);

    void deleteComment(Long id);
}
