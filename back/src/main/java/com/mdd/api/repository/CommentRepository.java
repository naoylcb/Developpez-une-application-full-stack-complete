package com.mdd.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdd.api.model.Comment;
import com.mdd.api.model.Post;

/**
 * Repository interface for managing Comment entities.
 * Provides methods to interact with the comment data in the database.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Finds all comments associated with a specific post.
     *
     * @param post The post to find comments for
     * @return List of comments belonging to the specified post
     */
    public List<Comment> findByPost(Post post);
}
