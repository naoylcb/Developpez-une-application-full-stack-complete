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
import com.mdd.api.exceptions.NotFoundException;
import com.mdd.api.response.MessageResponse;

/**
 * REST controller for managing comments.
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Gets all comments for a specific post.
     *
     * @param postId The ID of the post to get comments for
     * @return ResponseEntity containing either:
     *         - 200 OK with list of CommentDto objects
     *         - 404 Not Found with error message if post not found
     */
    @GetMapping
    public ResponseEntity<?> getComments(@RequestParam Long postId) {
        try {
            List<CommentDto> comments = commentService.getCommentsByPostId(postId);
            return ResponseEntity.ok(comments);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
        }
    }

    /**
     * Creates a new comment.
     *
     * @param authentication The authentication object containing user details
     * @param createCommentDto The DTO containing the comment data
     * @return ResponseEntity containing either:
     *         - 201 Created with created CommentDto object
     *         - 404 Not Found with error message if user or post not found
     */
    @PostMapping
    public ResponseEntity<?> createComment(Authentication authentication,
            @Valid @RequestBody CreateCommentDto createCommentDto) {
        try {
            CommentDto createdComment = commentService.createComment(authentication.getName(), createCommentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
        }
    }
}
