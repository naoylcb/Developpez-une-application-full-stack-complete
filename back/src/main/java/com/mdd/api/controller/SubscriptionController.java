package com.mdd.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.mdd.api.dto.TopicDto;
import com.mdd.api.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/me")
    public ResponseEntity<List<TopicDto>> getUserSubscriptions(Authentication authentication) {
        List<TopicDto> subscribedTopics = subscriptionService.getUserSubscriptions(authentication.getName());
        return ResponseEntity.ok(subscribedTopics);
    }

    @PostMapping
    public ResponseEntity<Void> subscribeToTopic(Authentication authentication, @RequestParam Long topicId) {
        subscriptionService.subscribeToTopic(authentication.getName(), topicId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unsubscribeFromTopic(Authentication authentication, @RequestParam Long topicId) {
        subscriptionService.unsubscribeFromTopic(authentication.getName(), topicId);
        return ResponseEntity.ok().build();
    }
}