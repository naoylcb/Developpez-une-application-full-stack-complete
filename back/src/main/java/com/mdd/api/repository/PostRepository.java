package com.mdd.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdd.api.model.Post;

/**
 * Repository interface for managing Post entities.
 * Provides methods to interact with the post data in the database.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Finds all posts that belong to any of the given topic IDs.
     *
     * @param topicIds List of topic IDs to find posts for
     * @return List of posts belonging to any of the specified topics
     */
    public List<Post> findByTopicIdIn(List<Long> topicIds);
}