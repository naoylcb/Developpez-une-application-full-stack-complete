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

    public List<TopicDto> getUserSubscriptions(String email) {
        AppUser appUser = appUserService.getUserByEmail(email).orElseThrow();
        List<Subscription> subscriptions = subscriptionRepository.findAll();

        return subscriptions.stream()
                .filter(subscription -> subscription.getUser().getId().equals(appUser.getId()))
                .map(subscription -> subscription.getTopic())
                .map(topic -> modelMapper.map(topic, TopicDto.class))
                .collect(Collectors.toList());
    }

    public void subscribeToTopic(String email, Long topicId) {
        AppUser appUser = appUserService.getUserByEmail(email).orElseThrow();
        Topic topic = topicService.getTopicById(topicId).orElseThrow();

        Subscription subscription = new Subscription();
        subscription.setUser(appUser);
        subscription.setTopic(topic);
        subscriptionRepository.save(subscription);
    }

    public void unsubscribeFromTopic(String email, Long topicId) {
        AppUser appUser = appUserService.getUserByEmail(email).orElseThrow();
        Topic topic = topicService.getTopicById(topicId).orElseThrow();

        Subscription subscription = subscriptionRepository.findByUserAndTopic(appUser, topic).orElseThrow();
        subscriptionRepository.delete(subscription);
    }
}