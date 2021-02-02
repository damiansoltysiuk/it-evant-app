package com.sda.iteventapp.restcontroller;

import com.sda.iteventapp.dto.CommentaryDto;
import com.sda.iteventapp.exception.CommentNotFoundException;
import com.sda.iteventapp.form.CommentForm;
import com.sda.iteventapp.mapper.CommentaryMapper;
import com.sda.iteventapp.model.Commentary;
import com.sda.iteventapp.service.CommentaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentaryController {
    private CommentaryService commentaryService;

    @Autowired
    public CommentaryController(CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @ApiOperation(value = "Return all comments")
    @GetMapping("")
    public ResponseEntity all() {
        List<Commentary> commentaryList = commentaryService.getAllComments();
        List<CommentaryDto> commentaryDtoList = CommentaryMapper.MAPPER.commentaryDtoList(commentaryList);
        return ResponseEntity.ok(commentaryDtoList);
    }

    @ApiOperation(value = "Return comments by id")
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Commentary commentary = commentaryService.getCommentsById(id).orElseThrow(() -> new CommentNotFoundException(id));
        CommentaryDto commentaryDto = CommentaryMapper.MAPPER.commentaryToCommentaryDTO(commentary);
        return ResponseEntity.ok(commentaryDto);
    }

    @ApiOperation(value = "Return all comments in event")
    @GetMapping("/event/{eventId}")
    public ResponseEntity getCommentsInEvent(@PathVariable("eventId") Long id) {
        List<Commentary> commentaryList = commentaryService.getAllCommentsInEvent(id).orElseThrow(() -> new CommentNotFoundException());
        List<CommentaryDto> commentaryDtoList = CommentaryMapper.MAPPER.commentaryDtoList(commentaryList);
        return ResponseEntity.ok(commentaryDtoList);
    }

    @ApiOperation(value = "Add comment")
    @PostMapping("")
    public ResponseEntity save(@RequestBody CommentForm commentForm, HttpServletRequest request) {
        Commentary saved = commentaryService.saveComments(commentForm);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                .fromContextPath(request)
                .path("/api/comment")
                .buildAndExpand(saved.getId())
                .toUri()
        ).build();
    }

    @ApiOperation(value = "Update comment")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id, @RequestBody CommentForm commentForm) {
        commentaryService.editComments(commentForm, id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete comment")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        commentaryService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}