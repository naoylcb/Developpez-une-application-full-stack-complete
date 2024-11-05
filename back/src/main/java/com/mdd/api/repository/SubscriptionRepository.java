package com.mdd.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdd.api.model.AppUser;
import com.mdd.api.model.Subscription;
import com.mdd.api.model.Topic;

/**
 * Repository interface for managing Subscription entities.
 * Provides methods to interact with the subscription data in the database.
 */
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Finds all subscriptions for a given user.
     * 
     * @param user The user whose subscriptions to find
     * @return List of subscriptions belonging to the user
     */
    public List<Subscription> findByUser(AppUser user);

    /**
     * Finds a specific subscription for a user and topic combination.
     * 
     * @param user The user to find subscription for
     * @param topic The topic to find subscription for
     * @return Optional containing the subscription if found, empty otherwise
     */
    public Optional<Subscription> findByUserAndTopic(AppUser user, Topic topic);
}
