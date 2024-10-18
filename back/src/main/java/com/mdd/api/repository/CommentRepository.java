package com.mdd.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdd.api.model.Comment;
import com.mdd.api.model.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public List<Comment> findByPost(Post post);
}
