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
import com.mdd.api.exceptions.NotFoundException;
import com.mdd.api.model.AppUser;
import com.mdd.api.service.AppUserService;
import com.mdd.api.service.PostService;

/**
 * Service class for managing comments.
 */
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

    /**
     * Retrieves all comments for a specific post, sorted by creation date in
     * descending order.
     *
     * @param postId The ID of the post to get comments for
     * @return A list of CommentDto objects containing the comments
     * @throws NotFoundException if the post is not found
     */
    public List<CommentDto> getCommentsByPostId(Long postId) throws NotFoundException {
        Post post = postService.getPostById(postId).orElseThrow(() -> new NotFoundException("Article non trouvé"));
        List<Comment> comments = commentRepository.findByPost(post);

        return comments.stream()
                .sorted((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()))
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new comment for a post.
     *
     * @param email            The email address of the user creating the comment
     * @param createCommentDto The information about the comment to create
     * @return A CommentDto containing the created comment information
     * @throws NotFoundException if either the user or post is not found
     */
    public CommentDto createComment(String email, CreateCommentDto createCommentDto) throws NotFoundException {
        AppUser author = appUserService.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));
        Post post = postService.getPostById(createCommentDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Article non trouvé"));

        Comment comment = new Comment();
        comment.setContent(createCommentDto.getContent());
        comment.setAuthor(author);
        comment.setPost(post);

        Comment createdComment = commentRepository.save(comment);
        return modelMapper.map(createdComment, CommentDto.class);
    }
}
