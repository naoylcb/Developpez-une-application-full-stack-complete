package com.mdd.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.mdd.api.repository.CommentRepository;
import com.mdd.api.model.Comment;
import com.mdd.api.model.Post;
import com.mdd.api.dto.CommentDto;
import com.mdd.api.dto.CreateCommentDto;
import com.mdd.api.model.AppUser;
import com.mdd.api.service.AppUserService;
import com.mdd.api.service.PostService;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PostService postService;

    public List<CommentDto> getCommentsByPostId(Long postId) {
        Post post = postService.getPostById(postId).orElseThrow();
        List<Comment> comments = commentRepository.findByPost(post);
        return comments.stream()
                .sorted((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()))
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    public CommentDto createComment(Authentication authentication, CreateCommentDto createCommentDto) {
        AppUser author = appUserService.getUserByEmail(authentication.getName()).orElseThrow();
        Post post = postService.getPostById(createCommentDto.getPostId()).orElseThrow();

        Comment comment = new Comment();
        comment.setContent(createCommentDto.getContent());
        comment.setAuthor(author);
        comment.setPost(post);

        Comment createdComment = commentRepository.save(comment);
        return modelMapper.map(createdComment, CommentDto.class);
    }
}
