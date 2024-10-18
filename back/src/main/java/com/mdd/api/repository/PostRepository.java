package com.mdd.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdd.api.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findByTopicIdIn(List<Long> topicIds);
}