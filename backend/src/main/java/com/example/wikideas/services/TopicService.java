package com.example.wikideas.services;

import com.example.wikideas.entities.Topic;
import com.example.wikideas.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public ResponseEntity<List<Topic>> getTopics(String title) {
        try {
            List<Topic> topics = new ArrayList<Topic>();

            if (title == null)
                topics.addAll(topicRepository.findAll());
            else
                topics.addAll(topicRepository.findByTitleContaining(title));

            if (topics.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(topics, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Topic> getTopicById(long id) {
        Optional<Topic> topicData = topicRepository.findById(id);

        if (topicData.isPresent()) {
            return new ResponseEntity<>(topicData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Topic> createTopic(Topic topic) {
        try {
            ZonedDateTime current_time = ZonedDateTime.now();
            Topic _topic = topicRepository
                    .save(new Topic(topic.getTitle(), topic.getDescription(), current_time));
            return new ResponseEntity<>(_topic, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Topic> editTopic(long id, Topic topic) {
        Optional<Topic> topicData = topicRepository.findById(id);

        ZonedDateTime current_time = ZonedDateTime.now();
        if (topicData.isPresent()) {
            Topic _topic = topicData.get();
            _topic.setTitle(topic.getTitle());
            _topic.setDescription(topic.getDescription());
            _topic.setCreated_at(topicData.get().getCreated_at());
            _topic.setUpdated_at(current_time);
            return new ResponseEntity<>(topicRepository.save(_topic), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
