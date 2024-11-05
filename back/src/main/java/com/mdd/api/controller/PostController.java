package com.mdd.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.mdd.api.dto.PostDto;
import com.mdd.api.dto.PostCreateDto;
import com.mdd.api.service.PostService;
import com.mdd.api.exceptions.NotFoundException;
import com.mdd.api.response.MessageResponse;

/**
 * REST controller for managing posts.
 */
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * Gets the feed of posts for the authenticated user.
     * The feed contains posts from topics the user is subscribed to.
     *
     * @param authentication The authentication object containing user details
     * @return ResponseEntity containing either:
     *         - 200 OK with list of PostDto objects
     *         - 404 Not Found with error message if user not found
     */
    @GetMapping("/feed")
    public ResponseEntity<?> feed(Authentication authentication) {
        try {
            List<PostDto> posts = postService.getUserFeed(authentication.getName());
            return ResponseEntity.ok(posts);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
        }
    }

    /**
     * Gets a specific post by its ID.
     *
     * @param id The ID of the post to retrieve
     * @return ResponseEntity containing either:
     *         - 200 OK with PostDto object
     *         - 404 Not Found with error message if post not found
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        try {
            PostDto post = postService.getPostInfoById(id);
            return ResponseEntity.ok(post);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
        }
    }

    /**
     * Creates a new post.
     *
     * @param authentication The authentication object containing user details
     * @param postCreateDto The DTO containing the new post data
     * @return ResponseEntity containing either:
     *         - 201 Created with created PostDto object
     *         - 404 Not Found with error message if user not found
     */
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(Authentication authentication,
            @Valid @RequestBody PostCreateDto postCreateDto) {
        try {
            PostDto post = postService.createPost(authentication.getName(), postCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(post);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
        }
    }
}