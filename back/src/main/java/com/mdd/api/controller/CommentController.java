package com.mdd.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import com.mdd.api.dto.CommentDto;
import com.mdd.api.dto.CreateCommentDto;
import com.mdd.api.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments(@RequestParam Long postId) {
        List<CommentDto> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(Authentication authentication, @Valid @RequestBody CreateCommentDto createCommentDto) {
        CommentDto createdComment = commentService.createComment(authentication, createCommentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }
}
