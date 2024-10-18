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
import com.mdd.api.model.AppUser;
import com.mdd.api.model.Post;
import com.mdd.api.repository.PostRepository;

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

    public List<PostDto> getUserFeed(String email) {
        List<TopicDto> subscribedTopics = subscriptionService.getUserSubscriptions(email);
        List<Post> posts = postRepository.findByTopicIdIn(subscribedTopics.stream()
                .map(TopicDto::getId)
                .collect(Collectors.toList()));

        return posts.stream()
                .sorted((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()))
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public PostDto getPostInfoById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    public PostDto createPost(String email, PostCreateDto postCreateDto) {
        AppUser appUser = appUserService.getUserByEmail(email).orElseThrow();
        Post post = modelMapper.map(postCreateDto, Post.class);
        post.setAuthor(appUser);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }
}
