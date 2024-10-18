package com.mdd.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdd.api.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}