package com.mdd.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdd.api.model.AppUser;
import com.mdd.api.model.Subscription;
import com.mdd.api.model.Topic;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    public Optional<Subscription> findByUserAndTopic(AppUser user, Topic topic);
}
