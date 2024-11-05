package com.mdd.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdd.api.dto.PostCreateDto;
import com.mdd.api.dto.PostDto;
import com.mdd.api.dto.TopicDto;
import com.mdd.api.exceptions.NotFoundException;
import com.mdd.api.model.AppUser;
import com.mdd.api.model.Post;
import com.mdd.api.repository.PostRepository;

/**
 * Service class for managing posts.
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves the feed of posts for a given user based on their topic subscriptions.
     * Posts are sorted by creation date in descending order.
     *
     * @param email The email address of the user
     * @return A list of PostDto objects containing the user's feed posts
     */
    public List<PostDto> getUserFeed(String email) throws NotFoundException {
        List<TopicDto> subscribedTopics = subscriptionService.getUserSubscriptions(email);
        List<Post> posts = postRepository.findByTopicIdIn(subscribedTopics.stream()
                .map(TopicDto::getId)
                .collect(Collectors.toList()));

        return posts.stream()
                .sorted((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()))
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param id The ID of the post to retrieve
     * @return An Optional containing the Post if found, or empty if not found
     */
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    /**
     * Retrieves information about a post by its ID.
     * Throws an exception if the post is not found.
     *
     * @param id The ID of the post to retrieve information for
     * @return A PostDto containing the post information
     * @throws NotFoundException if the post is not found
     */
    public PostDto getPostInfoById(Long id) throws NotFoundException {
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Article non trouvé"));
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    /**
     * Creates a new post.
     *
     * @param email The email address of the user creating the post
     * @param postCreateDto The information about the post to create
     * @return A PostDto containing the created post information
     * @throws NotFoundException if the user is not found
     */
    public PostDto createPost(String email, PostCreateDto postCreateDto) throws NotFoundException {
        AppUser appUser = appUserService.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));
        Post post = modelMapper.map(postCreateDto, Post.class);
        post.setAuthor(appUser);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }
}
