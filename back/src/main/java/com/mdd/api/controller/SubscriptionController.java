package com.mdd.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.mdd.api.dto.TopicDto;
import com.mdd.api.exceptions.BadRequestException;
import com.mdd.api.exceptions.NotFoundException;
import com.mdd.api.response.MessageResponse;
import com.mdd.api.service.SubscriptionService;

/**
 * REST controller for managing user subscriptions to topics.
 */
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    /**
     * Gets all topics that the authenticated user is subscribed to.
     *
     * @param authentication The authentication object containing user details
     * @return ResponseEntity containing either:
     *         - 200 OK with list of TopicDto objects if successful
     *         - 404 Not Found with error message if user not found
     */
    @GetMapping("/me")
    public ResponseEntity<?> getUserSubscriptions(Authentication authentication) {
        try {
            List<TopicDto> subscribedTopics = subscriptionService.getUserSubscriptions(authentication.getName());
            return ResponseEntity.ok(subscribedTopics);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
        }
    }

    /**
     * Subscribes the authenticated user to a topic.
     *
     * @param authentication The authentication object containing user details
     * @param topicId The ID of the topic to subscribe to
     * @return ResponseEntity containing either:
     *         - 200 OK if subscription successful
     *         - 400 Bad Request with error message if already subscribed
     *         - 404 Not Found with error message if user or topic not found
     */
    @PostMapping
    public ResponseEntity<?> subscribeToTopic(Authentication authentication, @RequestParam Long topicId) {
        try {
            subscriptionService.subscribeToTopic(authentication.getName(), topicId);
            return ResponseEntity.ok().build();
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
        }
    }

    /**
     * Unsubscribes the authenticated user from a topic.
     *
     * @param authentication The authentication object containing user details
     * @param topicId The ID of the topic to unsubscribe from
     * @return ResponseEntity containing either:
     *         - 200 OK if unsubscription successful
     *         - 400 Bad Request with error message if not subscribed
     *         - 404 Not Found with error message if user or topic not found
     */
    @DeleteMapping
    public ResponseEntity<?> unsubscribeFromTopic(Authentication authentication, @RequestParam Long topicId) {
        try {
            subscriptionService.unsubscribeFromTopic(authentication.getName(), topicId);
            return ResponseEntity.ok().build();
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
        }
    }
}