package com.example.wikideas.services;

import com.example.wikideas.repository.TopicRepository;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }


}
