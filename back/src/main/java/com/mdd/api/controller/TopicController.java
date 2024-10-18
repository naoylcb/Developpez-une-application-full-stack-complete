package com.mdd.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mdd.api.dto.TopicDto;
import com.mdd.api.service.TopicService;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/topics")
    public ResponseEntity<List<TopicDto>> getTopics() {
        List<TopicDto> topics = topicService.getTopicsInfo();
        return ResponseEntity.ok(topics);
    }
}