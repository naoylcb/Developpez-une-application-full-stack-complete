package com.mdd.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mdd.api.dto.TopicDto;
import com.mdd.api.service.TopicService;

/**
 * REST controller for managing topics.
 */
@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    /**
     * Retrieves all topics.
     *
     * @return ResponseEntity containing a list of TopicDto objects with topic information
     */
    @GetMapping("/topics")
    public ResponseEntity<List<TopicDto>> getTopics() {
        List<TopicDto> topics = topicService.getTopicsInfo();
        return ResponseEntity.ok(topics);
    }
}