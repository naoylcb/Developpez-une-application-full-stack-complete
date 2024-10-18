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

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TopicDto> getTopicsInfo() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream()
                .map(topic -> modelMapper.map(topic, TopicDto.class))
                .collect(Collectors.toList());
    }

    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }
}