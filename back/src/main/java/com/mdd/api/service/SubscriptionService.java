package com.mdd.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdd.api.dto.TopicDto;
import com.mdd.api.model.AppUser;
import com.mdd.api.model.Subscription;
import com.mdd.api.model.Topic;
import com.mdd.api.repository.SubscriptionRepository;
import com.mdd.api.exceptions.BadRequestException;
import com.mdd.api.exceptions.NotFoundException;

/**
 * Service class for managing user subscriptions to topics.
 */
@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves all topics that a user is subscribed to.
     *
     * @param email The email address of the user
     * @return A list of TopicDto objects representing the topics the user is subscribed to
     * @throws NotFoundException if the user is not found
     */
    public List<TopicDto> getUserSubscriptions(String email) throws NotFoundException {
        AppUser appUser = appUserService.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));
        List<Subscription> subscriptions = subscriptionRepository.findByUser(appUser);

        return subscriptions.stream()
                .map(subscription -> subscription.getTopic())
                .map(topic -> modelMapper.map(topic, TopicDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Subscribes a user to a specific topic.
     *
     * @param email The email address of the user
     * @param topicId The ID of the topic to subscribe to
     * @throws NotFoundException if either the user or topic is not found
     * @throws BadRequestException if the user is already subscribed to the topic
     */
    public void subscribeToTopic(String email, Long topicId) throws NotFoundException, BadRequestException {
        AppUser appUser = appUserService.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));
        Topic topic = topicService.getTopicById(topicId).orElseThrow(() -> new NotFoundException("Thème non trouvé"));

        if (subscriptionRepository.findByUserAndTopic(appUser, topic).isPresent()) {
            throw new BadRequestException("Vous êtes déjà abonné à ce thème !");
        }

        Subscription subscription = new Subscription();
        subscription.setUser(appUser);
        subscription.setTopic(topic);
        subscriptionRepository.save(subscription);
    }

    /**
     * Unsubscribes a user from a specific topic.
     *
     * @param email The email address of the user
     * @param topicId The ID of the topic to unsubscribe from
     * @throws NotFoundException if either the user or topic is not found
     * @throws BadRequestException if the user is not subscribed to the topic
     */
    public void unsubscribeFromTopic(String email, Long topicId) throws NotFoundException, BadRequestException {
        AppUser appUser = appUserService.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));
        Topic topic = topicService.getTopicById(topicId).orElseThrow(() -> new NotFoundException("Thème non trouvé"));

        if (!subscriptionRepository.findByUserAndTopic(appUser, topic).isPresent()) {
            throw new BadRequestException("Vous n'êtes pas abonné à ce thème !");
        }

        Subscription subscription = subscriptionRepository.findByUserAndTopic(appUser, topic).orElseThrow();
        subscriptionRepository.delete(subscription);
    }
}