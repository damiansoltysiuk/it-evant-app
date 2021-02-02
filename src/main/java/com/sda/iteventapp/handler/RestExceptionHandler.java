package com.sda.iteventapp.handler;

import com.sda.iteventapp.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(value = {CityNotFoundException.class})
    public ResponseEntity cityNotFound(CityNotFoundException ex) {
        log.debug("Handling CityNotFoundException\n");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {CommentNotFoundException.class})
    public ResponseEntity commentNotFound(CommentNotFoundException ex) {
        log.debug("Handling CommentNotFoundException\n");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {EventMemberNotFoundException.class})
    public ResponseEntity eventMemberNotFound(EventMemberNotFoundException ex) {
        log.debug("Handling EventMemberNotFoundException\n");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {EventNotFoundException.class})
    public ResponseEntity eventNotFound(EventNotFoundException ex) {
        log.debug("Handling EventNotFoundException\n");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {LocationNotFoundException.class})
    public ResponseEntity locationNotFound(LocationNotFoundException ex) {
        log.debug("Handling LocationNotFoundException\n");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity userNotFound(UserNotFoundException ex) {
        log.debug("Handling UserNotFoundException\n");
        return ResponseEntity.notFound().build();
    }
}
