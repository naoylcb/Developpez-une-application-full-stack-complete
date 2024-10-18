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

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/feed")
    public ResponseEntity<List<PostDto>> feed(Authentication authentication) {
        List<PostDto> posts = postService.getUserFeed(authentication.getName());
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        PostDto post = postService.getPostInfoById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/posts")
    public ResponseEntity<PostDto> createPost(Authentication authentication, @Valid @RequestBody PostCreateDto postCreateDto) {
        PostDto post = postService.createPost(authentication.getName(), postCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}