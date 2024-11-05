package com.mdd.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdd.api.model.Topic;

/**
 * Repository interface for managing Topic entities.
 * Provides methods to interact with the topic data in the database.
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {
}