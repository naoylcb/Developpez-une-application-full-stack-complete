package com.mdd.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdd.api.dto.TopicDto;
import com.mdd.api.model.Topic;
import com.mdd.api.repository.TopicRepository;

/**
 * Service class for managing topics.
 */
@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves information about all topics.
     *
     * @return A list of TopicDto objects containing topic information
     */
    public List<TopicDto> getTopicsInfo() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream()
                .map(topic -> modelMapper.map(topic, TopicDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a topic by its ID.
     *
     * @param id The ID of the topic to retrieve
     * @return An Optional containing the Topic if found, or empty if not found
     */
    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }
}